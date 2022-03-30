import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1149_RGB거리 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] rgb = new int[n + 1][3];
		for (int i = 1; i <= n; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; ++j) {
				rgb[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] dp = new int[n + 1][3];
		for (int i = 1; i <= n; ++i) {
			for (int j = 0; j < 3; ++j) {
				dp[i][j] = rgb[i][j] + Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]);
			}
		}
		System.out.println(Math.min(Math.min(dp[n][0], dp[n][1]), dp[n][2]));
	}

}