import java.io.*;
import java.util.*;

public class BOJ11779_최소비용구하기2 {
	static class Node {
		int current;
		Node prev;

		public Node(int current) {
			this.current = current;
			this.prev = null;
		}
	}

	static class Bus implements Comparable<Bus> {
		int no, cost;

		public Bus(int to, int cost) {
			this.no = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Bus o) {
			return this.cost - o.cost;
		}
	}

	static ArrayList<Bus>[] list;
	static Node[] nodes;
	static int n;
	static int[] minCost;
	static StringBuilder path = new StringBuilder();
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		list = new ArrayList[n + 1];
		nodes = new Node[n + 1];
		for (int i = 1; i <= n; ++i)
			nodes[i] = new Node(i);
		for (int i = 1; i <= n; ++i)
			list[i] = new ArrayList<Bus>();
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[from].add(new Bus(to, cost));
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		dijkstra(start, end);
		cnt = 0;
		pathStr(nodes[end]);
		bw.write(String.valueOf(minCost[end]));
		bw.newLine();
		bw.write(String.valueOf(cnt));
		bw.newLine();
		bw.append(path);
		bw.flush();
		bw.close();
		br.close();
	}

	private static void pathStr(Node node) {
		if (node.prev == null) {
			path.append(node.current);
			++cnt;
			return;
		}
		pathStr(node.prev);
		path.append(' ').append(node.current);
		++cnt;
	}

	private static void dijkstra(int start, int end) {
		PriorityQueue<Bus> pq = new PriorityQueue<>();
		minCost = new int[n + 1];
		Arrays.fill(minCost, Integer.MAX_VALUE);

		minCost[start] = 0;
		pq.offer(new Bus(start, minCost[start]));
		while (!pq.isEmpty()) {
			Bus cur = pq.poll();
			int num = cur.no;
			int curCost = cur.cost;
			if (num == end)
				return;
			for (Bus bus : list[num]) {
				int next = bus.no;
				int nextCost = curCost + bus.cost;
				if (minCost[next] > nextCost) {
					minCost[next] = nextCost;
					pq.offer(new Bus(next, minCost[next]));
					nodes[next].prev = nodes[num];
				}
			}
		}
	}
}