import java.io.*;
import java.util.*;

public class BOJ3055_탈출 {
	static class Point {
		int r, c, time;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public Point(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}

	}

	static Point start;
	static LinkedList<Point> waterQ = new LinkedList<>();
	static int minT = Integer.MAX_VALUE;
	static char[][] map;
	static boolean[][] visited;
	static int r, c;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		visited = new boolean[r][c];
		for (int i = 0; i < r; ++i) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < c; ++j) {
				switch (map[i][j]) {
				case 'S':
					start = new Point(i, j);
					visited[i][j] = true;
					break;
				case '*':
					waterQ.offer(new Point(i, j));
					break;
				default:
					break;
				}
			}
		}
		if (bfs()) {
			System.out.println(minT);
		} else {
			System.out.println("KAKTUS");
		}

		br.close();
	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	private static boolean bfs() {
		LinkedList<Point> q = new LinkedList<>();
		q.offer(start);
		while (!q.isEmpty()) {
			int waterSize = waterQ.size();
			while (waterSize > 0) {
				Point curW = waterQ.poll();
				for (int i = 0; i < 4; ++i) {
					int nR = curW.r + dr[i];
					int nC = curW.c + dc[i];
					if (rangeCheck(nR, nC) && waterAvailable(nR, nC)) {
						map[nR][nC] = '*';
						waterQ.add(new Point(nR, nC));
					}
				}
				--waterSize;
			}
			int size = q.size();
			while (size > 0) {
				Point cur = q.poll();
				int t = cur.time;
				if (map[cur.r][cur.c] == 'D') {
					minT = t;
					return true;
				}
				for (int i = 0; i < 4; ++i) {
					int nR = cur.r + dr[i];
					int nC = cur.c + dc[i];
					if (rangeCheck(nR, nC) && available(nR, nC) && !visited[nR][nC]) {
						visited[nR][nC] = true;
						q.add(new Point(nR, nC, t + 1));
					}
				}
				--size;
			}
		}
		return false;
	}

	private static boolean available(int r, int c) {
		switch (map[r][c]) {
		case 'X':
		case '*':
			return false;
		default:
			return true;
		}
	}

	private static boolean waterAvailable(int nR, int nC) {
		switch (map[nR][nC]) {
		case 'X':
		case 'D':
		case '*':
			return false;
		default:
			return true;
		}
	}

	private static boolean rangeCheck(int nR, int nC) {
		if (0 <= nR && nR < r && 0 <= nC && nC < c)
			return true;
		return false;
	}

}