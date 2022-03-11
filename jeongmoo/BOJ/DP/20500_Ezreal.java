import java.util.Scanner;

// 백준20500번. Ezreal 여눈부터 가네 ㅈㅈ
public class BOJ20500_Ezreal {	
	// 순열로 풀면 무조건 시간초과
	// 15의 배수. 3의 배수 + 5의 배수.
	// 5의배수: 끝자리가 5나 0이어야 함.
	// 3의배수: 각 자리 수의 합이 3의배수 여야함.
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		long dp[][] = new long[n][3];
		
		// 끝자리수가 1인 경우는 제외한다. (5의 배수여야 하므로)
		// 한자리수면 5밖에 없으므로 3으로 나눈 나머지가 아래와 같음
		dp[0][0] = 0;
		dp[0][1] = 0;
		dp[0][2] = 1;
		
		// 두자리수면 15, 55.
		if (1 < n) {
			dp[1][0] = 1;
			dp[1][1] = 1;
			dp[1][2] = 0;
		}
	
		// n자리수면 n-1자리수 앞에 1이나 5를 붙이는 것
		// 이전수 나머지 0 -> 1,5 -> 다음수 나머지 1,2
		// 이전수 나머지 1 -> 2,6 -> 다음수 나머지 0,2
		// 이전수 나머지 2 -> 3,7 -> 다음수 나머지 0,1
		// 즉 다음수(현재) 나머지 0이 되는 경우는 이전수가 1,2인 경우를 더해주면 된다. 같은 방식 
		int mod = 1000000007;
		for (int i = 2; i < n; i++) {
			dp[i][0] = (dp[i-1][1] + dp[i-1][2]) % mod;
			dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % mod;
			dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % mod;
		}
		
		System.out.println(dp[n-1][0]);
	}
}
