package com.ssafy.study;

import java.util.*;
import java.io.*;

// BOJ / 도시 분할 계획 / G4 /
//https://www.acmicpc.net/problem/1647

class Edge implements Comparable<Edge>{
	int a;
	int b;
	int weight;
	
	Edge(int a, int b, int weight){
		this.a=a;
		this.b=b;
		this.weight=weight;
	}
	
	@Override
	public int compareTo(Edge o) { //가중치 오름차순 정렬
		return this.weight-o.weight;
	}
}


public class Main_1647 {
	static int N,M; //N: 집, M: 길
	static int[] parents;
	
	public static int find(int x) { //집합의 부모 찾기
		if(x==parents[x]) return x;
		return parents[x]=find(parents[x]);
	}
	
	public static void union(int a, int b) { //하나의 집합으로 합치기
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return;
		if(aRoot<bRoot) parents[bRoot] = aRoot;
		else parents[aRoot] = bRoot;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		Edge[] list = new Edge[M];
		
		for(int i=2;i<N+1;i++)
			parents[i]=i;
		//M개의 간선 입력받기
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			list[i] = new Edge(a,b,weight);
		}
		
		//간선 리스트 가중치 오름차 순으로 정렬
		Arrays.sort(list);
		
		int result = 0; //신장트리 최소비용
		int max = Integer.MIN_VALUE; //신장 트리의 간선 중 최대 값
		int cnt=0; //간선 개수
		for(int i=0;i<M;i++) {
			if(find(list[i].a)!=find(list[i].b)) {
				union(list[i].a,list[i].b);
				cnt++;
				result +=list[i].weight;
				max = Math.max(max, list[i].weight);
			}
			if(cnt==N-1) //신장트리 완성
				break;
		}
		System.out.println(result-max); //신장 트리 중 최대값 간선 빼서 마을 2개로 나눔
	}

}
