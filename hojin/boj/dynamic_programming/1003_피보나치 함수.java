import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ1003_피보나치함수 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[][] dp = new int[41][2];
		dp[0][0] = 1;
		dp[1][1] = 1;
		for (int i = 2; i <= 40; ++i) {
			dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
			dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
		}
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; ++tc) {
			int n = Integer.parseInt(br.readLine());
			bw.write(String.format("%d %d%n", dp[n][0], dp[n][1]));
		}
		bw.flush();
		bw.close();
		br.close();
	}

}