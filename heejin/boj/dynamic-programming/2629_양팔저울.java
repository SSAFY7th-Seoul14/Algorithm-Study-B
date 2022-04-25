package swtest2;

import java.io.*;
import java.util.*;

public class Solution2 {

	static class Rock {
		int x; // 좌표
		int y;
		int rank; // 난이도

		public Rock(int x, int y, int rank) {
			this.x = x;
			this.y = y;
			this.rank = rank;
		}

	}

	static int res;
	static int dx[] = { 0, 0 }; // 좌우
	static int dy[] = { -1, 1 };
	static int map[][];
	static int N, M;
	static int sx, sy, ex, ey; // 시작점, 끝점

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			res = Integer.MAX_VALUE;
	
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 2) { // 시작점
						sx = i;
						sy = j;
					}
					if (map[i][j] == 3) { // 끝점
						ex = i;
						ey = j;
					}
				}
			}
			// 시작점에서 끝점까지 탐색 시작
			bfs(sx, sy);
			System.out.println("#" + t + " " + res);

		}
	}

	private static void bfs(int sx, int sy) {
		Queue<Rock> q = new LinkedList<>();
		q.offer(new Rock(sx, sy, 0));

		while (!q.isEmpty()) {
			Rock cur = q.poll();
			if (cur.x == ex && cur.y == ey) {
				res = Math.min(res, cur.rank);
			}
			// 현재 위치에서 상측에 암벽 있을 경우
			if (isUp(cur.x, cur.y) > 0) {
				int up = isUp(cur.x, cur.y);
				q.offer(new Rock(sx - up, sy, cur.rank < up ? up : cur.rank));
			}
			// 현재 위치에서 하측에 암벽 있을 경우
			if (isDown(cur.x, cur.y) > 0) {
				int down = isUp(cur.x, cur.y);
				q.offer(new Rock(sx + down, sy, cur.rank < down ? down : cur.rank));
			}
			for (int i = 0; i < 2; i++) { // 현재 위치에서 좌, 우측에 암벽 있을 경우
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				if (map[nx][ny] == 1) {
					q.offer(new Rock(nx, ny, cur.rank));
				}
			}
		}

	}

	private static int isDown(int x, int y) {
		if(x+1>N)
			return 0;
		for (int i = x+1; i < N; i++) {
			if (map[i][y] == 1) {
				return i - x;
			}
		}
		return 0;
	}

	private static int isUp(int x, int y) {
		if(x-1<0)
			return 0;
		for (int i = x-1; i >= 0; i--) {
			if (map[i][y] == 1) {
				return x - i;
			}
		}
		return 0;

	}

}
