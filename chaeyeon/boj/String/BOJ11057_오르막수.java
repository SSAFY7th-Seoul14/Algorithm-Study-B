package dp;

import java.util.Scanner;

//@
//값이 어떻게 나오는지는 알겠는데 점화식을 어떻게 세워야할지 모르겠어서 풀이 참조하였다.
//근데 아직도 잘 모르겠다....
public class BOJ11057_오르막수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[][] dp = new int[N+1][10];
		
		for(int i=0; i <= 9; i++) {
			dp[1][i] = 1;
		}
		
		for(int i=2; i<=N; i++) {
			dp[i][0] = 1;
			for(int j=1; j <= 9; j++) {
				dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % 10007 ;
			}
		}
		
		int result = 0;
		for (int i=0; i<=9; i++) {
			result += dp[N][i];
		}
		
		System.out.println(result);
	}

}
