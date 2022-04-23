import java.io.*;
import java.util.*;

class pair{
	int x,y,z, w;
	pair (int x, int y, int z, int w){
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
}
public class Main {

	static int n, m, ans, w, h;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int arr[][] = new int[50][50];
	static boolean visit[][][] = new boolean[50][50][1050];
	static int cx, cy, total, max;
	
	static void bfs(int sx, int sy)
	{
		Queue <pair> q = new LinkedList<pair>();
		visit[sx][sy][0] = true;
		q.add(new pair(sx, sy, 0, 0));
		int x,y,nx,ny,bit, nbit , cnt;
		while (!q.isEmpty())
		{
			x = q.peek().x;
			y = q.peek().y;
			bit = q.peek().z;
			cnt = q.peek().w;
			q.poll();
			if (bit == max)
			{
				ans = Math.min(ans, cnt);
				continue;
			}
			for (int i=0; i<4; i++)
			{
				nx = x + dx[i];
				ny = y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m)
					continue;
				if (arr[nx][ny] == 1)
					continue;
				if (arr[nx][ny] < 0)
					nbit = bit | (1 << ((arr[nx][ny] * -1) - 1));
				else 
					nbit = bit;
				if (visit[nx][ny][nbit])
					continue;
				visit[nx][ny][nbit] = true;
				q.add(new pair(nx, ny, nbit, cnt + 1));
			}
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = m = 1;
		
		char c;
		int sx, sy;
		while (n != 0 && m != 0)
		{
			ans = Integer.MAX_VALUE;
			m = scan.nextInt();
			n = scan.nextInt();
			sx = sy = total = max = 0;
			if (n == 0 && m == 0)
				break;
			String s;
			for (int i=0; i<n; i++)
			{
				s = scan.next();
				for (int j=0; j<m; j++)
				{
					c = s.charAt(j);
					if (c == '.')
						arr[i][j] = 0;
					else if (c == 'x')
						arr[i][j] = 1;
					else if (c == '*')
					{
						total++;
						arr[i][j] = total * -1;
					}
					else if (c == 'o')
					{
						arr[i][j] = 0;
						sx = i;
						sy = j;
					}
					for (int k=0; k<=1024; k++)
						visit[i][j][k] = false;
				}
			}
			max = (1 << total) - 1;
			//System.out.println(max);
			bfs(sx, sy);
			if (ans == Integer.MAX_VALUE)
				ans = -1;
			System.out.println(ans);
		}
	}
}
