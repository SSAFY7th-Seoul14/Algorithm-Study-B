package com;

import java.io.*;
import java.util.*;

public class Main {
	
	static boolean[] visit;
	static LinkedList<Integer>[] tree;
	static int[] dp;
	static int[][] arr;//다리 다이너마이트 개수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			visit = new boolean[n+1];
			tree = new LinkedList[n+1];
			for (int i = 1; i < n+1; i++) {
				tree[i] = new LinkedList<Integer>();
			}
			dp = new int[n+1];
			arr = new int[n+1][n+1];
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				tree[a].add(b);
				tree[b].add(a);
				arr[a][b] = d;
				arr[b][a] = d;
			}
			
			dfs(1);
			System.out.println(dp[1]);
		}
		
		
	}

	private static void dfs(int x) {
		visit[x] = true;
		for(int i : tree[x]) {
			if(!visit[i]) {
				dfs(i);
				dp[x] += Math.min(dp[i], arr[x][i]);
			}
		}
		if(tree[x].size() == 1) {
			dp[x] = arr[tree[x].get(0)][x];
		}
	}

}
