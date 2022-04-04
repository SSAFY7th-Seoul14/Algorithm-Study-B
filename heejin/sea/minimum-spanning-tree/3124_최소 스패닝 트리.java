package com.sea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int from;
	int to;
	int weight;

	public Node(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) { // 가중치 순으로 오름차순
		return this.weight - o.weight;
	}

}

public class Main_3124 {
	static List<ArrayList<Node>> graph; // 간선리스트
	static int parents[];
	static int V, E;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			long res = 0;
			PriorityQueue<Node> pq = new PriorityQueue<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			graph = new ArrayList<ArrayList<Node>>();
			for(int i=0;i<V+1;i++)
				graph.add(new ArrayList<Node>());
			parents = new int[V+1];
			makeParents();
			for(int i=0;i<E;i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				graph.get(A).add(new Node(A, B, C));
				pq.add(new Node(A,B,C));
			}
			int cnt = 0;
			while(cnt!=V-1) {
				Node cur = pq.poll();
				if(union(cur.from, cur.to)) {
					cnt++;
					res += cur.weight;
				}
			}
			
			System.out.println("#" + t + " " + res);
		}
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return false;
		else if(aRoot<bRoot)
			parents[bRoot] = aRoot;
		else 
			parents[aRoot] = bRoot;
		return true;
		
	}

	private static int find(int x) {
		if(x==parents[x]) return x;
		return parents[x] = find(parents[x]);
	}

	private static void makeParents() {
		for (int i = 0; i <= V; i++)
			parents[i] = i;

	}
}
