package com;

import java.io.*;
import java.util.*;

public class Main {
	
	static int n,max=0;
	static int[][] arr;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static boolean[][] visit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		n = (int)Math.pow(2, N); //배열 크기
		int q = Integer.parseInt(st.nextToken()); //주문 횟수

		arr = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<q; i++) {
			int l = Integer.parseInt(st.nextToken());
			fire(l);
		}
		
		int sum = 0;
		visit = new boolean[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				sum += arr[i][j];
				if(!visit[i][j] && arr[i][j] > 0) bfs(i,j);
			}
		}
		System.out.println(sum);
		System.out.println(max);
	}
	private static void bfs(int x, int y) {
		visit[x][y] = true;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x,y});
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				if(0<=nx && nx<n && 0<=ny && ny<n && arr[nx][ny] > 0 && !visit[nx][ny]) {
					q.offer(new int[] {nx,ny});
					cnt++;
					visit[nx][ny] = true;
				}
			}
		}

		max = Math.max(max, cnt);
		return;
	}
	private static void fire(int level) {
		int L = (int)Math.pow(2, level); //2^level
		//1.회전
		for (int i = 0; i < n; i += L) {
			for (int j = 0; j < n; j += L) {
				rotate(i,j,level);
			}
		}
		//2.얼음 양 -1
		ArrayList<int[]> list = new ArrayList<>();//줄어들 칸 리스트

		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nx = i+dx[d];
					int ny = j+dy[d];
					if(0<=nx && nx<n && 0<=ny && ny<n && arr[nx][ny] > 0) cnt++;
				}
				if(cnt < 3) list.add(new int[] {i,j});
			}
		}
		for(int[] x:list) {
			if(arr[x[0]][x[1]] > 0) arr[x[0]][x[1]]--;
		}
		
	}
	private static void rotate(int i, int j, int level) {
		for (int a = 0; a < Math.pow(2, level-1); a++) { //테두리 수 = 2^(단계-1)
			int x = i + a;
			int y = j + a;
			int e = (int)Math.pow(2,level) - 2*a; //테두리별  탐색개수 
			
			int[] temp = new int[e-1];
			for (int k = 0; k < temp.length; k++) {
				temp[k] = arr[x+k][y];
			}
			//1.
			for (int k = 0; k < e-1; k++) arr[x+k][y] = arr[x+e-1][y+k];
			//2.
			for (int k = 0; k < e-1; k++) arr[x+e-1][y+k] = arr[x+e-1-k][y+e-1];
			//3.
			for (int k = 0; k < e-1; k++) arr[x+1+k][y+e-1] = arr[x][y+1+k];
			//4.
			for (int k = 0; k < e-1; k++) arr[x][y+e-1-k] = temp[k];
		}
	}

}