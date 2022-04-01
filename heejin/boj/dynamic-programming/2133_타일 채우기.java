import java.io.*;

// BOJ / 타일 채우기 / G5 / 1시간
// https://www.acmicpc.net/problem/2133
// 참고: https://yabmoons.tistory.com/536
public class Main_2133 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int dp[] = new int[N + 1];

		for (int i = 1; i < N + 1; i = i + 2) // 홀수일 땐 불가능
			dp[i] = 0;

		dp[0] = 1; // 아무것도 없는 경우 1가지

		if (N >= 2)
			dp[2] = 3;

		for (int i = 4; i < N + 1; i = i + 2) {
			dp[i] = dp[i - 2] * dp[2];
			for (int j = 4; j <= i; j = j + 2) {
				dp[i] += dp[i - j] * 2; // 특별한 블록 2가지
			}
		}

		System.out.println(dp[N]);
	}
}
