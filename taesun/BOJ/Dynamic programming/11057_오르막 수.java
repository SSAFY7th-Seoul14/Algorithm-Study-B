import java.io.*;
import java.util.*;


public class Main {
	
	static int n,k, m, ans;
	static int cache[][] = new int[1001][10];
	static int div = 10007;
	// 2차원 dp를 사용, 점화식은 dp[n][num] = dp[n-1][num보다 작거나 같은 수] 들의 총합
	static int dp(int idx, int num)
	{
		if (idx <= 1)
			return 1;
		if (cache[idx][num] != -1)
			return cache[idx][num];
		int ret = 0;
		for (int i=0; i<10; i++)
		{
			if (i <= num)
				ret = (ret + dp(idx - 1, i)) % div;
		}
		return cache[idx][num] = ret;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		for (int i=0; i<=1000; i++)
		{
			for (int j=0; j<10; j++)
				cache[i][j] = -1;
		}
		for (int i=0; i<10; i++)
			ans = (ans + dp(n, i)) % div;
		System.out.print(ans);
	}
}