package swtest2;

import java.io.*;
import java.util.*;

public class Solution_1 {
	static int res; // 비용편익
	static int map[][];
	static int W, H;
	static int dx1[] = { -1, 0, 1, 1, 1, 0 };
	static int dy1[] = { 0, 1, 1, 0, -1, -1 };
	static int dx2[] = { -1, -1, 0, 1, 0, -1 };
	static int dy2[] = { 0, 1, 1, 0, -1, -1 };
	static boolean visited[][];

	static class Cell {
		int x;
		int y;
		int idx;
		int sum;

		public Cell(int x, int y, int idx, int sum) {
			this.x = x;
			this.y = y;
			this.idx = idx;
			this.sum = sum;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			res = Integer.MIN_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			visited = new boolean[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					visited = new boolean[H][W];
					bfs(i, j);
				}
			}
			System.out.println("#" + t + " " + res);
		}
	}

	private static void bfs(int x, int y) {

		Queue<Cell> q = new LinkedList<>();
		visited[x][y] = true;
		q.offer(new Cell(x, y, 0, map[x][y]));

		while (!q.isEmpty()) {
			Cell cur = q.poll();
			if(cur.idx>4)
				break;
			if (cur.idx == 4) {
				res = Math.max(res, cur.sum*cur.sum);
			}
			int nx = 0, ny = 0;
			for (int i = 0; i < 6; i++) {
				if (cur.y % 2 == 0) {
					nx = x + dx2[i];
					ny = y + dy2[i];
				} else {
					nx = x + dx1[i];
					ny = y + dy1[i];
				}
				if (nx < 0 || nx >= H || ny < 0 || ny >= W)
					continue;
				
					q.offer(new Cell(nx, ny, cur.idx + 1, cur.sum + map[nx][ny]));
					visited[nx][ny] = true;
				

			}
		}

	}

	private static void print(int x, int y) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(visited[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();

	}

	private static void getCost() {
		int sum = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (visited[i][j])
					sum += map[i][j] * map[i][j];
			}
		}
		res = Math.max(res, sum);
	}
}
