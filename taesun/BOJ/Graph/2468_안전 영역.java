import java.io.*;
import java.util.*;

class pair{
	int first,second;
	pair(int first, int second){
		this.first = first;
		this.second = second;
	}
}
public class Main {
	
	static int n,k;
	static int ans;
	static int arr[][] = new int[101][101];
	static boolean visit[][] = new boolean[101][101];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static Queue <pair> q = new LinkedList<pair>();
	static void clear()
	{
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
				visit[i][j] = false;
		}
		return;
	}
	static void bfs(int h)
	{
		int x, y, nx, ny;
		while (!q.isEmpty())
		{
			x = q.peek().first;
			y = q.peek().second;
			for (int i = 0; i < 4; i++)
			{
				nx = x + dx[i];
				ny = y + dy[i];
				if (nx < 0 || nx > n || ny < 0 || ny > n)
					continue;
				if (arr[nx][ny] > h && !visit[nx][ny])
				{
					visit[nx][ny] = true;
					q.add(new pair(nx, ny));
				}
			}
			q.poll();
		}
		return;
	}
	static int find(int h)
	{
		int cnt = 0;
		clear();
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (arr[i][j] > h && !visit[i][j])
				{
					visit[i][j] = true;
					cnt++;
					q.add(new pair(i, j));
					bfs(h);
				}
			}
		}
		return cnt;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		int d = 0;
		for (int i=0; i<n; i++)
		{
			for (int j=0; j<n; j++)
			{
				arr[i][j] = scan.nextInt();
				d = Math.max(arr[i][j], d);
			}
		}
		for (int i=0; i<d; i++)
			ans = Math.max(ans, find(i));
		System.out.println(ans);
	}
}