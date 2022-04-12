import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2294번. 동전 2
public class BOJ2294_Coin2 {
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coin = new int[n];
		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[k+1];
		Arrays.fill(dp, Integer.MAX_VALUE-1); // max값이면 아래 dp테이블 갱신하면서 +1해서 오버플로우 나면서 min값이 갱신되버림.
		dp[0] = 0;
		
		// 1원부터 k원까지 갱신
		for (int i = 1; i <= k; i++) {
			// 코인의 종류만큼 체크
			for (int j = 0; j < n; j++) {
				if (coin[j] <= i) { // 동전금액보다 현재가 더 크거나 같을때만 비교
					dp[i] = Math.min(dp[i], dp[i-coin[j]]+1);
				}
			}
		}
		
		// 출력
		if (dp[k] == Integer.MAX_VALUE-1)
			System.out.println("-1");
		else
			System.out.println(dp[k]);
	}
}
