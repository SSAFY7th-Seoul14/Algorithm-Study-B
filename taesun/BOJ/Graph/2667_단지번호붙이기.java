import java.io.*;
import java.util.*;


public class Main {
	
	static int n,k;
	static int ans;
	static int arr[][] = new int[101][101];
	static int apart_num;
	static int apart[] = new int[1001];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	
	
	static void dfs(int x, int y) 
	{
		short flag = 0;
		arr[x][y] = apart_num + 2;

		if (apart[apart_num] == 0)
			apart[apart_num]++;

		for (int i = 0; i < 4; i++)
		{
			if ((x + dx[i] >= 0 && x + dx[i] <= n - 1) && (y + dy[i] >= 0 && y + dy[i] <= n - 1))
			{
				if (arr[x + dx[i]][y + dy[i]] == 1)
				{
					apart[apart_num]++;
					dfs(x + dx[i], y + dy[i]);
				}
			}
		}
		return;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		int input;
		int sx = -1, sy = -1;
		for (int i = 0; i < n; i++)
		{
			String s = scan.next();
			for (int j = 0; j < s.length(); j++)
			{
				char c = s.charAt(j);
				if (c == '1')
					arr[i][j] = 1;
			}
		}
		for (int i=0; i<n; i++)
		{
			for (int j=0; j<n; j++)
			{
				if (arr[i][j] == 1)
				{
					dfs(i, j);
					apart_num++;
				}
			}
		}
		Arrays.sort(apart);
		System.out.println(apart_num);
		for (int i=0; i<=1000; i++)
		{
			if (apart[i] == 0)
				continue;
			System.out.println(apart[i]);
		}
	}
}