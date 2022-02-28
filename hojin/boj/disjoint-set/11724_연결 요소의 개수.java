import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11724 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] parent = makeSet(n);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), parent);
		}
		int cnt = 0;
		for (int i = 1; i <= n; i++) {
			if (parent[i] == i)
				++cnt;
		}
		System.out.println(cnt);
	}

	private static void union(int a, int b, int[] parent) {
		int aRoot = find(a, parent);
		int bRoot = find(b, parent);
		if (aRoot == bRoot)
			return;
		parent[bRoot] = aRoot;
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