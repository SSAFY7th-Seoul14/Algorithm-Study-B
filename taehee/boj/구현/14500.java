package com;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int n,m,max=0;
	static boolean[][] visit;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visit = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				visit[i][j] = true;
				dfs(i,j,1,arr[i][j]);
				visit[i][j] = false;
				cross(i,j);
			}
		}
		System.out.println(max);
	}

	private static void cross(int x, int y) {
		for (int i = 0; i < 4; i++) {//4개 모양에 대해
			int sum = arr[x][y];
			for (int d = 0; d < 4; d++) {
				if(i==d) continue;
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(0<=nx && nx<n && 0<=ny && ny<m) {
					sum += arr[nx][ny];
				}else { //범위안 아니면 안되는 모양
					break;
				}
			}
			max = Math.max(sum, max);
		}
	}

	private static void dfs(int x, int y, int cnt, int sum) {
		if(cnt==4) {
			max = Math.max(sum, max);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(0<=nx && nx<n && 0<=ny && ny<m && !visit[nx][ny]) {
				visit[nx][ny] = true;
				dfs(nx,ny,cnt+1,sum+arr[nx][ny]);
				visit[nx][ny] = false;
			}
		}
	}

}
