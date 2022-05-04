import java.io.*;
import java.util.*;

public class BOJ1932_정수삼각형 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = stoi(br.readLine());
		int[][] triangle = new int[n + 1][n + 1];
		for (int i = 1; i <= n; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= i; ++j) {
				triangle[i][j] = stoi(st.nextToken());
			}
		}
		int[][] dp = new int[n + 1][n + 1];
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= i; ++j) {
				dp[i][j] = triangle[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
			}
		}
		int max = 0;
		for (int i = 1; i <= n; ++i) {
			max = Math.max(dp[n][i], max);
		}
		System.out.println(max);
		br.close();
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
