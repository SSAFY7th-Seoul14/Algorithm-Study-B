import java.io.*;
import java.util.*;

class pair{
	int x,y,z,w;
	pair(int x, int y, int z, int w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
}


public class Main {
	
	static int w, h, ans;
	static int arr[][] = new int[101][101];
	static int visit[][] = new int[101][101];
	static int dx[] = { -1,0,1,0 };
	static int dy[] = { 0,1,0,-1 };
	static int sx, sy, ex, ey;
	static Queue <pair> q = new LinkedList<pair>();
	
	static void bfs()
	{
		visit[sx][sy] = 0;
		q.add(new pair(sx, sy, -1, -1));
		
		int x, y, c, d, nx, ny;
		while (!q.isEmpty())
		{
			x = q.peek().x;
			y = q.peek().y;
			c = q.peek().z;
			d = q.peek().w;
			q.poll();
			for (int i = 0; i < 4; i++)
			{
				nx = x + dx[i];
				ny = y + dy[i];
				int temp = c;
				if (nx < 0 || ny < 0 || nx >= h || ny >= w)
					continue;
				if (arr[nx][ny] == -1)
					continue;
				if (i != d)
					temp++;
				if (visit[nx][ny] >= temp) 
				//여기가 핵심, 경로가 겹치더라도 내가 더 작으면 지나가야됨
				{
					visit[nx][ny] = temp;
					q.add(new pair( nx, ny, temp, i ));
				}
			}
		}
		return;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[] = br.readLine().split(" ");
		w = Integer.parseInt(s[0]);
		h = Integer.parseInt(s[1]);
		
		char c;
		int cnt = 0;
		for (int i=0; i<h; i++)
		{
			String temp = br.readLine();
			for (int j=0; j<w; j++)
			{
				c = temp.charAt(j);
				if (c == 'C'&& cnt == 0)
				{
					arr[i][j] = -2;
					sx = i; 
					sy = j;
					cnt++;
				}
				else if (c == 'C' && cnt == 1)
				{
					arr[i][j] = -2;
					ex = i; 
					ey = j;
				}
				else if (c == '*')
					arr[i][j] = -1;
				visit[i][j] = Integer.MAX_VALUE;
			}
		}
		bfs();
		ans = visit[ex][ey];
		System.out.println(ans);
	}
}
