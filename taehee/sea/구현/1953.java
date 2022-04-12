package com;

import java.io.*;
import java.util.*;

public class Solution {
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int[][] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 지도크기
			int m = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken()); // 맨홀위치
			int c = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken()); // 시간
			arr = new int[n][m];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			boolean[][] visit = new boolean[n][m];
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] {r,c,1});
			visit[r][c] = true;
			int cnt = 1;
			while(true) {
				if(q.size()==0) break;
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];
				int time = cur[2];
				if(time==l) break;
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if(0<= nx && nx<n && 0<=ny && ny<m
							&&isPossible(x,y,nx,ny) && !visit[nx][ny]) {
						q.add(new int[] {nx,ny,time+1});
						cnt++;
						visit[nx][ny] = true;
					}
				}
			}
	        System.out.println("#"+t+" "+cnt);
		}

	}

	private static boolean isPossible(int x, int y, int nx, int ny) {
		if(arr[nx][ny] == 0) return false;
		
		if(arr[x][y] == 1) {
			if(bottom(x,y,nx,ny)) return true;
			if(top(x,y,nx,ny)) return true;
			if(right(x,y,nx,ny)) return true;				
			if(left(x,y,nx,ny)) return true;
			return false;
		}else if(arr[x][y] == 2) {
			if(bottom(x,y,nx,ny)) return true;
			if(top(x,y,nx,ny)) return true;
			return false;
		}else if(arr[x][y] == 3) {
			if(right(x,y,nx,ny)) return true;
			if(left(x,y,nx,ny)) return true;
			return false;
		}else if(arr[x][y] == 4) {
			if(right(x,y,nx,ny)) return true;
			if(top(x,y,nx,ny)) return true;
			return false;
		}else if(arr[x][y] == 5) {
			if(right(x,y,nx,ny)) return true;
			if(bottom(x,y,nx,ny)) return true;
			return false;
		}else if(arr[x][y] == 6) {
			if(left(x,y,nx,ny)) return true;
			if(bottom(x,y,nx,ny)) return true;
			return false;
		}else {
			if(left(x,y,nx,ny)) return true;
			if(top(x,y,nx,ny)) return true;
			return false;
		}
	}
	private static boolean right(int x, int y, int nx, int ny) {
		if(ny-y==1 && nx-x==0 &&
				(arr[nx][ny] == 1 || arr[nx][ny] == 3 || arr[nx][ny] == 6 || arr[nx][ny] == 7)) return true;			
		return false;
	}
	private static boolean left(int x, int y, int nx, int ny) {
		if(ny-y == -1 && nx-x== 0 &&
				(arr[nx][ny] == 1 || arr[nx][ny] == 3 || arr[nx][ny] == 4 || arr[nx][ny] == 5)) return true;			
		return false;
	}
	private static boolean top(int x, int y, int nx, int ny) {
		if(nx-x == -1 && ny-y==0 && 
				(arr[nx][ny] == 1 || arr[nx][ny] == 2 || arr[nx][ny] == 5 || arr[nx][ny] == 6)) return true;			
		return false;
	}
	private static boolean bottom(int x, int y, int nx, int ny) {
		if(nx-x == 1 && ny-y==0 &&
				(arr[nx][ny] == 1 || arr[nx][ny] == 2 || arr[nx][ny] == 4 || arr[nx][ny] == 7)) return true;			
		return false;
	}
}
