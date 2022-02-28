import java.io.*;
import java.util.*;

class pair {
	int x,y,z,w;
	public pair(int x, int y, int z, int w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
}
public class Main {
	static int ans = Integer.MAX_VALUE, n, m;
	static int arr[][] = new int[1010][1010];
	static boolean visit[][][] = new boolean[1010][1010][2];
	static int dx[] = { 0,0,-1,1 };
	static int dy[] = { -1,1,0,0 };
	
	static Queue <pair> q = new LinkedList<pair>();
	
	static void print()
	{
		System.out.println();
		for (int i = 0; i < n; i++)
		{
			for (int j=0; j<m; j++)
				System.out.print(arr[i][j] + " ");
			System.out.println();
		}
	}
	static void bfs(int sx, int sy)
	{
		int x,y,d,c,nx,ny;
		int temp;
		
		// 그럴일은 없겠지만 시작위치에 벽이 있을 경우도 고려 
		visit[sx][sy][arr[sx][sy]] = true;
		q.add(new pair (sx, sy, 0, arr[sx][sy]));
		while (!q.isEmpty())
		{
			x = q.peek().x;
			y = q.peek().y;
			// 현재 노드까지 최단 거리 
			d = q.peek().z;
			// 현재 노드까지 부순 벽의 수 
			c = q.peek().w;
			if (x == n - 1 && y == m - 1)
			{
				ans = Math.min(ans, d);
				return;
			}
			q.poll();
			for (int i = 0; i < 4; i++)
			{
				nx = x + dx[i];
				ny = y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m)
					continue;
				if (arr[nx][ny] == 0 && !visit[nx][ny][c])
				{
					visit[nx][ny][c] = true;
					q.add(new pair (nx, ny , d+1, c));
				}
				else if (arr[nx][ny] == 1 && c == 0 && !visit[nx][ny][c + 1])
				{
					visit[nx][ny][c + 1] = true;
					q.add(new pair (nx, ny , d+1, c + 1));
				}
			}
		}
		
		return;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		for (int i = 0; i < n; i++)
		{
			String s = scan.next();
			for (int j=0; j<m; j++)
				arr[i][j] = s.charAt(j) - '0';
		}
		bfs(0,0);
		if (ans == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ans + 1);
	}	
}
