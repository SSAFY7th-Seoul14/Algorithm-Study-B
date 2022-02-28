package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// SEA / 창용 마을 무리의 개수 / D4 / 25분
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWngfZVa9XwDFAQU&categoryId=AWngfZVa9XwDFAQU
public class Solution_7465 {
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			//make-set
			parents = new int[N+1];
			for(int i=1;i<N+1;i++)
				parents[i]=i;
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a,b);
			}

			Set<Integer> p = new HashSet<Integer>();
			for(int i=1;i<N+1;i++)
				p.add(find(parents[i]));
			sb.append("#").append(t).append(" ").append(p.size()).append("\n");
			
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
		
	}

	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return;
		if(aRoot<bRoot) parents[bRoot]=aRoot;
		else parents[aRoot]=bRoot;
	}

	private static int find(int a) {
		if(a==parents[a]) return a;
		return parents[a]=find(parents[a]);
	}

}
