package com;

import java.io.*;
import java.util.*;

public class Main {
	
	static int n,l,r;
	static ArrayList<ArrayList<int[]>> union;
	static ArrayList<int[]> u;
	static boolean[][] visit;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0;
		while(true) {
			union = new ArrayList<>();
			visit = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(!visit[i][j]) {
						u = new ArrayList<>();
						visit[i][j] = true;
						bfs(i,j);
						if(u.size()!=1) union.add(u);
					}
				}
			}
			if(union.size() == 0) break;
			move();
			cnt++;
		}
		System.out.println(cnt);
	}

	private static void move() {
		for(ArrayList<int[]> u : union) {
			int sum = 0;
			for(int[] p : u) {
				sum += arr[p[0]][p[1]];
			}
			int res = sum / u.size();
			for(int[] p : u) {
				arr[p[0]][p[1]] = res;
			}
			
		}
	}

	private static void bfs(int x, int y) {
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x,y});
		u.add(new int[] {x,y});
		visit[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if(0<=nx && nx<n && 0<=ny && ny<n && !visit[nx][ny] ) {
					int diff = Math.abs(arr[nx][ny] - arr[cur[0]][cur[1]]);
					if(l<= diff && diff <=r) {
						q.offer(new int[] {nx,ny});
						u.add(new int[] {nx,ny});
						visit[nx][ny] = true;
					}
				}
			}
		}
	}

}
