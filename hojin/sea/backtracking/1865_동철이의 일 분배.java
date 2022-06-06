import java.util.*;
import java.io.*;

public class SWEA1865_동철이의일분배 {

	static double ans;
	static int n;
	static double[][] Pij;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = stoi(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			n = stoi(br.readLine());
			Pij = new double[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					Pij[i][j] = (stoi(st.nextToken()) / 100.0);
				}
			}
			ans = 0;
			for (int i = 0; i < n; i++) {
				BT(1 << i, 1, Pij[0][i]);
			}
			sb.append('#').append(tc).append(' ').append(String.format("%.6f", ans * 100.0)).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static void BT(int selected, int cnt, double p) {
		if (p == 0.0)
			return;
		if (p < ans) return;
		if (cnt == n) {
			ans = Math.max(p, ans);
			return;
		}
		for (int i = 0; i < n; i++) {
			if ((selected & 1 << i) == 0) {
				BT(selected | 1 << i, cnt + 1, p * Pij[cnt][i]);
			}
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
