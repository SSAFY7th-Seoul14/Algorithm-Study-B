import java.io.*;
import java.util.*;

class pair implements Comparable<pair>{
	int first, second;
	public pair(int x, int y) {
		this.first = x;
		this.second = y;
	}
	@Override
	public int compareTo(pair o) {
		// TODO Auto-generated method stub
		return this.second - o.second;
	}
	
}
public class Main {
	static int ans, n, m, x;
	static int dist[] = new int[1003];
	static ArrayList<ArrayList<pair>> arr = new ArrayList<ArrayList<pair>>(); 
	

	static int dijkstra(int src, int dest)
	{
		PriorityQueue<pair> q = new PriorityQueue<pair>();
		int idx, d, next, w;
		for (int i = 0; i < 1001; i++)
		{
			if (i != src)
				dist[i] = Integer.MAX_VALUE;
			else
				dist[i] = 0;
		}
		q.add(new pair(src, 0));
		while (!q.isEmpty())
		{
			idx = q.peek().first;
			d = q.peek().second;
			q.poll();
			if (dist[idx] < d) // 이미 방문한 곳이면 dist != INF 이므로 넘어감
				continue;
			for (int i=0; i < arr.get(idx).size(); i++)
			{
				next = arr.get(idx).get(i).first;
				w = arr.get(idx).get(i).second;
				if (dist[next] > dist[idx] + w)
				{
					dist[next] = dist[idx] + w;
					q.add(new pair(next, dist[next]));
				}
			}
		}
		return dist[dest];
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		x = scan.nextInt();
		int a, b, c;
		for (int i=0; i<= n+1; i++)
			arr.add(new ArrayList<pair>());
		for (int i=0; i<m; i++)
		{
			a = scan.nextInt();
			b = scan.nextInt();
			c = scan.nextInt();
			arr.get(a).add(new pair(b , c));
		}
		for (int i = 1; i<= n; i++)
			ans = Math.max(ans, dijkstra(i, x) + dijkstra(x , i));
		PriorityQueue<pair> q = new PriorityQueue<pair>();
		System.out.println(ans);
	}	
}
