import java.io.*;
import java.util.*;

class pair implements Comparable<pair> {
	int first, second;

	public pair(int x, int y) {
		this.first = x;
		this.second = y;
	}

	@Override
	public int compareTo(pair o) {
		return this.second - o.second;
	}
}

public class Main {
	static int n, m;
	static ArrayList<ArrayList<pair>> arr = new ArrayList<ArrayList<pair>>();

	static int dijkstra(int src, int dest) {
		int dist[] = new int[1001];
		for (int i = 1; i <= n; i++) {
			if (i == src)
				dist[i] = 0;
			else
				dist[i] = Integer.MAX_VALUE;
		}
		PriorityQueue<pair> q = new PriorityQueue<pair>();
		q.add(new pair(src, 0));
		int x, d, next, w;
		while (!q.isEmpty()) {
			x = q.peek().first;
			d = q.peek().second;
			q.poll();
			if (dist[x] < d)
				continue;
			for (int i=0; i<arr.get(x).size(); i++)
			{
				next = arr.get(x).get(i).first;
				w = arr.get(x).get(i).second;
				if (dist[next] > dist[x] + w)
				{
					dist[next] = dist[x] + w;
					q.add(new pair(next, dist[next]));
				}
			}
		}
		return dist[dest];
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i <= n; i++)
			arr.add(new ArrayList<pair>());
		m = Integer.parseInt(br.readLine());
		int a, b, c, src, dest;
		String s[];
		for (int i = 0; i < m; i++) {
			s = br.readLine().split(" ");
			a = Integer.parseInt(s[0]);
			b = Integer.parseInt(s[1]);
			c = Integer.parseInt(s[2]);
			arr.get(a).add(new pair(b, c));
		}
		s = br.readLine().split(" ");
		src = Integer.parseInt(s[0]);
		dest = Integer.parseInt(s[1]);
		System.out.println(dijkstra(src, dest));
	}
}
