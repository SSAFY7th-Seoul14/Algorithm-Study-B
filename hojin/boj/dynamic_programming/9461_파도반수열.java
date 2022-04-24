import java.io.*;

public class BOJ9461_파도반수열 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		long[] dp = new long[101];
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		dp[4] = 2;
		dp[5] = 2;
		for (int i = 6; i <= 100; ++i) {
			dp[i] = dp[i - 5] + dp[i - 1];
		}
		for (int tc = 0; tc < T; ++tc) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp[n]).append('\n');
		}
		System.out.println(sb);
		br.close();
	}

}
