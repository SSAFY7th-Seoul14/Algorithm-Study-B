import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 2133번. 타일 채우기
public class BOJ2133_TileFill {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[31]; 
		dp[2] = 3; // 3가지 기본단위.

		// 빈칸이 있는 경우를 모두 생각했었는데. 빈칸이 있으면 안 되는 문제인것 같다.
		// 즉 홀수번째는 만들 수 있는 경우의 수가 모두 0이다.
		
		dp[4] = 11;
		// 4번째의 경우 2번째 모양 뒤에 2번째 모양 3가지를 다 더하는데. 
		// 끝이 |이고고 중간에 ==가 들어간 고유패턴이 위 아래로 2개 더 나올 수 있어. 3x3 + 2 = 11가지가 된다.
		
		// 이게 이해가 어려운데 2=2, 4=2+2(2개에 2개를 붙임. dp[2]*dp[2]) + 고유패턴 2개 까지는 위에 말한대로고
		// 6 = 4+2(dp[4]*dp[2]) +  2+4(dp[2]*dp[4]) + 고유패턴 2개
		// 8 = 6+2, 4+4, 2+6 + 고유패턴 2개

		// 여기서 dp[2]가 3개인데. 이 것이 기본 단위로 보고 dp[i] = dp[i-2]*3을 베이스로 잡는다.
		// 그리고 뒤에 경우에서 앞에서 중복되지 않는건 각각의 고유패턴 2개씩 밖에 없으므로
		// dp[i-4], dp[i-6], dp[i-8], ... 까지는 x 2를 해서 더해준다. (고유 모양만. 위 아래반전해서 2개)

		for (int i = 6; i <=n; i+=2) {
			dp[i] = dp[i-2]*3;  // 이전 모양에 2개블럭을 붙여 만드는 새로운 모양
			
			// 중복되지 않는 이전 모양들의 고유패턴을 생각하여 전부 x2를 해서 더해줌.
			for (int j = i-4; j > 0; j-=2) {
				dp[i] += dp[j]*2;
			}
			
			// 현재 모양의 고유패턴 2개
			dp[i] += 2;
		}
		System.out.println(dp[n]);
	}
}