package com;

import java.io.*;
import java.util.*;

public class Main {
	
	static int n,m;
	static ArrayList<ArrayList<Integer>> tree;
	static int[] depth; //root로부터 깊이 저장
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		tree = new ArrayList<>(); //각 정점과 연결된 점들
		for (int i = 0; i < n+1; i++) {
			tree.add(new ArrayList<Integer>());
		}
		StringTokenizer st;
		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree.get(a).add(b);
			tree.get(b).add(a);
		}
		depth = new int[n+1];
		parent = new int[n+1];
		dfs(1,1);
		
		m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int ans = lca(a,b);
			System.out.println(ans);
		}
	}

	private static int lca(int a, int b) {
		int da = depth[a];
		int db = depth[b];
		
		if(da > db) {//a가 더 밑에
			while(da!=db) {
				da--;
				a = parent[a];
			}
		}else { //b가 더밑에
			while(da!=db) {
				db--;
				b = parent[b];
			}			
		}
		while(a!=b) { //깊이 같은데 다를때
			a = parent[a];
			b = parent[b];			
		}
		return a;
	}

	private static void dfs(int from, int cnt) {
		depth[from] = cnt;
		for(int next : tree.get(from)) {
			if(depth[next] == 0) {
				dfs(next,cnt+1);
				parent[next] = from;
			}
		}
	}
}