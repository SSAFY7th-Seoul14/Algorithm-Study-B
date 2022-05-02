import java.util.*;
import java.io.*;

public class BOJ17404_RGB거리2 {
	static int INF = 1000 * 1000 + 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = stoi(br.readLine());
		int[][] rgb = new int[n + 1][3];
		for (int i = 1; i <= n; ++i) {
			st = new StringTokenizer(br.readLine());
			rgb[i] = new int[] { stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()) };
		}
		int[][] dp = new int[n + 1][3];
		int min = INF;
		for (int i = 0; i < 3; ++i) {
			Arrays.fill(dp[1], INF);
			dp[1][i] = rgb[1][i];
			for (int j = 2; j <= n; ++j)
				for (int k = 0; k < 3; ++k)
					dp[j][k] = rgb[j][k] + Math.min(dp[j - 1][(k + 1) % 3], dp[j - 1][(k + 2) % 3]);
			for (int j = 0; j < 3; ++j)
				if (j != i)
					if (min > dp[n][j])
						min = dp[n][j];
		}
		System.out.println(min);
		br.close();
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
