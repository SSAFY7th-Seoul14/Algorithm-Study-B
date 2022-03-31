import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 11057번. 오르막 수
public class BOJ11057_ClimbingNumber {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// 기존 수 앞자리(왼쪽)에 수를 더한다고 생각.
		long[][] dp = new long[1001][10];
		// 1자리수는 모든 경우가 가능하고 1가지 경우만 있음.
		for (int i = 0; i < 10; i++) {
			dp[1][i] = 1;
		}
		
		// 2자리수부터 00~09, 11~19, ... 10+9+8+...+1 = 55
		// 3자리수부터 000~009, 111~110, ... 55+45+36+...+1 = 220
		
		for (int i = 2; i <= n; i++) {
			// 앞에 0을 둔다고 하면 이전자리 0~9까지의 합
			// 앞에 1을 둔다고 하면 이전자리 1~9까지의 합 ...
			for (int j = 0; j < 10; j++) {
				long num = 0;
				for (int k = j; k < 10; k++) {
					num += dp[i-1][k];
				}
				dp[i][j] = num % 10007;
			}
		}
		
		long result = 0;
		for (int i = 0; i < 10; i++) {
			result += dp[n][i];
		}
		System.out.println(result%10007);
	}
}