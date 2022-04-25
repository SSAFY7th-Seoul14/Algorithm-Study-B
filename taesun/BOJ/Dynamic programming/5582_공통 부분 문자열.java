import java.io.*;
import java.util.*;


public class Main {
	
	static int n,m, ans;
	static int arr[][] = new int[4010][4010];
	
	// 점화식 : 현재까지 최장 수열 길이 = dp[i][j]
	// dp[i][j] = dp[i-1][j-1] + 1(단 현재 문자가 일치할때)
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		String s1 = scan.next();
		String s2 = scan.next();
		
		for (int i = 0; i < s1.length(); i++)
		{
			for (int j = 0; j < s2.length(); j++)
			{
				if (s1.charAt(i) == s2.charAt(j))
					arr[i][j] = 1;
				if (i >= 1 && j >= 1)
				{
					if (arr[i - 1][j - 1] != 0 && arr[i][j] == 1)
						arr[i][j] += arr[i - 1][j - 1];
				}
				ans = Math.max(arr[i][j], ans);
			}
		}
		System.out.println(ans);
	}
}
