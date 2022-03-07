package com;

import java.util.*;
import java.io.*;

public class Main {
	static int[] dp;
	static int[][] arr;
	static int n,m,x;

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1][m+1];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			arr[from][to] = time;
		}
		
		int[] time = dijkstra(x); //오고가고 시간을 저장할 배열
		for(int i=1; i<=n; i++) {
			if(i==x) continue;
			time[i] += dijkstra(i)[x];
		}
		
		int max = 0;
		for(int i=1; i<=n; i++) {
			max = Math.max(time[i], max);
		}
		System.out.println(max);
	}
	private static int[] dijkstra(int start) {
		dp = new int[n+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[start] = 0;
		boolean[] visit = new boolean[n+1];
		
		for(int i=1; i<n+1; i++) {
			//각 집에서 X번으로 가는 최단경로 찾으면 stop
			if(start != x && visit[x]==true) break;
			
			int cur = 0;
			int min = Integer.MAX_VALUE;
			for(int j=1; j<n+1; j++) {
				if(!visit[j] && dp[j] < min) {
					min = dp[j]; 
					cur = j;
				}
			}
			visit[cur] = true;
			
			for(int j=1; j<n+1; j++) {
				if(!visit[j] && arr[cur][j] != 0 &&dp[j] > dp[cur] + arr[cur][j]) {
					dp[j] = dp[cur] + arr[cur][j];
				}
			}
		}
		
		return dp;
	}
	
}