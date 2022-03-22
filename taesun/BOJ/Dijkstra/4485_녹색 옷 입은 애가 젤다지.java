import java.io.*;
import java.util.*;

class pair implements Comparable<pair>{
	int x, y, z;
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
/*
bfs인데 각 arr 값의 총합이 최소로 되어야함 = 다익스트라
arr값을 간선 가중치라고 생각, 이미 갱신된 좌표를 무시하는 코드가 없어도 잘돌아감
*/
public class Main {
	static int n, ans;
	static int arr[][] = new int[130][130];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int dist[][] = new int[130][130];
	public static void dijkstra()
	{
		int x, y, nx, ny, d, w;
		PriorityQueue<pair> q = new PriorityQueue<pair>();
		dist[0][0] = 0;
		q.add(new pair(0,0,arr[0][0]));
		while (!q.isEmpty())
		{
			x = q.peek().x;
			y = q.peek().y;
			w = q.peek().z;
			//System.out.println(x + " " + y + " " + w);
			q.poll();
			
			for (int i=0; i<4; i++)
			{
				nx = x + dx[i];
				ny = y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= n)
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
		int test = 1;
		while (true)
		{
			n = scan.nextInt();
			if (n == 0)
				break;
			for (int i=0; i<=125; i++)
			{
				for (int j=0; j<=125; j++)
				{
					arr[i][j] = 0;
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			for (int i=0; i<n; i++)
			{
				for (int j=0; j<n; j++)
					arr[i][j] = scan.nextInt();
			}
			dijkstra();
			ans = dist[n-1][n-1];
			System.out.println("Problem " + test++ + ": " + ans);
		}
	}	
}
