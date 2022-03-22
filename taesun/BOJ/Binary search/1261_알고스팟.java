import java.io.*;
import java.util.*;

class pair implements Comparable<pair>{
	int x,y,z;
	public pair(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	@Override
	public int compareTo(pair o) {
		// TODO Auto-generated method stub
		return this.z - o.z;
	}
}

// 2차원 배열에서 다익스트라 돌면서 최단 경로를 찾는 문제 
public class Main {
	static int ans;
	static int arr[][] = new int[101][101];
	static int n, m;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int dist[][] = new int[101][101];
	
	public static void dijkstra()
	{
		int x, y, nx, ny, d, w;
		PriorityQueue<pair> q = new PriorityQueue<pair>();
		for (int i=0; i<=100; i++)
		{
			for (int j=0; j<=100; j++)
				dist[i][j] = Integer.MAX_VALUE;
		}
		dist[0][0] = 0;
		q.add(new pair(0,0,arr[0][0]));
		while (!q.isEmpty())
		{
			x = q.peek().x;
			y = q.peek().y;
			w = q.peek().z;
			q.poll();
			
			for (int i=0; i<4; i++)
			{
				nx = x + dx[i];
				ny = y + dy[i];
				if (nx < 0 || nx >= m || ny < 0 || ny >= n)
					continue;
				if (dist[nx][ny] > arr[nx][ny] + w)
				{
					dist[nx][ny] = arr[nx][ny] + w;
					q.add(new pair(nx, ny, dist[nx][ny]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		for (int i=0; i<m; i++)
		{
			String s = scan.next();
			for (int j=0; j<n; j++)
				arr[i][j] = s.charAt(j) - '0';
		}
		dijkstra();
		ans = dist[m-1][n-1];
		System.out.println(ans);
	}
}