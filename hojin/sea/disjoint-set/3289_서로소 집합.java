import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append(String.format("#%d ", tc));
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());

			parent = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				parent[i] = i;
			}
			int m = Integer.parseInt(st.nextToken());
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				switch (cmd) {
				case 0:
					union(a, b);
					break;
				case 1:
					sb.append(find(a) == find(b) ? 1 : 0);
					break;
				}
			}
			sb.append("\n");
		}
		br.close();
		System.out.println(sb);
	}

	private static int find(int i) {
		if (parent[i] == i)
			return i;
		else
			return parent[i] = find(parent[i]);
	}

	private static void union(int a, int b) {
		int pA = find(a);
		int pB = find(b);

		if (pA != pB) parent[pB] = pA;

	}
}