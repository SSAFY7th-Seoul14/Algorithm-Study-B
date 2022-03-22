import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1261_알고스팟 {
	static class Vertex implements Comparable<Vertex> {
		int r, c, cnt;

		public Vertex(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.cnt - o.cnt;
		}
	}

	static int[][] delta = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }, minCnt;
	static int m, n, START = 0;
	static char[][] map;

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new char[n][];
		minCnt = new int[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		// 다익스트라
		dijkstra();
		bw.write(Integer.toString(minCnt[n - 1][m - 1]));
		bw.flush();
		bw.close();
	}

	private static void dijkstra() {
		for (int i = 0; i < n; i++) {
			Arrays.fill(minCnt[i], Integer.MAX_VALUE);
		}
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		minCnt[START][START] = 0;
		pq.offer(new Vertex(START, START, minCnt[START][START]));
		while (!pq.isEmpty()) {
			Vertex cur = pq.poll();
			int r = cur.r;
			int c = cur.c;
			for (int i = 0; i < 4; i++) {
				int nR = r + delta[i][0];
				int nC = c + delta[i][1];
				if (rangeCheck(nR, nC)) {
					if (minCnt[nR][nC] > minCnt[r][c] + map[nR][nC] - '0') {
						minCnt[nR][nC] = minCnt[r][c] + map[nR][nC] - '0';
						pq.offer(new Vertex(nR, nC, minCnt[nR][nC]));
					}
				}
			}
		}
	}

	private static boolean rangeCheck(int r, int c) {
		if (0 <= r && r < n && 0 <= c && c < m) {
			return true;
		}
		return false;
	}

}