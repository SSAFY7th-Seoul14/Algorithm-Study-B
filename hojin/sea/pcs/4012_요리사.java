import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4012 {

	static int n, min;
	static int[][] S;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			S = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					int data = Integer.parseInt(st.nextToken());
					S[i][j] = data;
				}
			}
			min = Integer.MAX_VALUE;
			combination(0, 0, 0);
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		br.close();
		System.out.println(sb);
	}

	private static void combination(int cnt, int start, int flag) {
		if (cnt == n / 2) {
			sumMethod(flag);
			return;
		}
		for (int i = start; i < n; i++) {
			combination(cnt + 1, i + 1, flag | 1 << i);
		}
	}

	private static void sumMethod(int flag) {
		int sumA = 0, sumB = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if ((flag & 1 << i) != 0 && (flag & 1 << j) != 0) {
					sumA += S[i][j] + S[j][i];
				} else if ((flag & 1 << i) == 0 && (flag & 1 << j) == 0) {
					sumB += S[i][j] + S[j][i];
				}
			}
		}
		min = Math.min(min, Math.abs(sumA - sumB));
	}

}