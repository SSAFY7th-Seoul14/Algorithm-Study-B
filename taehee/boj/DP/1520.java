package com;

import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int m,n,cnt=0;
	static int[][] arr,dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken()); //세로 크기
		n = Integer.parseInt(st.nextToken()); //가로 크기
		arr = new int[m][n]; //산 배열		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dp[i][j] = -1;
			}
		}
		
		System.out.println(dfs(0,0));
	}

	private static int dfs(int x, int y) {
		if(x==m-1 && y==n-1) {
			return 1;
		}
		if(dp[x][y] != -1) { //방문한 경우 return...memoization
			return dp[x][y]; 
		}else {
			dp[x][y] = 0; //방문하면 0으로 바꿔준다
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(0<=nx && nx<m && 0<=ny && ny<n) { //범위안에있고
					if(arr[nx][ny] < arr[x][y]) { //내리막일때만
						dp[x][y] += dfs(nx,ny);
					}
				}
			}			
		}
		return dp[x][y];
	}

}
