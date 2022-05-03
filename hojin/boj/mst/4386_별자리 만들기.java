import java.util.*;
import java.io.*;

public class BOJ4386_별자리만들기 {
	static class Star {
		double x, y;

		public Star(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static class Edge implements Comparable<Edge> {
		int from, to;
		double length;

		public Edge(int from, int to, double length) {
			this.from = from;
			this.to = to;
			this.length = length;
		}

		@Override
		public int compareTo(Edge o) {
			double compare = this.length - o.length;
			return compare > 0.00 ? 1 : (compare < 0.00 ? -1 : 0);
		}
	}

	static Star[] stars;
	static int n;
	static int[] selected = new int[2];
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		stars = new Star[n];
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			double x = stod(st.nextToken());
			double y = stod(st.nextToken());
			stars[i] = new Star(x, y);
		}
		combi(0, 0);
		parent = new int[n];
		makeParent();
		double answer = 0;
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (union(cur.from, cur.to)) {
				answer += cur.length;
			}
		}
		System.out.printf("%.2f", answer);
		br.close();
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) {
			return false;
		}
		if (aRoot < bRoot) {
			parent[bRoot] = aRoot;
		} else {
			parent[aRoot] = bRoot;
		}
		return true;
	}

	private static int find(int a) {
		if (parent[a] == a) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}

	private static void makeParent() {
		for (int i = 0; i < n; ++i) {
			parent[i] = i;
		}
	}

	private static void combi(int cnt, int start) {
		if (cnt == 2) {
			makeEdge();
			return;
		}
		for (int i = start; i < n; ++i) {
			selected[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}

	private static void makeEdge() {
		double xSqr = Math.pow(stars[selected[0]].x - stars[selected[1]].x, 2);
		double ySqr = Math.pow(stars[selected[0]].y - stars[selected[1]].y, 2);
		double len = Math.sqrt(xSqr + ySqr);
		pq.add(new Edge(selected[0], selected[1], len));
	}

	private static double stod(String str) {
		return Double.parseDouble(str);
	}

}