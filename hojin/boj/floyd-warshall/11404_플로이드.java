import java.util.*;
import java.io.*;

public class BOJ11404_플로이드 {

	static int INF = 10000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int n = stoi(br.readLine());
		int[][] costs = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(costs[i], INF);
		}
		for (int i = 0; i < n; i++) {
			costs[i][i] = 0;
		}
		int m = stoi(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken()) - 1;
			int to = stoi(st.nextToken()) - 1;
			int cost = stoi(st.nextToken());
			costs[from][to] = Math.min(cost, costs[from][to]);
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				if (i == k)
					continue;
				for (int j = 0; j < n; j++) {
					if (j == i || j == k)
						continue;
					costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
				}
			}
		}
		for (int[] is : costs) {
			for (int i : is) {
				sb.append(i == INF ? 0 : i).append(' ');
			}
			sb.append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
