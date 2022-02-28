package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//BOJ / 상근이의 여행 / S3 / 15분
//https://www.acmicpc.net/problem/9372
public class Main_9372 {
	static int N,M;
	static List<ArrayList<Integer>> graph;
	static boolean[] visited;
	static int cnt; //비행기 횟수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			cnt=0;
			StringTokenizer st=  new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			//그래프 선언
			graph = new ArrayList<ArrayList<Integer>>();
			for(int i=0;i<N+1;i++) {
				graph.add(new ArrayList<Integer>());
			}
			visited = new boolean[N+1];
			
			//비행 스케줄 입력
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph.get(a).add(b);
				graph.get(b).add(a);
			}
			
			dfs(1);
			System.out.println(cnt);
		}
		
	}
	private static void dfs(int x) {
		visited[x]=true;
		for(int i=0;i<graph.get(x).size();i++) {
			int cur = graph.get(x).get(i);
			if(!visited[cur]) {
				dfs(cur);
				cnt++;
			}
		}
		
	}
	

}
