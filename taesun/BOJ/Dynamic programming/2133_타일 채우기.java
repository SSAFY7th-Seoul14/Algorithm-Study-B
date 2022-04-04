import java.io.*;
import java.util.*;

public class Main {
	
	static int n, k, ans;
	static int cache[] = new int[31];
	
	// 점화식 규칙 생각하기가 어려웠던 문제 
	// dp[n] = dp[n-2] * 3 은 금방 찾을 수 있지만 
	// 여기에 추가적으로 짝수 번째마다 새로운 패턴이 2개씩 생긴다 
	// dp[4] = 3 * 3 + 2;
	// 따라서 dp[n] = dp[n-2] * 3 + dp[n - 4] * 2 + ......
	static int dp(int idx)
	{
		if (idx <= 0)
			return 0;
		if (cache[idx] != -1)
			return cache[idx];
		int ret = dp(idx - 2) * 3;
		int temp = idx - 2;
		while (temp > 0)
		{
			temp -= 2;
			ret += dp(temp) * 2;
		}
		return cache[idx] = ret + 2;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		if (n % 2 == 1)
			ans = 0;
		else
		{
			for (int i=0; i<=30; i++)
				cache[i] = -1;
			cache[0] = 0;
			cache[2] = 3;
			ans = dp(n);
		}
		System.out.println(ans);
	}
}