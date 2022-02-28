import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA7236 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			char[][] reservoir = new char[n][n];
			int ans = 0;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					reservoir[i][j] = st.nextToken().charAt(0);
				}
			}
			ans = checkDepth(reservoir, n);
			bw.write(String.format("#%d %d%n", tc, ans));
		}
		br.close();
		bw.flush();
		bw.close();
	}

	private static int checkDepth(char[][] reservoir, int n) {
		int[][] delta = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (reservoir[i][j] == 'W') {
					int cnt = 0;
					for (int k = 0; k < 8; k++) {
						int nR = i + delta[k][0];
						int nC = j + delta[k][1];

						if (nR >= 0 && nR < n && nC >= 0 && nC < n) {
							if (reservoir[nR][nC] == 'W') {
								++cnt;
							}
						}
					}
					if (cnt == 0)
						cnt = 1;
					if (max < cnt)
						max = cnt;
				}
			}
		}
		return max;
	}

}