package com;

import java.util.*;
import java.io.*;

public class Main {
	
	static int n,m;
	static int[][] arr;
	static ArrayList<int[]> ices = new ArrayList<>();
	static boolean[][] visit;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] > 0) ices.add(new int[] {i,j,arr[i][j]});
			}
		}
		
		int year = 0;
		while(true) {
			visit = new boolean[n][m];
			int cnt = count();
			if(cnt >= 2) {
				System.out.println(year);
				break;
			}
			else if(cnt == 1) {
				melt();
				year++;
			}
			else {
				System.out.println(0);
				break;
			}
		}
	}
	private static void melt() {
		ArrayList<int[]> next = new ArrayList<>();
		//덮어쓰기위한 복사본
		int[][] copy = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		for (int i=0; i<ices.size(); i++) {
			int x = ices.get(i)[0];
			int y = ices.get(i)[1];
			int before = ices.get(i)[2];
			int seaCnt = 0;
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(0<= nx && nx < n && 0<= ny && ny < m && arr[nx][ny] == 0) {
					seaCnt++;
				}
			}
			int after = (before - seaCnt <= 0) ? 0 : before - seaCnt;
			
			copy[x][y] = after;
			if(after!=0) next.add(new int[] {x,y,after});
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = copy[i][j];
			}
		}
		ices = next;
	}
	private static int count() {
		int cnt = 0;
		
		for(int i=0; i<ices.size(); i++) {
			int x = ices.get(i)[0];
			int y = ices.get(i)[1];
			if(!visit[x][y]) {
				visit[x][y] = true;
				dfs(x,y);
				cnt++;
			}
		}
		return cnt;
	}
	private static void dfs(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(0<= nx && nx < n && 0<= ny && ny < m && arr[nx][ny] > 0 && !visit[nx][ny]) {
				visit[nx][ny] = true;
				dfs(nx,ny);
			}
		}
		return;
	}
} 