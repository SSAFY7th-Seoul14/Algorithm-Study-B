import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11401_이항계수3 {

	static long DIV = 1000000007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		long[] dp = new long[n + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= n; ++i) {
			dp[i] = ((dp[i - 1]) * i) % DIV;
		}
		long bottom = (dp[n - r] * dp[r]) % DIV;
		bottom = pow(bottom, DIV - 2);
		System.out.println((dp[n] * bottom) % DIV);
		br.close();
	}

	private static long pow(long num, long exp) {
		long answer;
		if (exp == 1) {
			return num;
		} 
		answer = pow(num, exp / 2);
		if (exp % 2 == 0) {
			return (answer * answer) % DIV;
		} else {
			return (((answer * answer) % DIV) * num) % DIV;
		}
	}

}
