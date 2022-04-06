package com;

import java.io.*;
import java.util.*;

public class Main {
	static class Person {
		int x, y, cnt;// 좌표, 이동횟수
		int key; // 가지고있는 열쇠들

		public Person(int x, int y, int cnt, int key) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.key = key;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x = 0;
		int y = 0; // 민식이 위치

		int[][][] visit = new int[n][m][64]; // 각 열쇠를 들고 방문했는지
		char[][] arr = new char[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = st.nextToken().toCharArray();
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == '0') {
					x = i;
					y = j;
				}
			}
		}
		arr[x][y] = '.';

		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };
		Queue<Person> q = new LinkedList<>();
		q.add(new Person(x, y, 0, 0)); // 열쇠 아무것도 없으니 0
		visit[x][y][0] = 1;

		while (!q.isEmpty()) {
			Person p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				// 범위 안, 벽아니고, 지금키 가지고 방문했는지
				if (0 <= nx && nx < n && 0 <= ny && ny < m 
						&& arr[nx][ny] != '#' && visit[nx][ny][p.key] == 0) {
					if (arr[nx][ny] == '.') {
						q.add(new Person(nx, ny, p.cnt + 1, p.key));
						visit[nx][ny][p.key] = 1;

					} else if (0 <= arr[nx][ny] - 'a' && arr[nx][ny] - 'a' <= 5) {// a~f 열쇠
						int k = (1 << (arr[nx][ny] - 'a')) | p.key; //주운키 더해주기
						if (visit[nx][ny][k] == 0) {
							q.add(new Person(nx, ny, p.cnt + 1, k));
							visit[nx][ny][k] = 1;
						}

					} else if (0 <= arr[nx][ny] - 'A' && arr[nx][ny] - 'A' <= 5) { // 문일때
						int door = (1 << (arr[nx][ny] - 'A')) & p.key; //해당 키 있는지
						if (door > 0) {
							q.add(new Person(nx, ny, p.cnt + 1, p.key));
							visit[nx][ny][p.key] = 1;
						}
					} else { // 출구 도착
						System.out.println(p.cnt + 1);
						return;
					}
				}
			}
		}
		System.out.println(-1);
	}
}