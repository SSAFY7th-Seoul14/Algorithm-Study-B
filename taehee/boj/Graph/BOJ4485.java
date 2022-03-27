package com;

import java.io.*;
import java.util.*;

public class Main {
			
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int t = 1;
    	while(true) {
    		int n = Integer.parseInt(br.readLine());
    		if(n == 0) break;
    		
    		int[] dx = {1,-1,0,0};
    		int[] dy = {0,0,1,-1};
    		
    		int[][] arr = new int[n][n];
    		int[][] dp = new int[n][n];
    		boolean[][] visit = new boolean[n][n];
    		
    		for (int i = 0; i < n; i++) {
    			StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
    		dp[0][0] = arr[0][0];
    		
    		Queue<int[]> q = new LinkedList<>();
    		q.add(new int[] {0,0});
    		visit[0][0] = true;
    		
    		while(!q.isEmpty()) {
    			int[] p = q.poll();
    			int x = p[0];
    			int y = p[1];
    			for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if(0<= nx && nx < n && 0<= ny && ny < n) {
						if(!visit[nx][ny]) {
							visit[nx][ny] = true;
							dp[nx][ny] = dp[x][y] + arr[nx][ny];
							q.add(new int[] {nx,ny});
						}else{
							if(dp[nx][ny] > dp[x][y] + arr[nx][ny]) {
								dp[nx][ny] = dp[x][y] + arr[nx][ny];
								q.add(new int[] {nx,ny});
							}
						}
					}
				}
    		}
    		System.out.printf("Problem %d: %d\n",t,dp[n-1][n-1]);
    		t++;
    	}
    	
    }

}