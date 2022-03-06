import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ6497_전력난 {

	static class Bridge implements Comparable<Bridge> {
		int from, to, dist;

		public Bridge(int from, int to, int dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(Bridge o) {
			return this.dist - o.dist;
		}

	}

	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
			if (m == 0 && n == 0)
				break;

			int totalDist = 0;
			PriorityQueue<Bridge> pq = new PriorityQueue<Bridge>();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int dist = Integer.parseInt(st.nextToken());
				totalDist += dist;
				pq.offer(new Bridge(from, to, dist));
			}

			makeSet(n);

			int minDist = 0;
			int cnt = 0;

			while (!pq.isEmpty()) {
				Bridge tmp = pq.poll();
				if (union(tmp.from, tmp.to)) {
					minDist += tmp.dist;
					if (++cnt == m - 1)
						break;
				}
			}

			sb.append(String.format("%d%n", totalDist - minDist));
		}
		br.close();
		System.out.println(sb);
	}

	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;
		parents[bRoot] = aRoot;
		return true;
	}

	private static int findSet(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = findSet(parents[a]);
	}

	private static void makeSet(int n) {
		parents = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}
	}

}