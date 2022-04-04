import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int from, to, cost;

	public Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}

}

public class SWEA3124_최소스패닝트리 {

	static int[] parents;
	static int V, E;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			for (int i = 0; i < E; ++i) {
				st = new StringTokenizer(br.readLine());
				pq.offer(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken())));
			}
			long totalCost = 0;
			int setCnt = 0;

			makeSet();
			while (!pq.isEmpty()) {
				Edge current = pq.poll();
				int from = current.from;
				int to = current.to;
				if (union(from, to)) {
					totalCost += current.cost;
					if (++setCnt == V - 1)
						break;
				}
			}
			bw.write(String.format("#%d %d%n", tc, totalCost));
		}
		br.close();
		bw.flush();
		bw.close();
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
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = findSet(parents[a]);
	}

	private static void makeSet() {
		parents = new int[V + 1];
		for (int i = 1; i <= V; ++i) {
			parents[i] = i;
		}
	}

}