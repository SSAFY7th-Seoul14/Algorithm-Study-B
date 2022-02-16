package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ / DFS와 BFS / S2 / 45분
// https://www.acmicpc.net/problem/1260
public class Main_1260 {
	
	static List<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 정점 개수
		int M = Integer.parseInt(st.nextToken()); // 간선 개수
		int V = Integer.parseInt(st.nextToken()); // 시작 정점 번호
		
		
		//그래프 초기화
		for(int i=0;i<N+1;i++) {
			graph.add(new ArrayList<Integer>());
		}
		visited = new boolean[N+1];
		//그래프 정보 입력받기
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			//node1에 연결된 노드(node2) 정보 저장
			graph.get(node1).add(node2);
			graph.get(node2).add(node1);
		}

		for(int i=0;i<graph.size();i++) {
			Collections.sort(graph.get(i));
		}

		dfs(V);
		
		System.out.println();
		visited = new boolean[N+1];
		bfs(V);
		
	}
	
	public static void dfs(int x) {
		//현재 노드 방문 처리
		visited[x]= true;
		System.out.print(x+" ");
		//현재 노드와 연결된 다른 노드들 재귀적 방문
		for(int i=0;i<graph.get(x).size();i++) {
			int y = graph.get(x).get(i);
			if(!visited[y])
				dfs(y);
		}
	}
	
	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		//현재 노드 방문처리
		visited[start] = true;
		//큐가 빌 때까지 반복
		while(!q.isEmpty()) {
			//큐에서 원소 뽑기
			int x = q.poll();
			System.out.print(x+" ");
			for(int i=0;i<graph.get(x).size();i++) {
				int y = graph.get(x).get(i);
				if(!visited[y]) {
					q.offer(y);
					visited[y]=true;
				}
			}
		}
	}

}
