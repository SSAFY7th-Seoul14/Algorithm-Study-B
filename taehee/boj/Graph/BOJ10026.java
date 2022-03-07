package com;

import java.util.Scanner;

public class Main {
	static int n;
	static char[][] arr;
	static boolean[][] visit;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new char[n][];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.next().toCharArray();
		}
		
		int cnt = 0; //색약
		visit = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(!visit[i][j]) {
					dfs(arr[i][j],i,j,true);
					cnt++;
					
				}
			}
		}
		int cnt2= 0; //평범
		visit = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(!visit[i][j]) {
					dfs(arr[i][j],i,j,false);
					cnt2++;
				}
					
			}
		}
		System.out.println(cnt2+ " " + cnt);
	}

	private static void dfs(char c, int x, int y, boolean b) {
		visit[x][y] = true;
		if(b) {//r==g b
			for(int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(0<= nx && nx < n && 0<= ny && ny < n && !visit[nx][ny]) {
					if((c=='R' || c=='G') && (arr[nx][ny]=='R' || arr[nx][ny]=='G')) {
						dfs(c,nx,ny,true);
					}
					else if(c=='B' && arr[nx][ny] == 'B') {
						dfs(c,nx,ny,true);
					}
				}
			}
		}else {//r g b
			for(int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(0<= nx && nx < n && 0<= ny && ny < n) {
					if(!visit[nx][ny] && arr[nx][ny] == c) {
						dfs(c,nx,ny,false);
					}
				}
			}
		}
		return;
	}
}