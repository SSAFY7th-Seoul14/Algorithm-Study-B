import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ9372 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			// 국가의 수 n
			int n = Integer.parseInt(st.nextToken());
			// 비행기 종류 m
			int m = Integer.parseInt(st.nextToken());
			int[] parent = makeSet(n);
			int ans = 0;
			for (int i = 0; i < m; i++) {
				// 국가 a, b 사이를 왕복하는 비행기
				st = new StringTokenizer(br.readLine());
				if (union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), parent))
					++ans;
			}
			bw.write(String.format("%d%n", ans));
		}
		br.close();
		bw.flush();
		bw.close();
	}

	private static boolean union(int a, int b, int[] parent) {
		int aRoot = find(a, parent);
		int bRoot = find(b, parent);
		if (aRoot == bRoot)
			return false;
		parent[bRoot] = aRoot;
		return true;
	}

	private static int find(int a, int[] parent) {
		if (parent[a] == a)
			return a;
		return parent[a] = find(parent[a], parent);
	}

	private static int[] makeSet(int n) {
		int[] set = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			set[i] = i;
		}
		return set;
	}

}