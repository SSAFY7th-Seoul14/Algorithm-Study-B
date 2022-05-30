import java.util.*;
import java.io.*;

public class BOJ10942_팰린드롬 {
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = stoi(br.readLine());
		int[] blackboard = new int[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; ++i) {
			blackboard[i] = stoi(st.nextToken());
		}
		dp = new int[n + 1][n + 1];
		for (int i = 1; i <= n; ++i) {
			Arrays.fill(dp[i], -1);
		}
		for (int i = 1; i <= n; ++i) {
			for (int j = i; j <= n; ++j) {
				dp[i][j] = isPal(blackboard, i, j);
			}
		}
		int m = stoi(br.readLine());
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int s = stoi(st.nextToken());
			int e = stoi(st.nextToken());
			sb.append(dp[s][e]).append('\n');
		}
		System.out.println(sb);
		br.close();
	}

	private static int isPal(int[] blackboard, int s, int e) {
		if (s == e)
			return dp[s][e] = 1;
		else if (e - 1 == s)
			return dp[s][e] = blackboard[s] == blackboard[e] ? 1 : 0;
		if (dp[s][e] > -1)
			return dp[s][e];
		dp[s][e] = isPal(blackboard, s + 1, e - 1) == 0 ? 0
				: dp[s + 1][e - 1] * (blackboard[s] == blackboard[e] ? 1 : 0);
		return dp[s][e];
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}