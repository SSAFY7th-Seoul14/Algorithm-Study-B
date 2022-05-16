package com;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		char[][] arr = new char[r][c];
		boolean[][] visit = new boolean[r][c];
		boolean[][] wvisit = new boolean[r][c];
		int bx = 0;
		int by = 0;//비버굴
		Queue<int[]> water = new LinkedList<>();//현재 물위치
		Queue<int[]> q = new LinkedList<>();//고슴도치
		
		for (int i = 0; i < r; i++) {
			arr[i] = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				if(arr[i][j] == 'D') {
					bx = i;
					by = j;
				}
				if(arr[i][j] == 'S') {
					q.offer(new int[] {i,j,0});
					visit[i][j] = true;
				}
				if(arr[i][j] == '*') {
					water.offer(new int[] {i,j});
					wvisit[i][j] = true;
				}
			}
		}
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		
		while(!q.isEmpty()) {
			int size = water.size();
			for(int w=0; w<size; w++) { //물을 먼저 다 움직인다
				int[] cur = water.poll();
				for (int i = 0; i < 4; i++) {
					int nx = cur[0] + dx[i];
					int ny = cur[1] + dy[i];
					if(0<=nx && nx<r && 0<=ny && ny<c && !wvisit[nx][ny]
							&& (arr[nx][ny] =='.' || arr[nx][ny] == 'S')) {
						arr[nx][ny] = '*';
						water.offer(new int[] {nx,ny});
						wvisit[nx][ny] = true;
					}
				}
			}
			size = q.size();
			for (int w = 0; w < size; w++) {//고슴도치 이동
				int[] cur = q.poll();
				if(cur[0] == bx && cur[1] == by) {//고슴도치 D에 도착
					System.out.println(cur[2]);
					return;
				}
				for (int i = 0; i < 4; i++) {
					int nx = cur[0] + dx[i];
					int ny = cur[1] + dy[i];
					if(0<=nx && nx<r && 0<=ny && ny<c && !visit[nx][ny]
							&& (arr[nx][ny] == '.' || arr[nx][ny] == 'D')) {
						arr[nx][ny] = 'S';
						q.offer(new int[] {nx,ny,cur[2]+1});
						visit[nx][ny] = true;
					}
				}	
			}
		}
		System.out.println("KAKTUS");
	}

}
