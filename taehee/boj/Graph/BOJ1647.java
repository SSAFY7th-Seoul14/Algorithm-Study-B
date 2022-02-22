package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static class Edge implements Comparable<Edge>{
		int from, to , w;

		public Edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge e) {
			return this.w - e.w;
		}
		
	}
	
	static int[] roots;
	static int n;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Edge[] list = new Edge[m];
		roots = new int[n+1];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[i] = new Edge(a,b,w);
		}
		Arrays.sort(list);
		
		for(int i=1; i<n+1; i++) {
			roots[i] = i;
		}
		int cnt = 0;
		int ans = 0;
		for(Edge e : list) {
			if(union(e.from, e.to)) {
				ans += e.w;
				if(++cnt == n-2) break;
			}
		}
		System.out.println(ans);
	}

	private static boolean union(int a, int b) {
		int aRoot = root(a);
		int bRoot = root(b);
		
		if(aRoot == bRoot) return false;
		roots[bRoot] = aRoot;
		return true;
	}

	private static int root(int a) {
		if(roots[a] == a) return a;
		return roots[a] = root(roots[a]);
	}
}