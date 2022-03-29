import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ1463_1로만들기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n + 1];
		dp[1] = 0;
		for (int i = 2; i <= n; ++i) {
			if (i % 3 == 0 && i % 2 == 0) {
				dp[i] = Math.min(Math.min(dp[i / 3], dp[i / 2]), dp[i - 1]) + 1;
			} else if (i % 3 == 0) {
				dp[i] = Math.min(dp[i / 3], dp[i - 1]) + 1;
			} else if (i % 2 == 0) {
				dp[i] = Math.min(dp[i / 2], dp[i - 1]) + 1;
			} else {
				dp[i] = dp[i - 1] + 1;
			}
		}
		bw.write(String.valueOf(dp[n]));
		bw.flush();
		bw.close();
		br.close();
	}
}