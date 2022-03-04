import java.io.*;
import java.util.*;

class pair{
	int first, second;
	public pair(int x,int y) {
		this.first = x;
		this.second = y;
	}
}

public class Main {
	static int m, n, r, c , d;
	static int arr[][] = new int[101][101];
	static int ans;
	static int dx[] = {0,0,-1,1};
	static int dy[] = {1,-1,0,0};
	static Queue <pair> q = new LinkedList<pair>();
	static void bfs()
	{
		int x, y;
		while (!q.isEmpty())
		{
			x = q.peek().first;
			y = q.peek().second;
			arr[x][y] = 2; // 청소함 
			if (d == 0)  // 현재 방향 북 
			{
				int count = 0;
				for (int i = 0; i < 4; i++)
				{
					if ((x + dx[i] >= 0 && x + dx[i] <= n - 1) && (y + dy[i] >= 0 && y + dy[i] <= m - 1))
					{
						if (arr[x + dx[i]][y + dy[i]] != 0)
							count++;
					}
				}
				if (count == 4)
				{
					if (x + 1 <= n - 1) // c의 경우 
					{
						if (arr[x + 1][y] != 1)
						{
							q.poll();
							q.add(new pair(x + 1, y));
							continue;
						}
						else  // d의 경우 
						{
							return;
						}
					}
				}

				if (y - 1 >= 0) 
				{
					if (arr[x][y - 1] == 0) // a의 경우
					{
						d = 3; 
						q.poll();
						q.add(new pair(x, y - 1));
						ans++;
						continue;
					}
					else // b의 경우 
					{
						d = 3;
						q.poll();
						q.add(new pair(x, y));
						continue;
					}
				}
			}
			else if (d == 1)
			{
				int count = 0;
				for (int i = 0; i < 4; i++)
				{
					if ((x + dx[i] >= 0 && x + dx[i] <= n - 1) && (y + dy[i] >= 0 && y + dy[i] <= m - 1))
					{
						if (arr[x + dx[i]][y + dy[i]] != 0)
							count++;
					}
				}
				if (count == 4)
				{
					if (y - 1 >= 0)
					{
						if (arr[x][y - 1] != 1)
						{
							q.poll();
							q.add(new pair(x, y - 1));
							continue;
						}
						else  // d의 경우 
						{
							return;
						}
					}
				}
				if (x - 1 >= 0)
				{
					if (arr[x - 1][y] == 0) // a의 경우
					{
						d = 0;
						q.poll();
						q.add(new pair(x - 1, y));
						ans++;
						continue;
					}
					else // b의 경우 
					{
						d = 0;
						q.poll();
						q.add(new pair(x, y));
						continue;
					}
				}
				
			}
			else if (d == 2)
			{
				int count = 0;
				for (int i = 0; i < 4; i++)
				{
					if ((x + dx[i] >= 0 && x + dx[i] <= n - 1) && (y + dy[i] >= 0 && y + dy[i] <= m - 1))
					{
						if (arr[x + dx[i]][y + dy[i]] != 0)
							count++;
					}
				}
				if (count == 4)
				{
					if (x - 1 >= 0)
					{
						if (arr[x - 1][y] != 1)
						{
							q.poll();
							q.add(new pair(x - 1, y));
							continue;
						}
						else  // d의 경우 
						{
							return;
						}
					}
				}
				if (y + 1 <= m-1)
				{
					if (arr[x][y + 1] == 0) // a의 경우
					{
						d = 1;
						q.poll();
						q.add(new pair(x, y + 1));
						ans++;
						continue;
					}
					else // b의 경우 
					{
						d = 1;
						q.poll();
						q.add(new pair(x, y));
						continue;
					}
				}

			}
			else if (d == 3)
			{
				int count = 0;
				for (int i = 0; i < 4; i++)
				{
					if ((x + dx[i] >= 0 && x + dx[i] <= n - 1) && (y + dy[i] >= 0 && y + dy[i] <= m - 1))
					{
						if (arr[x + dx[i]][y + dy[i]] != 0)
							count++;
					}
				}
				if (count == 4)
				{
					if (y + 1 <= m - 1)
					{
						if (arr[x][y + 1] != 1)
						{
							q.poll();
							q.add(new pair(x, y + 1));
							continue;
						}
						else  // d의 경우 
						{
							return;
						}
					}
				}
				if (x+1 <=n-1)
				{
					if (arr[x + 1][y] == 0) // a의 경우
					{
						d = 2;
						q.poll();
						q.add(new pair(x + 1, y));
						ans++;
						continue;
					}
					else // b의 경우 
					{
						d = 2;
						q.poll();
						q.add(new pair(x, y));
						continue;
					}
				}
				
			}
			q.poll();
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		r = scan.nextInt();
		c = scan.nextInt();
		d = scan.nextInt();
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
				arr[i][j] = scan.nextInt();
		}
		q.add(new pair(r, c));
		bfs();
		System.out.println(ans + 1);
	}
}
