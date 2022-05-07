import java.io.*;
import java.util.*;

public class BOJ12784_인하니카공화국 {

	static class Island {
		int val;
		ArrayList<Bridge> link;
		int min;

		public Island(int val) {
			this.val = val;
			this.link = new ArrayList<>();
			this.min = 0;
		}

	}

	static class Bridge {
		int to, cost;

		public Bridge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

	}

	static Bridge[] bridges;
	static Island[] islands;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = stoi(br.readLine());
		StringTokenizer st;
		for (int tc = 0; tc < T; ++tc) {
			st = new StringTokenizer(br.readLine());
			int n = stoi(st.nextToken());
			islands = new Island[n + 1];
			visited = new boolean[n + 1];
			for (int i = 1; i <= n; ++i) {
				islands[i] = new Island(i);
			}
			int m = stoi(st.nextToken());
			for (int i = 0; i < m; ++i) {
				st = new StringTokenizer(br.readLine());
				int from = stoi(st.nextToken());
				int to = stoi(st.nextToken());
				int dynamite = stoi(st.nextToken());
				islands[from].link.add(new Bridge(to, dynamite));
				islands[to].link.add(new Bridge(from, dynamite));
			}
			visited[1] = true;
			dfs(1);
			sb.append(islands[1].min).append('\n');
		}
		System.out.println(sb);
		br.close();
	}

	private static void dfs(int nth) {
		ArrayList<Bridge> link = islands[nth].link;
		int size = link.size();
		if (nth != 1 && size == 1) {
			islands[nth].min = link.get(0).cost;
			return;
		}
		for (int i = 0; i < size; ++i) {
			Bridge next = link.get(i);
			int nextVal = next.to;
			if (!visited[nextVal]) {
				visited[nextVal] = true;
				dfs(nextVal);
				islands[nth].min += Math.min(next.cost, islands[nextVal].min);
			}
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
