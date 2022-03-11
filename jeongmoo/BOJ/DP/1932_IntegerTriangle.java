import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1932번. 정수 삼각형
public class BOJ1932_IntegerTriangle {
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		
		int[][] data = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 처리
		int[][] dp = new int[n][n];
		dp[0][0] = data[0][0];
		
		if (1 < n) {
			dp[1][0] = dp[0][0] + data[1][0];
			dp[1][1] = dp[0][0] + data[1][1];
		}
		
		for (int i = 2; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0)
					dp[i][j] = dp[i-1][j] + data[i][j];
				else if (j == i)
					dp[i][j] = dp[i-1][j-1] + data[i][j];
				else
					dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + data[i][j];
			}
		}
		
		// 출력
		int max = 0;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, dp[n-1][i]);
		}
		
		System.out.println(max);
	}
}
