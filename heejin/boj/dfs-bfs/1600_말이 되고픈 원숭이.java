package com.boj;

import java.io.*;
import java.util.*;
// BOJ / 말이 되고픈 원숭이 / G4 / 40분
// https://www.acmicpc.net/problem/1600

class Node {
	int x;
	int y;
	int horse; // 말처럼 점프한 횟수
	int time; // 소요 시간

	public Node(int x, int y, int horse, int time) {
		this.x = x;
		this.y = y;
		this.horse = horse;
		this.time = time;
	}

}

public class Main_1600 {
	static int dx[] = { -1, 1, 0, 0 }; // 인접이동
	static int dy[] = { 0, 0, -1, 1 };
	static int dx2[] = { -2, -1, 1, 2, 2, 1, -1, -2 }; // 말처럼 이동
	static int dy2[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int map[][];
	static int visited[][][];
	static int K, W, H;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new int[H][W][K + 1]; // (w,h)좌표에서 k번만큼 말처럼 점프하여 방문하였을 때 시간
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if (W == 1 && H == 1)
			System.out.println(0);
		else
			System.out.println(bfs(0, 0));
	}

	private static int bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x, y, 0, 0));
		visited[x][y][0] = 0;

		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.x == H - 1 && cur.y == W - 1) {
				// visited[nx][ny][0~K]에서 최솟값 구하기
				int res = Integer.MAX_VALUE;
				for (int i = 0; i <= K; i++) {
					if (visited[cur.x][cur.y][i] != 0)
						res = Math.min(res, visited[cur.x][cur.y][i]);
				}

				return res;
			}
			// 1. 말처럼 점프
			for (int i = 0; i < 8; i++) {
				int nx = cur.x + dx2[i];
				int ny = cur.y + dy2[i];
				if (nx < 0 || nx >= H || ny < 0 || ny >= W)
					continue;
				if (map[nx][ny] != 1 && cur.horse < K && visited[nx][ny][cur.horse + 1] == 0) { // 점프 횟수가 K 미만이고, 목적지가
																								// 아직 미방문일 경우
					q.offer(new Node(nx, ny, cur.horse + 1, cur.time + 1));
					visited[nx][ny][cur.horse + 1] = visited[cur.x][cur.y][cur.horse] + 1;
				}
			}
			// 2. 인접 이동
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= H || ny < 0 || ny >= W)
					continue;
				if (map[nx][ny] != 1 && visited[nx][ny][cur.horse] == 0) {
					q.offer(new Node(nx, ny, cur.horse, cur.time + 1));
					visited[nx][ny][cur.horse] = visited[cur.x][cur.y][cur.horse] + 1;
				}

			}
		}
		return -1;
	}
}
