package com;

import java.io.*;
import java.util.*;

public class Solution {

	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int n,w,h, min;
	static int[] orders; //깨는 순서
	static int[][] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			min = 1000;
			orders = new int[n];
			arr = new int[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			perm(0); // 중복순열 생성
			System.out.println("#"+t+" "+min);
		}
	}
	private static void perm(int cnt) {
		if(cnt == n) {
			int[][] temp = copy(arr); //복사본 생성
			for (int i = 0; i < n; i++) { //복사본을 순서대로 깨기
				temp = detroy(temp,orders[i]);
				if(temp==null) return;
			}
			int bCnt = 0; //다 깨고나서 벽돌개수
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if(temp[i][j] > 0) bCnt++;
				}
			}
			min = Math.min(min, bCnt);
			return;
		}
		for (int i = 0; i < w; i++) {
			orders[cnt] = i;
			perm(cnt+1);
		}
	}
	
	private static int[][] detroy(int[][] temp, int y) {
		boolean[][] visit = new boolean[h][w]; //깨질 벽돌들 true
		int x = 0;
		while(x < h-1 && temp[x][y] == 0) x++; //깰 벽돌 좌표 x,y
		
		Queue<int[]> q = new LinkedList<>(); 
		q.add(new int[] {x,y});
		visit[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int d = 0; d < 4; d++) { //4방향으로
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				for (int i = 0; i < temp[cur[0]][cur[1]] - 1; i++) { //벽돌수 - 1만큼 깨기
					if(0<=nx && nx<h && 0<=ny && ny<w && temp[nx][ny] > 0 && !visit[nx][ny]) {
						q.add(new int[] {nx,ny});
						visit[nx][ny] = true;
					}
					nx += dx[d];
					ny += dy[d];
				}
			}
		}
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if(visit[i][j]) temp[i][j] = 0; //깨질 벽돌들 전부 0으로 
			}
		}
		move(temp); // 벽돌 내려주기
		return temp;
	}
	private static void move(int[][] temp) {
		for (int i = 0; i < w; i++) { //각 열들에 대해 1 밑으로 내려주기
			int cx = h-1;
			while(true) {
				while(cx>=0 && temp[cx][i] > 0) cx--; //맨밑 0 찾고
				if(cx < 0) break;
				
				int nx = cx-1;
				while(nx>=0 && temp[nx][i] == 0) nx--; //맨밑 벽돌 찾고
				if(nx < 0) break;
				//swap
				int tmp = temp[cx][i];
				temp[cx][i] = temp[nx][i];
				temp[nx][i] = tmp;
				
			}
		}
	}
	private static int[][] copy(int[][] a) {
		int[][] tmp = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				tmp[i][j] = a[i][j];
			}
		}
		return tmp;
	}
}
