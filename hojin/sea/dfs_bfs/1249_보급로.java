// 45분
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
public class SWEA1249_보급로 {
	static class Point implements Comparable<Point> {
		int r, c, cost;

		public Point(int r, int c, int cost) {
			super();
			this.r = r;
			this.c = c;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point o) {
			return this.cost - o.cost;
		}

	}

	static int n;
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; ++tc) {
			n = Integer.parseInt(br.readLine());
			int[][] map = new int[n][n];
			int[][] dp = new int[n][n];
			for (int i = 0; i < n; ++i) {
				char[] tmp = br.readLine().toCharArray();
				for (int j = 0; j < n; ++j) {
					map[i][j] = tmp[j] - '0';
				}
			}
			for (int i = 0; i < n; ++i) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);
			}
			dp[0][0] = map[0][0];
			// bfs해가면서, 단 pq로
			boolean[][] visited = new boolean[n][n];
			PriorityQueue<Point> pq = new PriorityQueue<Point>();
			visited[0][0] = true;
			pq.offer(new Point(0, 0, dp[0][0]));
			while (!pq.isEmpty()) {
				Point cur = pq.poll();
				int curR = cur.r;
				int curC = cur.c;
				int curMinCost = cur.cost;
				for (int i = 0; i < 4; ++i) {
					int nR = curR + dr[i];
					int nC = curC + dc[i];
					if (rangeCheck(nR, nC)) {
						// 이웃들 갱신
						// 사방탐색 영역의 dp값이 자신의 dp+map보다 크면 갱신
						if (dp[nR][nC] > curMinCost + map[nR][nC]) {
							dp[nR][nC] = curMinCost + map[nR][nC];
							pq.offer(new Point(nR, nC, dp[nR][nC]));
						}
					}
				}
			}
			bw.write(String.format("#%d %d%n", tc, dp[n - 1][n - 1]));
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean rangeCheck(int nR, int nC) {
		if (0 <= nR && nR < n && 0 <= nC && nC < n)
			return true;
		return false;
	}

}
