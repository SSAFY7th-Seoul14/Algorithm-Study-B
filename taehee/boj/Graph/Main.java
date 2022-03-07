package com;

import java.util.*;
import java.io.*;

public class Main {
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
	static int[] roots;
	static int n,m;

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			m = Integer.parseInt(st.nextToken()); //집
			n = Integer.parseInt(st.nextToken()); //길
			
			if(m==0 && n ==0) break;
			
			ArrayList<Edge> roads = new ArrayList<>();
			roots = new int[m];
			
			int total = 0;
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				total += w;
				roads.add(new Edge(from,to,w));
			}
			
			Collections.sort(roads);
			
			for(int i=0;i<m;i++) {
				roots[i] = i;
			}
			
			int cnt = 0;
			for(Edge road : roads) {
				if(union(road.from,road.to)) {
					total -= road.w;
					if(++cnt == m-1) break;
				}
			}
			System.out.println(total);
		}
	}
	private static int find(int a) {
		if(roots[a] == a) return a;
		return roots[a] = find(roots[a]);
	}
	private static boolean union(int a, int b) {
		int ra = find(a);
		int ba = find(b);
		
		if(ra == ba) return false;
		roots[ba] = ra;
		return true;
	}
	
}