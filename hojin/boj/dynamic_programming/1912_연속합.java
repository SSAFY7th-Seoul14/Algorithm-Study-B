import java.util.*;
import java.io.*;

public class BOJ1912_연속합 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] dp = new int[n];
		dp[0] = stoi(st.nextToken());
		int max = dp[0];
		for (int i = 1; i < n; ++i) {
			int inp = stoi(st.nextToken());
			dp[i] = Math.max(dp[i - 1] + inp, inp);
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
		br.close();
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
