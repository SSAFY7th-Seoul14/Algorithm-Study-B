package com;

import java.util.*;
import java.io.*;

public class Main {
	static int[] roots;
	static class Edge implements Comparable<Edge>{
		int from,to,w;

		public Edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		roots = new int[n+1];
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= n; i++) {
			roots[i] = i;
			
		}
		Edge[] list = new Edge[m];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[i] = new Edge(from,to,weight);
		}
		Arrays.sort(list);
		
		int cnt = 0;
		int ans = 0;
		for(Edge e : list) {
			if(union(e.from, e.to)) {
				ans += e.w;
				if(++cnt == n-1) break;
			}
		}
		System.out.println(ans);

	}
	private static int find(int a) {
		if(roots[a] == a) return a;
		return roots[a] = find(roots[a]);
	}
	private static boolean union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		
		if(ra == rb) return false;
		roots[rb] = ra;
		return true;
	}
	
}