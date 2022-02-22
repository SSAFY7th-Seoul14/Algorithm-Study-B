package com.ssafy.study;

import java.io.*;
import java.util.*;
// BOJ / 전력난 / G4 / 15분
// https://www.acmicpc.net/problem/6497
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
	public int compareTo(Edge o) {
		return this.weight-o.weight;
	}
}
public class Main_6497 {
	static int[] parents;
	
	
	public static int find(int x) {
		if(parents[x]==x) return x;
		return parents[x]=find(parents[x]);
	}
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return;
		if(a<b) parents[bRoot]=aRoot;
		else parents[aRoot]=bRoot;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			if(m==0 && n==0)
				break;
			
			parents = new int[m];
			for(int i=0;i<m;i++)
				parents[i]=i;
			
			Edge[] list = new Edge[n];
			int total_money = 0; //기존 총 액수
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				total_money +=weight;
				list[i] = new Edge(a,b,weight);
			}
			Arrays.sort(list);
			int save_money = 0; //절약 비용
			int cnt=0; //간선 수
			for(Edge e: list) {
				if(find(e.a)!=find(e.b)) {
					union(e.a, e.b);
					cnt++;
					save_money +=e.weight;
				}
				if(cnt==n-1)
					break;
			}
			System.out.println(total_money-save_money);
			
		}
	}

}
