package com;

import java.util.*;
import java.io.*;

public class Main {

	static int[] roots;
	static int[] tree;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = 1;

		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			if(n==0 && m==0) break;
			
			tree = new int[n+1];
			roots = new int[n+1];
			for (int i = 1; i < n+1; i++) {
				roots[i] = i;
			}
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				union(from,to);
			}
			
			Set<Integer> set = new HashSet<>();
			for (int i = 1; i < n+1; i++) {
				if(roots[i] != 0) set.add(roots[i]);
			}
			int trees = set.size();
			
			if(trees == 0) System.out.println("Case "+tc++ +": No trees.");
			else if(trees == 1) System.out.println("Case "+tc++ +": There is one tree.");				
			else System.out.println("Case "+tc++ +": A forest of "+trees+" trees.");
		}
	}

	private static void union(int from, int to) {
		int a = find(from);
		int b = find(to);
		
		if(a==b) { //같으면 사이클 생김
			for (int i = 1; i < roots.length; i++) {
				if(roots[i] == a) roots[i] = 0;
			}
		}else if(a==0 || b==0) { //같지 않지만 한쪽이 사이클
			for (int i = 1; i < roots.length; i++) {
				if(roots[i] == a || roots[i] == b) roots[i] = 0;
			}
		}else {
			roots[b] = a;
		}
	}

	private static int find(int a) {
		if(roots[a] == a) return a;
		return roots[a] = find(roots[a]);
	}
	
}