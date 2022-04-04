import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 11726번. 2xn 타일링
public class BOJ11726_Tiling {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n+2]; // 1인 경우에도 초과 안나게 그냥 +2로 설정
		dp[1] = 1; // |
		dp[2] = 2; // || =
		// dp[3] = 3; // ||| =| |=
		// 이게 사실은 2번뒤에 |를 붙이는 경우 + 1번 뒤에 =를 붙이는 경우인 것. ||를 붙이는 경우는 중복된다.
		for (int i = 3; i <= n; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 10007;
		}
		System.out.println(dp[n]);
	}
}