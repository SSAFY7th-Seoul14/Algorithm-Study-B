import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1003번. 피보나치 함수
public class BOJ1003_FibonacciFunction {
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tc; i++) {
			int n = Integer.parseInt(br.readLine());
			
			int[][] dp = new int[n+1][2]; // 0,1
			
			dp[0][0] = 1;
			dp[0][1] = 0;
			
			if (n > 0) {
				dp[1][0] = 0;
				dp[1][1] = 1;
			}
			
			for (int j = 2; j <= n; j++) {
				dp[j][0] = dp[j-1][0] + dp[j-2][0];
				dp[j][1] = dp[j-1][1] + dp[j-2][1];
			}
			
			sb.append(dp[n][0] + " " + dp[n][1] + "\n");
		}
		System.out.println(sb);
	}
}