import java.io.*;
import java.util.*;

class pair {
	int first,second;
	public pair(int x, int y)
	{
		this.first = x;
		this.second = y;
	}
}
public class Main {
	static int ans, n, m;
	static int arr[][] = new int[1010][1010];
	static int dx[] = { 0,0,-1,1 };
	static int dy[] = { -1,1,0,0 };
	
	static Queue <pair> q = new LinkedList<pair>();
	static void bfs()
	{
		int x = 0, y = 0 ,nx , ny;
		int temp;
		while (!q.isEmpty())
		{
			x = q.peek().first;
			y = q.peek().second;
			q.poll();
			for (int i = 0; i < 4; i++)
			{
				nx = x + dx[i];
				ny = y + dy[i];
				if ((nx >= 0 && nx <= m - 1) && (ny >= 0 && ny <= n - 1))
				{
					if (arr[nx][ny] == 0)
					{
						arr[nx][ny] = arr[x][y] + 1;
						q.add(new pair(nx, ny));
					}
				}
			}
		}
		ans = arr[x][y];
		ans--;
		return;
	}
	static void find()
	{
		int cnt = 0;
		// 처음 시작할때 큐에 동시에 집어넣기 
		for (int i = 0; i < m; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (arr[i][j] == 1)
				{
					q.add(new pair(i, j));
					cnt++;
				}
			}
		}
		if(cnt > 0)
			bfs();
		return;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		m = scan.nextInt();
		n = scan.nextInt();
		for (int j = 0; j < n; j++)
		{
			for (int i = 0; i < m; i++)
				arr[i][j] = scan.nextInt();
		}
		find();
		for (int i = 0; i < m; i++) // 만약 다 끝나고 안익은게 있는지 확인  
		{
			for (int j = 0; j < n; j++)
			{
				if (arr[i][j] == 0)
				{
					ans = -1;
					break;
				}
			}
		}
		System.out.println(ans);
	}	
}
