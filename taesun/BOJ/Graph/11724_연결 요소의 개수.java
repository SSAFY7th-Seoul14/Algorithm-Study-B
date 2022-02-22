import java.io.*;
import java.util.*;


public class Main {
	static int ans, n, m;
	static int arr[][] = new int[1010][1010];
	static boolean visit[] = new boolean[1010];
	static void dfs(int idx)
	{
		int next;
		for (int i = 0; i < n; i++)
		{
			if (arr[idx][i] > 0 && !visit[i])
			{
				visit[i] = true;
				dfs(i);
			}
		}
		return;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		int a,b;
		for (int i = 0; i < m; i++)
		{
			a = scan.nextInt();
			b = scan.nextInt();
			arr[a-1][b-1] = 1;
			arr[b-1][a-1] = 1;
		}
		for (int i = 0; i < n; i++)
		{
			if (!visit[i])
			{
				visit[i] = true;
				dfs(i);
				ans++;
			}
		}
		System.out.println(ans);
	}	
}
