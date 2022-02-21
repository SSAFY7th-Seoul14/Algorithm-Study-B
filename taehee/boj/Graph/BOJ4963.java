package com;

import java.util.Scanner;

public class Main {

	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0, 1, -1, 1, -1 };
	static int[] dy = { 0, 0, 1, -1, 1, 1, -1, -1 };
	static int w, h, cnt;

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			w = sc.nextInt();
			h = sc.nextInt();
			cnt = 0;
			if (w == 0 && h == 0)
				break;

			arr = new int[h][w];
			visited = new boolean[h][w];

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (arr[i][j] == 1 && visited[i][j] == false) {
						dfs(i, j);
						cnt++;
					}
				}
			}

			System.out.println(cnt);

		}
	}

	private static void dfs(int x, int y) {
		visited[x][y] = true;

		for (int d = 0; d < 8; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (0 <= nx && nx < h && 0 <= ny && ny < w) {
				if (arr[nx][ny] == 1 && visited[nx][ny] == false) {
					dfs(nx, ny);
				}
			}
		}
	}
}