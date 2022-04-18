import java.io.*;
import java.util.*;


public class Main {

	// 백준 호석이 두 마리 치킨 
	static int n, m, ans;
	static int arr[][] = new int[101][101];
	static int dist[][] = new int[101][101];
	static int INF = 210000000;
	// n이 작으니까 플로이드를 쓸 수 있다 
	static void floyd()
	{	
		for (int k = 1; k <=n; k++)
		{
			for (int i=1; i <= n; i++)
			{
				for (int j = 1; j <= n; j++)
				{
					if (dist[i][j] > dist[i][k] + dist[k][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
	}
	static int distance(int a, int b)
	{
		int ret = 0;
		for (int i=1; i<=n; i++)
		{
			// 현재 위치가 치킨집이면 거리는 0이므로 넘어간다 
			if (i == a || i == b)
				continue;
			// 가장 가까운 치킨집까지 왕복거리를 구해서 더해준다 
			ret += Math.min(dist[i][a] * 2, dist[i][b] * 2);
		}
		return ret;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		
		ans = Integer.MAX_VALUE;
		n = scan.nextInt();
		m = scan.nextInt();
		int x,y;
		int building[] = {0,0};
		for (int i=1; i<=n; i++)
		{
			for (int j=1; j<=n; j++)
			{
				if (i == j)
					dist[i][j] = 0;
				else
					dist[i][j] = INF;
			}
		}
		
		for (int i = 0; i < m; i++)
		{
			x = scan.nextInt();
			y = scan.nextInt();
			dist[x][y] = 1;
			dist[y][x] = 1;
		}
		// 플로이드로 모든 노드간 거리를 미리 계산하고 쓰자 
		floyd();
		
		// 치킨집 2개 고르기 
		int d;
		for (int i=1; i<=n; i++)
		{
			for (int j=i+1; j<=n; j++)
			{
				d = distance(i, j);
				// 작은 번호가 우선시 되어야하므로 거리가 같은 경우는 무시 
				if (ans > d)
				{
					ans = d;
					building[0] = i;
					building[1] = j;
				}
			}
		}
		System.out.println(building[0] + " " + building[1] + " " + ans);
	}
}
