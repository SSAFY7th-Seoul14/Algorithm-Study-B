package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
	static class Edge implements Comparable<Edge>{
		int from, to;
		double d;

		public Edge(int from, int to, double d) {
			this.from = from;
			this.to = to;
			this.d = d;
		}

		@Override
		public int compareTo(Edge e) {
			return (int)(this.d - e.d);
		}
		
	}
	static int[] roots;
	static int n;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t<=T; t++) {
			n = Integer.parseInt(br.readLine());
			roots = new int[n];
			String[] x = br.readLine().split(" ");
			String[] y = br.readLine().split(" ");
			double E = Double.parseDouble(br.readLine());
			
			ArrayList<Edge> eList = new ArrayList<>();
			
			for(int i=0; i<n; i++) {
				for(int j=i+1; j<n; j++) {
					int x1 = Integer.parseInt(x[i]);
					int y1 = Integer.parseInt(y[i]);
					int x2 = Integer.parseInt(x[j]);
					int y2 = Integer.parseInt(y[j]);
					double d = Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2);
					eList.add(new Edge(i,j,d));
				}
			}
			//오름차순 정렬
			Collections.sort(eList);
			//makeSet
			for(int i=0; i<n; i++) {
				roots[i] = i;
			}
			//간선 선택
			int cnt = 0;
			double total = 0;
			for(Edge e : eList) {
				if(union(e.from, e.to)) {
					total += E * e.d;
					if(++cnt == n-1) break;
				}
			}
			System.out.println("#"+t+" "+Math.round(total));
		}
	}
	private static boolean union(int from, int to) {
		int a = findSet(from);
		int b = findSet(to);
		if(a==b) return false;
		
		roots[b] = a;
		return true;
	}
	private static int findSet(int a) {
		if(roots[a] == a) return a;
		return roots[a] = findSet(roots[a]);
	}
	
}
