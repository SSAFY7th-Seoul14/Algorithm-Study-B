import java.io.*;
import java.util.*;

public class BOJ1162_도로포장 {

	static class Node implements Comparable<Node> {
		int to, cnt;
		long time;

		public Node(int to, long time) {
			this.to = to;
			this.time = time;
			this.cnt = 0;
		}

		public Node(int to, long time, int cnt) {
			this.to = to;
			this.time = time;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			return (int) (this.time - o.time);
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = stoi(st.nextToken());
		ArrayList<Node>[] cities = new ArrayList[n + 1];
		for (int i = 1; i <= n; ++i) {
			cities[i] = new ArrayList<>();
		}
		int m = stoi(st.nextToken());
		int k = stoi(st.nextToken());
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int c1 = stoi(st.nextToken());
			int c2 = stoi(st.nextToken());
			int t = stoi(st.nextToken());
			cities[c1].add(new Node(c2, t));
			cities[c2].add(new Node(c1, t));
		}
		long[][] dist = new long[n + 1][k + 1];
		for (int i = 1; i <= n; ++i) {
			Arrays.fill(dist[i], Long.MAX_VALUE);
		}
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int curNo = cur.to;
			long curT = cur.time;
			int curCnt = cur.cnt;
			if (dist[curNo][curCnt] < curT)
				continue;
			for (Node curE : cities[curNo]) {
				int nextNo = curE.to;
				long nextT1 = curE.time;
				if (curT + nextT1 < dist[nextNo][curCnt]) {
					dist[nextNo][curCnt] = curT + nextT1;
					pq.offer(new Node(nextNo, dist[nextNo][curCnt], curCnt));
				}
				int breakingCnt = curCnt + 1;
				if (breakingCnt <= k) {
					if (curT < dist[nextNo][breakingCnt]) {
						dist[nextNo][breakingCnt] = curT;
						pq.offer(new Node(nextNo, dist[nextNo][breakingCnt], breakingCnt));
					}
				}
			}
		}
		long min = Long.MAX_VALUE;
		for (int i = 0; i <= k; ++i) {
			min = Math.min(dist[n][i], min);
		}
		System.out.println(min);
		br.close();
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
