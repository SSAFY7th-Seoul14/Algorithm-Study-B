package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

//BOJ / 연결 요소의 개수 / S2 / 40분
// https://www.acmicpc.net/problem/11724
public class Main_11724 {
	static List<ArrayList<Integer>> graph;
	static boolean[] visited;
	static int res; // 연결 요소 개수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N= Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<ArrayList<Integer>>();
		visited = new boolean[N+1];
		
		//그래프 초기화
		for(int i=0;i<N+1;i++)
			graph.add(new ArrayList<Integer>());
		
		// Graph 간선 형성
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		for(int i=1;i<N+1;i++) {
			if(!visited[i]) {
				dfs(i);
				res++; //연결요소 +1
			}	
		}
		System.out.println(res);
	}

	private static void dfs(int x) {
		
		visited[x]=true;
		for(int i=0;i<graph.get(x).size();i++) {
			int cur = graph.get(x).get(i);
			if(!visited[cur]) {
				dfs(cur);
			}
				
		}
		
	}
	

}
