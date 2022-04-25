import java.io.*;

public class BOJ5582_공통부분문자열 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str1st = br.readLine().toCharArray();
		char[] str2nd = br.readLine().toCharArray();
		int len1st = str1st.length;
		int len2nd = str2nd.length;
		int[][] dp = new int[len1st + 1][len2nd + 1];
		int max = 0;
		for (int i = 1; i <= len1st; ++i) {
			for (int j = 1; j <= len2nd; ++j) {
				if (str1st[i - 1] == str2nd[j - 1])
					dp[i][j] = dp[i - 1][j - 1] + 1;
				max = Math.max(max, dp[i][j]);
			}
		}
		System.out.println(max);
		br.close();
	}

}
