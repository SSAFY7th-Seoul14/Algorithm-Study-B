import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 9465번. 스티커
public class BOJ9465_Sticker {
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < tc; i++) {
			int n = Integer.parseInt(br.readLine());
			int[][] data = new int[n][2];
			
			// 입력
			for (int j = 0; j < 2; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < n; k++) {
					data[k][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] dp = new int[n][2]; // 위 선택, 아래 선택
			dp[0][0] = data[0][0];
			dp[0][1] = data[0][1];
			
			if (1 < n) {
				dp[1][0] = dp[0][1] + data[1][0];
				dp[1][1] = dp[0][0] + data[1][1];
			}
			
			for (int j = 2; j < n; j++) {
				// 윗줄
				int first = dp[j-1][1] + data[j][0];	// 이전(j-1)	대각선 값과 
				int second = dp[j-2][1] + data[j][0];	// 옆옆(j-2) 대각선 값을 비교 
				dp[j][0] = Math.max(first, second);
				
				// 아랫줄
				first = dp[j-1][0] + data[j][1];
				second = dp[j-2][0] + data[j][1];
				dp[j][1] = Math.max(first, second);
			}
			
			// 처리
			int result = Math.max(dp[n-1][0], dp[n-1][1]);			
			
			// 출력
			System.out.println(result);
		}
	}
}
