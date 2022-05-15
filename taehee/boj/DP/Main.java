package com;

import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge>{
		int num,cnt;
		long w;

		public Edge(int num, long w, int cnt) {
			this.num = num;
			this.w = w;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.w < o.w) return -1;
			return 1;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		ArrayList<Edge>[] arr = new ArrayList[n+1];
		long[][] dp = new long[n+1][k+1]; // i,j : i까지 j개 포장해서 온 비용
		long MAX = Long.MAX_VALUE;
		for (int i = 0; i < n+1; i++) {
			arr[i] = new ArrayList<>();
			Arrays.fill(dp[i], MAX);
		}
		dp[1][0] = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			arr[from].add(new Edge(to,w,0));
			arr[to].add(new Edge(from,w,0));
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1,0,0));
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			//현재좌표로오는 비용 > 현재까지오는 dp
			if(now.w > dp[now.num][now.cnt]) continue;
			for(Edge next : arr[now.num]) {
				long nw = next.w + now.w;
				//포장 x
				if(nw < dp[next.num][now.cnt]) {
					dp[next.num][now.cnt] = nw;
					pq.add(new Edge(next.num, nw, now.cnt));
				}
				//포장 o
				if(now.cnt < k && now.w < dp[next.num][now.cnt+1]) {
					dp[next.num][now.cnt+1] = now.w;
					pq.add(new Edge(next.num, now.w, now.cnt+1));
				}
			}
		}
		long min = MAX;
		for (int i = 0; i < k+1; i++) {
			min = Math.min(min, dp[n][i]);
		}
		System.out.println(min);
	}

}
