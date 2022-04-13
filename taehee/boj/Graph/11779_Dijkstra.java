package com;

import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		Node from;
		int idx,cost;
		
		public Node(Node from, int idx, int cost) {
			this.from = from;
			this.idx = idx;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int[][] arr = new int[n+1][n+1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if(arr[from][to]!=0) arr[from][to] = Math.min(arr[from][to], cost);
			else arr[from][to] = cost;
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		Node[] dp = new Node[n+1];
		boolean[] visit = new boolean[n+1];
		for (int i = 1; i < n+1; i++) {
			dp[i] = new Node(null,i,Integer.MAX_VALUE);
		}
		dp[start] = new Node(null,start,0);
		for (int i = 1; i < n+1; i++) {
			int min = Integer.MAX_VALUE;
			int cur = 0;
			for (int j = 1; j < n+1; j++) {
				if(!visit[j] && dp[j].cost < min) {
					min = dp[j].cost;
					cur = j;
				}
			}
			visit[cur] = true;
			for (int j = 1; j < n+1; j++) {
				if(!visit[j] && arr[cur][j] != 0 
						&& dp[j].cost > dp[cur].cost + arr[cur][j]) {
					int tmp = dp[cur].cost + arr[cur][j];
					dp[j] = new Node(dp[cur],j,tmp);
				}
			}
		}
		System.out.println(dp[end].cost);
		ArrayList<Integer> route = new ArrayList<>();
		Node node = dp[end];
		while(node.from != null) {
			route.add(0, node.idx);
			node = node.from;
			if(node.from==null) route.add(0,node.idx);
		}
		System.out.println(route.size());
		for(int r : route) {
			System.out.print(r+" ");
		}
	}

}