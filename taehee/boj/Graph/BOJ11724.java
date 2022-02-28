package com;

import java.util.Scanner;

public class Main {
	
	static int[][] arr;
	static boolean[] visited;
	static int cnt = 0;
	static int n,m;
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		arr = new int[n+1][n+1];
		visited = new boolean[n+1];
		
		for(int i=0; i<m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			arr[x][y] = 1;
			arr[y][x] = 1;
		}
		
		for(int i=1; i<n+1; i++) {
			if(!visited[i]) {
				dfs(i);
				cnt++;
			}
		}
		System.out.println(cnt);
	}

	private static void dfs(int x) {
		visited[x] = true;
		for(int y=1; y<n+1; y++) {
			if(arr[x][y] == 1 && !visited[y]) {
				dfs(y);
			}
		}
	}
}