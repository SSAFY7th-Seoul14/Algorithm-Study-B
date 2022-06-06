import java.io.*;
import java.util.*;

class Ana {
	int visited, from, time;

	public Ana(int visited, int from, int time) {
		this.visited = visited;
		this.from = from;
		this.time = time;
	}

}

public class BOJ17182_우주탐사선 {
	static int[][] map;
	static int MAX_VALUE = Integer.MAX_VALUE;
	static int n;
	static int ans = MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		map = new int[n][n];
		int start = stoi(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		bfs(start);
		System.out.println(ans);
	}

	private static void bfs(int start) {
		Queue<Ana> q = new LinkedList<Ana>();
		q.add(new Ana(1 << start, start, 0));
		while (!q.isEmpty()) {
			Ana ana = q.poll();
			int visited = ana.visited;
			int from = ana.from;
			int time = ana.time;
			if (visited == (1 << n) - 1) {
				ans = Math.min(ans, time);
				continue;
			}
			for (int i = 0; i < n; i++) {
				if (((1 << i) & visited) == 0) {
					q.add(new Ana(visited | 1 << i, i, time + map[from][i]));
				}
			}
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
