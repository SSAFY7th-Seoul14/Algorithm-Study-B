import java.io.*;
import java.util.*;


public class Main {
	
	static int n,k, m;
	static int ans;
	static int arr[][] = new int[1025][1025];
	static int sum[][] = new int[1025][1025];
	static int sum2[][] = new int[1025][1025];
	static int dp[][] = new int[1025][1025];
	
	
	// dp랑 누적합 안쓰면 시간초과 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[] = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		for (int i=1; i<=n; i++)
		{
			s = br.readLine().split(" ");
			for (int j=0; j<n; j++)
				arr[i][j+1] = Integer.parseInt(s[j]);
		}
		int s1 = 0, s2 = 0;
		for (int i = 1; i <= n; i++)
		{
			for (int j = 1; j <= n; j++)
			{
				s1 += arr[i][j];
				s2 += arr[j][i];
				sum2[j][i] = s2;
				sum[i][j] = s1;
			}
		}
		
		for (int i = 1; i <= n; i++)
		{
			dp[1][i] = sum[1][i];
			dp[i][1] = sum2[i][1];
		}
		// dp[i][j] = (1, 1)부터 (i,j)까지 구간합을 의미 
		// 점화식은 직접 해보면서 구하는게 빠름 
		for (int i = 2; i <= n; i++)
		{
			for (int j = 2; j <= n; j++)
				dp[i][j] = dp[i][j - 1] - dp[i - 1][j - 1] + dp[i-1][j] + arr[i][j];
		}
		// 총 나올 수 있는 경우는 4가지 
		// 각 경우마다 맞는 식을 적용해서 값을 구한다
		StringBuilder st = new StringBuilder();
		for (int i = 0; i < m; i++)
		{
			s = br.readLine().split(" ");
			int x = Integer.parseInt(s[0]);
			int y = Integer.parseInt(s[1]);
			int x2 = Integer.parseInt(s[2]);
			int y2 = Integer.parseInt(s[3]);
			int temp = 0;
			// 
			if (x != x2 && y != y2)
				temp = dp[x2][y2] - dp[x2][y - 1] - dp[x - 1][y2] + dp[x - 1][y - 1];
			// 같은 열에 있을때
			else if (x == x2 && y != y2)
				temp = sum[x][y2] - sum[x][y] + arr[x][y];
			// 같은 행에 있을때
			else if (x != x2 && y == y2)
				temp = sum2[x2][y2] - sum2[x][y] + arr[x][y];
			// 일치할때
			else if (x == x2 && y == y2)
				temp = arr[x][y];
			st.append(temp);
			st.append('\n');
		}
		System.out.print(st);
	}
}