package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// BOJ / 촌수계산 / S2 / 25분
//https://www.acmicpc.net/problem/2644
public class Main_2644 {
	static List<ArrayList<Integer>> graph;
	static boolean visited[];
	static int n1, n2; //찾고자 하는 사람 2명
	static boolean flag; //촌수 찾기 가능 여부
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		n1 = Integer.parseInt(st.nextToken());
		n2 = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		
		visited = new boolean[N+1];
		//그래프 초기화
		graph = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<=N;i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph.get(x).add(y); //y의 부모 x
			graph.get(y).add(x);
		}
		
		dfs(n1,0);
		if(!flag) //촌수 못 찾으면
			System.out.println(-1);
		
	
	}
	private static void dfs(int n, int count) {
		if(n==n2) {
			System.out.println(count);
			flag=true;
		}
		
		visited[n]=true;
		for(int i=0;i<graph.get(n).size();i++) {
			int cur = graph.get(n).get(i);
			if(!visited[cur]) dfs(cur, count+1);
		}
	}

}
