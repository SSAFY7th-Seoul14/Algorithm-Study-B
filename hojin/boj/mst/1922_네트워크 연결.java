import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1922_네트워크연결 {
	static class Network implements Comparable<Network> {
		int from, to, cost;

		public Network(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Network o) {
			return this.cost - o.cost;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 컴퓨터의 수 1 <= n <= 1,000
		int n = Integer.parseInt(br.readLine());
		// 연결할수 있는 선의 수 1 <= m <= 100,000
		int m = Integer.parseInt(br.readLine());
		Network[] networks = new Network[m];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			networks[i] = new Network(from, to, cost);
		}
		br.close();
		Arrays.sort(networks);
		int[] parents = makeSet(n);
		int costs = 0;
		for (int i = 0; i < m; i++) {
			if (union(networks[i].from, networks[i].to, parents))
				costs += networks[i].cost;
		}
		System.out.println(costs);
	}

	private static boolean union(int a, int b, int[] parents) {
		int aRoot = findSet(a, parents);
		int bRoot = findSet(b, parents);
		if (aRoot == bRoot)
			return false;
		parents[bRoot] = aRoot;
		return true;
	}

	private static int findSet(int a, int[] parents) {
		if (a == parents[a])
			return a;
		return parents[a] = findSet(parents[a], parents);
	}

	private static int[] makeSet(int n) {
		int[] set = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			set[i] = i;
		}
		return set;
	}

}