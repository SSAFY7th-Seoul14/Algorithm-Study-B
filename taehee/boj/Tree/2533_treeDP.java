package com;

import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static boolean[] visit;
	static LinkedList<Integer>[] tree;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new int[n+1][2]; //0이면 얼리x, 1이면 얼리
		visit = new boolean[n+1];
		tree = new LinkedList[n+1];
		for (int i = 1; i < n+1; i++) {
			tree[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			tree[u].add(v);
			tree[v].add(u);
		}
		int root = 1;
		dfs(root);
		System.out.println(Math.min(dp[root][0], dp[root][1]));
	}

	private static void dfs(int root) {
		visit[root] = true;
		dp[root][0] = 0;
		dp[root][1] = 1;
		
		LinkedList<Integer> item = tree[root];
		for(int child : item) {
			if(!visit[child]) {
				dfs(child);
				dp[root][0] += dp[child][1];
				dp[root][1] += Math.min(dp[child][0], dp[child][1]);
			}
		}
	}

}
