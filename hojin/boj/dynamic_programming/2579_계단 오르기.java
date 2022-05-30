import java.io.*;

public class BOJ2579_계단오르기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = stoi(br.readLine());
		int[] stairs = new int[n];
		for (int i = 0; i < n; ++i) {
			stairs[i] = stoi(br.readLine());
		}
		int[][] dp = new int[n][3];
		dp[0][1] = stairs[0];
		dp[0][2] = stairs[0];
		if (n >= 2) {
			dp[1][0] = dp[0][1];
			dp[1][1] = stairs[1];
			dp[1][2] = dp[0][1] + stairs[1];
			for (int i = 2; i < n; ++i) {
				dp[i][0] = dp[i - 1][2];
				dp[i][1] = Math.max(dp[i - 1][0], dp[i - 2][1]) + stairs[i];
				dp[i][2] = dp[i - 1][1] + stairs[i];
			}
		}
		int max = Math.max(dp[n - 1][2], dp[n - 1][1]);
		System.out.println(max);
		br.close();
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
