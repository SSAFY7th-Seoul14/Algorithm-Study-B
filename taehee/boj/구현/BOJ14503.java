package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken()); //n,m
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken()); //청소기 좌표, 방향
		
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		boolean isStop = false;
		
		while(!isStop) {
			map[x][y] = 2; //지금좌표 청소
			boolean clear = false;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[(d+3)%4];
				int ny = y + dy[(d+3)%4]; //왼쪽공간 좌표
				d = (d+3)%4; //회전
				if(map[nx][ny] == 0) { //전진
					x = nx;
					y = ny;
					clear = true;
					break;
				}
			}
			if(!clear) { //청소 못하는 경우
				int nx = x - dx[d];
				int ny = y - dy[d];
				if(map[nx][ny] == 1) { //멈춤
					isStop = true;
				}else { //후진
					x = nx;
					y = ny;
				}
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j] == 2) cnt++;
			}
			
		}
		System.out.println(cnt);
	}
}