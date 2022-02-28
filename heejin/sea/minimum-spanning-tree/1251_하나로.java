package com.ssafy.study;

import java.io.*;
import java.util.*;

// SEA / 하나로 / D4 / 45분
//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD&categoryId=AV15StKqAQkCFAYD
class Node{
	int num;
	int x;
	int y;
	public Node(int num, int x, int y) {
		this.num = num;
		this.x = x;
		this.y = y;
	}
}

class Edge implements Comparable<Edge>{
	int from;
	int to;
	long weight;
	public Edge(int from, int to, long weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	@Override
	public int compareTo(Edge o) {
		return Long.compare(this.weight, o.weight);
	}

}

public class Solution_1251 {
	static int N;
	static int[] parents;
	public static void makeSet() {
		for(int i=0;i<N;i++)
			parents[i]=i;
	}
	public static int find(int x) {
		if(x==parents[x]) return x;
		return parents[x]=find(parents[x]);
	}
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return;
		if(aRoot<bRoot) parents[bRoot]=aRoot;
		else parents[aRoot] = bRoot;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			long res= Long.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			parents = new int[N];
			
			//정점들의 x,y좌표 입력받기
			int[] xlist = new int[N];
			int[] ylist = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				xlist[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				ylist[i] = Integer.parseInt(st.nextToken());
			}
			//이율 입력받기
			double E = Double.parseDouble(br.readLine());
			//N개의 정점 리스트 만들기
			Node[] nodes = new Node[N];
			for(int i=0;i<N;i++) {
				nodes[i] = new Node(i,xlist[i],ylist[i]);
			}
			//정점리스트를 기반으로 간선 리스트 만들기
			Edge[] edges = new Edge[N*(N-1)/2];
			int idx=0;
			for(int i=0;i<N;i++) {
				for(int j=i+1;j<N;j++) {
					Node from = nodes[i];
					Node to = nodes[j];
					long weight =(long)Math.pow(from.x-to.x,2)+(long)Math.pow(from.y-to.y,2);
					edges[idx++] = new Edge(nodes[i].num,nodes[j].num, weight);
				}
			}
			
			//간선 리스트를 간선 기준으로 오름차순 정렬
			Arrays.sort(edges);

			makeSet();
			int cnt=0;
			res=0;
			for(int i=0;i<N*(N-1)/2;i++) {
				
				if(find(edges[i].from)!=find(edges[i].to)) {
					union(edges[i].from, edges[i].to);
					cnt++;
					res += edges[i].weight;
				}
				if(cnt==N)
					break;
			}
			res = Math.round(res*E);
			System.out.println("#"+t+" "+res);
		}
	}
}
