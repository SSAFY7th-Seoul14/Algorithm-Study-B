import java.util.*;
import java.io.*;

public class BOJ9184_신나는함수실행 {

	static StringBuilder sb = new StringBuilder();
	static int[][][] dp = new int[101][101][101];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 0; i < 101; ++i) {
			for (int j = 0; j < 101; ++j) {
				Arrays.fill(dp[i][j], Integer.MAX_VALUE);
			}
		}
		while (true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == -1 && b == -1 && c == -1)
				break;
			sb.append(String.format("w(%d, %d, %d) = %d%n", a, b, c, w(a + 50, b + 50, c + 50)));
		}
		System.out.println(sb);
		br.close();
	}

	private static int w(int a, int b, int c) {
		if (a <= 50 || b <= 50 || c <= 50) {
			return dp[a][b][c] = 1;
		}
		if (a > 70 || b > 70 || c > 70) {
			if (dp[70][70][70] == Integer.MAX_VALUE)
				dp[70][70][70] = w(70, 70, 70);
			return dp[70][70][70];
		}
		if (a < b && b < c) {
			if (dp[a][b][c - 1] == Integer.MAX_VALUE)
				dp[a][b][c - 1] = w(a, b, c - 1);
			if (dp[a][b - 1][c - 1] == Integer.MAX_VALUE)
				dp[a][b - 1][c - 1] = w(a, b - 1, c - 1);
			if (dp[a][b - 1][c] == Integer.MAX_VALUE)
				dp[a][b - 1][c] = w(a, b - 1, c);
			return dp[a][b][c - 1] + dp[a][b - 1][c - 1] - dp[a][b - 1][c];
		}
		if (dp[a - 1][b][c] == Integer.MAX_VALUE)
			dp[a - 1][b][c] = w(a - 1, b, c);
		if (dp[a - 1][b - 1][c] == Integer.MAX_VALUE)
			dp[a - 1][b - 1][c] = w(a - 1, b - 1, c);
		if (dp[a - 1][b][c - 1] == Integer.MAX_VALUE)
			dp[a - 1][b][c - 1] = w(a - 1, b, c - 1);
		if (dp[a - 1][b - 1][c - 1] == Integer.MAX_VALUE)
			dp[a - 1][b - 1][c - 1] = w(a - 1, b - 1, c - 1);
		return dp[a - 1][b][c] + dp[a - 1][b - 1][c] + dp[a - 1][b][c - 1] - dp[a - 1][b - 1][c - 1];
	}

}
