import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int r, c, time;

	public Point(int r, int c, int time) {
		super();
		this.r = r;
		this.c = c;
		this.time = time;
	}

}

public class SWEA1953_탈주범검거 {

	static int[][][] tunnels = {
			// 0
			{},
			// 1
			{ { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } },
			// 2
			{ { -1, 0 }, { 1, 0 } },
			// 3
			{ { 0, -1 }, { 0, 1 } },
			// 4
			{ { -1, 0 }, { 0, 1 } },
			// 5
			{ { 0, 1 }, { 1, 0 } },
			// 6
			{ { 1, 0 }, { 0, -1 } },
			// 7
			{ { 0, -1 }, { -1, 0 } } };
	static int[][] map;
	static int n, m, L, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; ++tc) {
			st = new StringTokenizer(br.readLine());
			// 5<=세로n, 가로m<=50
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			// 맨홀 위치 R, C
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			// 소요시간 L
			L = Integer.parseInt(st.nextToken());
			// 입력 받을 map
			map = new int[n][m];
			for (int i = 0; i < n; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			answer = 0;
			// R, C부터 bfs로 이동
			bfs(R, C);
			bw.write(String.format("#%d %d%n", tc, answer));
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static void bfs(int r, int c) {
		boolean[][] visited = new boolean[n][m];
		Queue<Point> q = new LinkedList<>();
		visited[r][c] = true;
		q.offer(new Point(r, c, 1));
		++answer;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			int curR = cur.r;
			int curC = cur.c;
			int curTime = cur.time;
			if (curTime == L)
				continue;
			int[][] curTunnel = tunnels[map[curR][curC]];
			for (int i = 0; i < curTunnel.length; ++i) {
				int nR = curR + curTunnel[i][0];
				int nC = curC + curTunnel[i][1];
				if (rangeCheck(nR, nC) && !visited[nR][nC]) {
					int[][] nextTunnel = tunnels[map[nR][nC]];
					for (int j = 0; j < nextTunnel.length; ++j) {
						int conCheckR = curTunnel[i][0] + nextTunnel[j][0];
						int conCheckC = curTunnel[i][1] + nextTunnel[j][1];
						if (conCheckR == 0 && conCheckC == 0) {
							visited[nR][nC] = true;
							++answer;
							q.offer(new Point(nR, nC, curTime + 1));
						}
					}
				}
			}
		}
	}

	private static boolean rangeCheck(int nR, int nC) {
		if (0 <= nR && nR < n && 0 <= nC && nC < m)
			return true;
		return false;
	}

}
