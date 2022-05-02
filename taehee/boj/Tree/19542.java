package com;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] depth;
	static ArrayList<Integer>[] link;
	static int move,n,s,d;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		link = new ArrayList[n+1];
		for (int i = 0; i < n+1; i++) {
			link[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			link[a].add(b);
			link[b].add(a);
		}
		depth = new int[n+1];
		
		dfs(s,-1);
		
		System.out.println(2*move);
	}

	private static int dfs(int node, int pa) {
		for(int l : link[node]) {
			if(l != pa) {
				depth[node] = Math.max(depth[node], dfs(l,node)+1);
			}
		}
		if(node != s && depth[node] >= d) move++;
		return depth[node];
	}

}
