import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1149번. RGB 거리
public class BOJ1149_RGBDistance {
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] data = new int[n][3];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}			
		}
		
		int[][] dp = new int[n][3]; // 2차원배열로 RGB 3가지 색상마다 각각 최소값을 저장하는게 핵심. 
		
		// 첫 색 지정
		for (int i = 0; i < 3; i++) {
			dp[0][i] = data[0][i];
		}
		
		// rgb
		for (int i = 1; i < n; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + data[i][0]; 
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + data[i][1]; 
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + data[i][2]; 
		}
		
		int result = Math.min(Math.min(dp[n-1][0], dp[n-1][1]), dp[n-1][2]);
		System.out.println(result);
	}
}