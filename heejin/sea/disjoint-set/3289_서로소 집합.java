package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// SEA / 서로소 집합 / D4 / 15분
//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJKA6qr2oDFAWr
public class Solution_3289 {
	static int N,M;
	static int parents[];
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			//make set
			parents = new int[N+1];
			for(int i=1;i<N+1;i++) {
				parents[i]=i;
			}
			
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				switch(cmd) {
				case 0: 
					union(a,b);
					break;
				case 1:
					if(find(a)==find(b))
						sb.append(1);
					else
						sb.append(0);
					break;
				}
			}
			sb.append("\n");
			
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return;
		if(aRoot<bRoot) parents[bRoot]=aRoot;
		else parents[aRoot]=bRoot;
		
	}
	private static int find(int x) {
		if(parents[x]==x) return x;
		return parents[x]=find(parents[x]);

	}

}
