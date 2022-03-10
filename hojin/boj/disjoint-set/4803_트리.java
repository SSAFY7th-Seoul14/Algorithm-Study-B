import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ4803_트리 {
	static int[] parents;
	static boolean[] cycle;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int tc = 0;
		while (true) {
			st = new StringTokenizer(br.readLine());
//			정점의 개수 n <= 500
			int n = Integer.parseInt(st.nextToken());
//			간선의 개수 m <= n(n-1)/2
			int m = Integer.parseInt(st.nextToken());
			if (n == 0 && m == 0) {
				break;
			}
			// 초기화 작업
			reset(n);
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			String ans;
			// union작업 후 tree의 개수 세어주기
			int T = countTrees(n);
			switch (T) {
			case 0:
				ans = "No trees.";
				break;
			case 1:
				ans = "There is one tree.";
				break;
			default:
				ans = String.format("A forest of %d trees.", T);
				break;
			}
			sb.append(String.format("Case %d: %s%n", ++tc, ans));
		}
		System.out.println(sb);
	}

	private static void reset(int n) {
		// 서로소집합 생성
		makeSet(n);
		// cycle 여부 체크할 boolean 배열 생성
		cycle = new boolean[n + 1];
	}

	private static int countTrees(int n) {
		int cnt = 0;
		for (int i = 1; i <= n; i++) {
			//해당 요소가 cycle에 들어갔다면 tree가 아니므로 continue
			if (cycle[i])
				continue;
			if (parents[i] == i)
				++cnt;
		}
		return cnt;
	}

	private static void union(int a, int b) {
		if (a == b)
			return;
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) {
			//순환이 발생하면 순환 체크
			cycle[aRoot] = true;
			return;
		}
		//기본은 bRoot을 aRoot에 붙이는 것이지만 bRoot가 이미 순환이었을 수 있으므로 이 경우는 반대로 붙여주기
		if (cycle[bRoot])
			parents[aRoot] = bRoot;
		else
			parents[bRoot] = aRoot;
		return;
	}

	private static int findSet(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = findSet(parents[a]);
	}

	private static void makeSet(int n) {
		parents = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}
	}

}