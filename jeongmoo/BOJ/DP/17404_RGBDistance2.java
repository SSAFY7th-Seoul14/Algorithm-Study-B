import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 17404번. RGB거리2
public class BOJ17404_RGBDistance2 {
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
		
		// 시작 색을 고정하고 끝까지 가서 최소값을 갱신
		int result = Integer.MAX_VALUE;
		for (int j = 0; j < 3; j++) {
			// 첫 색 지정
			for (int i = 0; i < 3; i++) {
				if (i==j)
					dp[0][i] = data[0][j]; // 첫색을 무조건 j로 설정한다
				else // j가 아닌 다른색은 이번에 선택하지 못하도록 한다.
					dp[0][i] = 1000001;		
			}
			
			// rgb
			for (int i = 1; i < n; i++) {
				dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + data[i][0]; 
				dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + data[i][1]; 
				dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + data[i][2]; 
			}
			
			for (int i = 0; i < 3; i++) {
				if (i == j) continue; // 시작과 끝색이 같으면 제외
				result = Math.min(result, dp[n-1][i]);
			}
		}
		System.out.println(result);
	}
}