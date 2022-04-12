package com;

import java.io.*;
import java.util.*;

public class Solution {
	static final int inf = 100_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine()); // 지도크기
			int[][] arr = new int[n][n];
			int[][] dp = new int[n][n];
			for (int i = 0; i < n; i++) {
				String s = br.readLine();
				for (int j = 0; j < n; j++) {
					arr[i][j] = s.charAt(j) - '0';
					dp[i][j] = inf;
				}
			}
			int[] dx = {1,-1,0,0};
			int[] dy = {0,0,1,-1};
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] {0,0});
			dp[0][0] = 0;
			while(!q.isEmpty()) {
				int[] c = q.poll();
				int x = c[0];
				int y = c[1];
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if(0<= nx && nx<n && 0<=ny && ny<n 
							&& dp[nx][ny] > dp[x][y] + arr[nx][ny]) {
						dp[nx][ny] = dp[x][y] + arr[nx][ny];
						q.add(new int[] {nx,ny});
					}
				}
			}
			
	        System.out.println("#"+t+" "+dp[n-1][n-1]);
		}

	}
}
