package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n+1][n+1];
		//버스비용이 0일수도 있으니 -1로 초기화
		for (int i = 1; i <= n; i++) {
			Arrays.fill(arr[i], -1);
		}
		
		StringTokenizer st;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			if(arr[from][to] == -1) arr[from][to] = w;
			else if(arr[from][to] > w) arr[from][to] = w;
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[n+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[start] = 0;
		
		boolean[] visit = new boolean[n+1];
		
		for (int i = 0; i < n; i++) {
			int min = Integer.MAX_VALUE;
			int current = 0;
			//최소값 찾기
			for (int j = 1; j <= n; j++) {
				if(!visit[j] && dp[j] < min) {
					min = dp[j];
					current = j;
				}
			}
			visit[current] = true;
			//경유하면서 최소값 업데이트 해주기
			for (int j = 1; j <= n; j++) {
				if(!visit[j] && arr[current][j] != -1 &&
						dp[j] > arr[current][j] + dp[current]) {
					dp[j] = arr[current][j] + dp[current];
				}
			}
		}
		System.out.println(dp[end]);
	}
}