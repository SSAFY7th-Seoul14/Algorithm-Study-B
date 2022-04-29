import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 12852번. 1로 만들기 2
public class BOJ12852_MakeItOne2 {
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x = Integer.parseInt(br.readLine());
		
		int[] dp = new int[x+1];
		dp[1] = 0; // 1에 도착하면 0번에서 bottom-up
		
		for (int i = 2; i <= x; i++) {
			// 1을 빼는 경우
			dp[i] = dp[i-1] + 1;
			
			// 2로 나눠지거나 3으로 나눠지면 현재와 비교해서 최소값으로 갱신
			if (i%2==0)
				dp[i] = Math.min(dp[i], dp[i/2]+1);
			if (i%3==0)
				dp[i] = Math.min(dp[i], dp[i/3]+1);
		}
		
		// 최솟값
		StringBuilder sb = new StringBuilder();
		sb.append(dp[x]).append("\n");
		
		// cnt가 현재보다 1작은 케이스로 계속 내려간다.
		int num = x;
		int cnt = dp[x];
		sb.append(num).append(" ");
		while (num > 1) {
			if (dp[num-1] == cnt-1) {
				num = num-1;
			} else if (num%2==0 && dp[num/2] == cnt-1) {
				num = num/2;
			} else if (num%3==0 && dp[num/3] == cnt-1) {
				num = num/3;
			}
			cnt--;
			sb.append(num).append(" ");
		}
		System.out.println(sb);
	}
}
