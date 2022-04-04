import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2133_타일채우기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[31];
		if (n % 2 == 1) {
			System.out.println(0);
		} else {
			dp[0] = 1;
			dp[2] = 3;
			for (int i = 4; i <= n; i += 2) {
				dp[i] = dp[i - 2] * 3;
				for (int j = i - 4; j >= 0; j -= 2) {
					dp[i] += dp[j] * 2;
				}
			}
			System.out.println(dp[n]);
		}
		br.close();
	}

}