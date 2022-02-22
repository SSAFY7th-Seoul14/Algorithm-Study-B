import java.io.*;
import java.util.*;

class pair{
	int first,second;
	public pair(int x, int y) {
		this.first = x;
		this.second = y;
	}
}
public class Main {
	static int ans, n, l, r;
	static int arr[][] = new int[51][51];
	// 어떤 국가끼리 합쳐졌는지 체크용
	static int union[][] = new int[51][51];
	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1,0,0};
	static Queue <pair> q = new LinkedList<pair>();
	static void bfs()
	{
		int x, y , diff, nx, ny;
		while (!q.isEmpty())
		{
			x = q.peek().first;
			y = q.peek().second;
			q.poll();
			for (int i = 0; i < 4; i++)
			{
				nx = x + dx[i];
				ny = y + dy[i];
				if (nx >= 0 && nx <= n - 1 && ny >= 0 && ny <= n - 1)
				{
					diff = Math.abs(arr[x][y] - arr[nx][ny]);
					if (diff >= l && diff <= r)
					{
						if (union[nx][ny] == 0)
						{
							union[nx][ny] = union[x][y];
							q.add(new pair(x + dx[i], y + dy[i]));
						}
					}
				}
			}
		}
	}
	
	static int find()
	{
		int diff, mark = 0, nx, ny;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				// 현재 위치에서 상하좌우를 보면서 인구수 차이를 계산한다
				for (int k = 0; k < 4; k++)
				{
					nx = i + dx[k];
					ny = j + dy[k];
					if (nx < 0 || nx >= n || ny < 0 || ny >= n)
						continue;
					diff = Math.abs(arr[i][j] - arr[nx][ny]);
					// 인구차이가 조건을 만족할때 
					if (diff >= l && diff <= r)
					{
						if (union[i][j] == 0)
						{
							mark++;
							union[i][j] = mark;
							q.add(new pair(i, j));
							bfs();
						}
					}
				}
			}
		}
		int sum[] = new int[mark + 1];
		int cnt[] = new int[mark + 1];
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				for (int k = 0; k < mark; k++)
				{
					if (union[i][j] == k + 1)
					{
						sum[k] += arr[i][j];
						cnt[k]++;
					}
				}
			}
		}
		// 인구수를 구역만큼 나눠주고 갱신 
		for (int i = 0; i < mark; i++)
			sum[i] /= cnt[i];
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				for (int k = 0; k < mark; k++)
				{
					if (union[i][j] == k + 1)
					{
						union[i][j] = 0;
						arr[i][j] = sum[k];
					}
				}
			}
		}
		return mark;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		l = scan.nextInt();
		r = scan.nextInt();
		for (int i=0; i<n; i++)
		{
			for (int j=0; j<n; j++)
				arr[i][j] = scan.nextInt();
		}
		int temp;
		while (true)
		{
			temp = find();
			if (temp > 0)
				ans++;
			else
				break;
		}
		System.out.println(ans);
	}	
}
