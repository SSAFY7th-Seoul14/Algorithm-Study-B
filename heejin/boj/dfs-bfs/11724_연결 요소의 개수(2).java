package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//BOJ / 연결 요소의 개수 / S2 / 40분 / Union-find
// https://www.acmicpc.net/problem/11724
public class Main_11724_2 {
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N= Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//노드의 개수 만큼 부모 테이블 초기화
		parent = new int[N+1];
		
		//부모 테이블을 자기 자신으로 초기화
		for(int i=1;i<N+1;i++) 
			parent[i]=i;
		
		// union 연산 진행
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a,b);
		}
		
		// 연결요소 개수 세기 - 집합 사용(중복x)
		Set<Integer> connect = new HashSet<>();
		for(int i=1;i<N+1;i++) {
			connect.add(find(parent[i]));
		}
		System.out.println(connect.size());
	}
	// 두 원소가 속한 집합 합치기
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a<b)
			parent[b]=a;
		else
			parent[a]=b;
		
	}
	//특정 원소가 속한 집합 찾기
	private static int find(int x) {
		if(parent[x]!=x) //루트 노드가 아니면, 루트노드 찾을 때까지 재귀호출
			parent[x]=find(parent[x]);
		return parent[x];
	}

}
