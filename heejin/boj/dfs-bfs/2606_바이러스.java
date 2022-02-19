package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// BOJ / 바이러스 / S3 / 15분
// https://www.acmicpc.net/problem/2606
public class Main_2606 {
	static List<ArrayList<Integer>> graph;
	static int N, V;//컴퓨터의 수, 네트워크 연결 수
	static boolean[] visited;
	static int cnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		V = Integer.parseInt(br.readLine());
		
		visited = new boolean[N+1];
		//graph 초기화
		graph = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<N+1;i++) {
			graph.add(new ArrayList<Integer>());
			
		}
		//graph 네트워크 연결 상태 입력받기
		for(int i=0;i<V;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			graph.get(node1).add(node2);
			graph.get(node2).add(node1);
		}
		
		dfs(1); //1번 컴퓨터에 의해 웜 바이러스 걸리게 되는 컴퓨터 수
		System.out.println(cnt);
	}

	private static void dfs(int n) {
		visited[n] = true;
		for(int i=0;i<graph.get(n).size();i++) {
			int next = graph.get(n).get(i);
			if(!visited[next]) {
				cnt++;
				dfs(next);
			}
		}
	}

}
