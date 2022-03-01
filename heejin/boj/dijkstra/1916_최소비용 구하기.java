package com.study;

import java.io.*;
import java.util.*;


// BOJ / 최소비용 구하기 / G5 / 40분
// https://www.acmicpc.net/problem/1916

class Node implements Comparable<Node>{
	int to; //목적지
	int weight; //비용
	
	
	public Node(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return this.weight-o.weight;
	}		
}
public class Main_1916 {
	static int N,M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		List<ArrayList<Node>> graph = new ArrayList<>();
		//graph 초기화
		for(int i=0;i<N+1;i++) {
			graph.add(new ArrayList<Node>());
		}
		//graph 입력받기
		for(int i=0;i<M;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to,weight));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start =  Integer.parseInt(st.nextToken());
		int end =  Integer.parseInt(st.nextToken());
		
		int distance[] = new int[N+1];
		boolean visited[] = new boolean[N+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start]=0;
		pq.offer(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			int cur = current.to;
			
			if(cur==end)
				break;
			
			//현재 노드 이미 처리된 적 있으면 무시
			if(visited[cur]) continue;
			
			visited[cur]=true;
			//현재 노드와 연결된 다른 인접 노드들 확인
			for(Node node: graph.get(cur)) {
				//현재 노드 거쳐서, 다른 노드 이동하는 거리가 더 짧은 경우
				if(!visited[node.to] && distance[node.to]> distance[cur] + node.weight) {
					distance[node.to] = distance[cur] + node.weight;
					pq.add(new Node(node.to, distance[node.to]));
				}
			}
		}
		System.out.println(distance[end]);
	}
}
