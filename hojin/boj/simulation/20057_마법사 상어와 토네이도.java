import java.util.*;
import java.io.*;

public class BOJ20057_마법사상어와토네이도 {
	static class Tornado {
		int r, c;

		public Tornado() {
			this.r = n / 2;
			this.c = n / 2;
		}

		public void move(int num) {
			int dir = num % 4;
			int dist = num / 2 + 1;
			while (dist > 0) {
				int nr = this.r + delta[dir][0];
				int nc = this.c + delta[dir][1];
				spread(nr, nc, dir);
				this.r = nr;
				this.c = nc;
				--dist;
			}
		}
	}

	static int n;
	static int[][] delta = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// n은 홀수
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		int sum = 0;
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; ++j) {
				int inp = Integer.parseInt(st.nextToken());
				sum += inp;
				map[i][j] = inp;
			}
		}
		makeTornado();
		for (int[] is : map) {
			for (int i : is) {
				sum -= i;
			}
		}
		System.out.println(sum);
		br.close();
	}

	public static void spread(int r, int c, int dir) {
		if (!rangeCheck(r, c))
			return;
		double curSand = map[r][c];

		int disappeard = (int) Math.floor(curSand * 0.01);
		for (int i = 0; i < 2; ++i) {
			int nr = r + delta[(dir + 2) % 4][0] + delta[(dir + 1 + 2 * i) % 4][0];
			int nc = c + delta[(dir + 2) % 4][1] + delta[(dir + 1 + 2 * i) % 4][1];
			if (rangeCheck(nr, nc)) {
				map[nr][nc] += disappeard;
			}
		}
		map[r][c] -= 2 * disappeard;

		disappeard = (int) Math.floor(curSand * 0.02);
		for (int i = 0; i < 2; ++i) {
			int nr = r + 2 * delta[(dir + 1 + 2 * i) % 4][0];
			int nc = c + 2 * delta[(dir + 1 + 2 * i) % 4][1];
			if (rangeCheck(nr, nc)) {
				map[nr][nc] += disappeard;
			}
		}
		map[r][c] -= 2 * disappeard;

		disappeard = (int) Math.floor(curSand * 0.07);
		for (int i = 0; i < 2; ++i) {
			int nr = r + delta[(dir + 1 + 2 * i) % 4][0];
			int nc = c + delta[(dir + 1 + 2 * i) % 4][1];
			if (rangeCheck(nr, nc)) {
				map[nr][nc] += disappeard;
			}
		}
		map[r][c] -= 2 * disappeard;

		disappeard = (int) Math.floor(curSand * 0.1);
		for (int i = 0; i < 2; ++i) {
			int nr = r + delta[dir][0] + delta[(dir + 1 + 2 * i) % 4][0];
			int nc = c + delta[dir][1] + delta[(dir + 1 + 2 * i) % 4][1];
			if (rangeCheck(nr, nc)) {
				map[nr][nc] += disappeard;
			}
		}
		map[r][c] -= 2 * disappeard;

		disappeard = (int) Math.floor(curSand * 0.05);
		map[r][c] -= disappeard;
		int nr = r + delta[dir][0];
		int nc = c + delta[dir][1];
		if (rangeCheck(nr, nc))
			map[nr][nc] += map[r][c];

		nr += delta[dir][0];
		nc += delta[dir][1];
		if (rangeCheck(nr, nc))
			map[nr][nc] += disappeard;

		map[r][c] = 0;
	}

	private static void makeTornado() {
		Tornado tornado = new Tornado();
		for (int i = 0; i <= 2 * (n - 1); ++i) {
			tornado.move(i);
		}
	}

	private static boolean rangeCheck(int r, int c) {
		if (0 <= r && r < n && 0 <= c && c < n)
			return true;
		return false;
	}
}
