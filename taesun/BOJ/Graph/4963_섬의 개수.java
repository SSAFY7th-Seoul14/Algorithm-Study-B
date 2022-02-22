import java.io.*;
import java.util.*;

class pair {
	int x,y;
	public pair(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static int ans, w = -1, h = -1;
	static int arr[][] = new int[51][51];
	static boolean visit[][] = new boolean[51][51];
	static int dx[] = { 0,0,-1,1, -1,-1 ,1,1};
	static int dy[] = { -1,1,0,0 , -1, 1,-1,1};
	

	static void clear()
	{
		ans = 0;
		for (int i = 0; i < h; i++)
		{
			for (int j=0; j<w; j++)
			{
				arr[i][j] = 0;
				visit[i][j] = false;
			}
		}
	}
	static void find()
	{
		for (int i = 0; i < h; i++)
		{
			for (int j=0; j<w; j++)
			{
				// 섬이고 아직 방문한 적이 없을때 
				if (arr[i][j] == 1 && !visit[i][j])
				{
					ans++;
					bfs(i, j);
				}
			}
		}
	}
	static void bfs(int sx, int sy)
	{
		int x,y,d,c,nx,ny;
		int temp;
		
		Queue <pair> q = new LinkedList<pair>();
		visit[sx][sy] = true;
		q.add(new pair (sx, sy));
		while (!q.isEmpty())
		{
			x = q.peek().x;
			y = q.peek().y;
			q.poll();
			for (int i = 0; i < 8; i++)
			{
				nx = x + dx[i];
				ny = y + dy[i];
				if (nx < 0 || nx >= h || ny < 0 || ny >= w)
					continue;
				if (arr[nx][ny] == 1 && !visit[nx][ny])
				{
					visit[nx][ny] = true;
					q.add(new pair (nx, ny));
				}
			}
		}
		
		return;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		
		while (true)
		{
			w = scan.nextInt();
			h = scan.nextInt();
			clear();
			if (w == 0 && h == 0)
				break;
			for (int i = 0; i < h; i++)
			{
				for (int j=0; j<w; j++)
					arr[i][j] = scan.nextInt();
			}
			find();
			System.out.println(ans);
		}
	
	}	
}
