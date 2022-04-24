import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 5582번. 공통 부분 문자열
public class BOJ5582_CommonSubstring {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();
		
		int[][] dp = new int[str1.length+1][str2.length+1];
		
		int max = 0;
		for (int i = 0; i < str1.length; i++) {
			for (int j = 0; j < str2.length; j++) {
				if (str1[i] == str2[j]) {
					if (i==0 || j==0)
						dp[i][j] = 1;
					else
						dp[i][j] = dp[i-1][j-1] + 1;
					
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		
		System.out.println(max);
	}
}
