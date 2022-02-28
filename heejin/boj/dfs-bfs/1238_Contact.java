package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// SEA / Contact / D4 / 55분
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15B1cKAKwCFAYD
public class Solution_1238 {
	
	static int res; //마지막에 연락 받는 사람(정답)
	static int N; // 입력받는 데이터 길이
	static List<ArrayList<Integer>> graph;
	static int[] visited;
	static int MAX = 100; //최대 사람 수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		for(int t=1;t<=10;t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			visited = new int[MAX+1];
			res=0;
			int start = Integer.parseInt(st.nextToken()); //시작 사람
			
			graph = new ArrayList<ArrayList<Integer>>(); //그래프 초기화
			for(int i=0;i<MAX+1;i++) {
				graph.add(new ArrayList<Integer>());
			}
			
			st = new StringTokenizer(br.readLine()); //그래프 간선 형성
			while(st.hasMoreTokens()) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph.get(from).add(to);
			}
			
			bfs(start);
			//출력
			sb.append("#").append(t).append(" ").append(res).append("\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
	private static void bfs(int start) {
		visited[start]=1; //시작점은 1단계
		Queue<Integer> q= new LinkedList<>();
		q.offer(start);
		
		int cur=0, next;
		while(!q.isEmpty()) {
			cur = q.poll();
			for(int i=0;i<graph.get(cur).size();i++) {
				next = graph.get(cur).get(i);
				if(visited[next]==0) { //아직 방문하지 않았을 경우
					q.offer(next);
					visited[next]=visited[cur]+1; //다음 단계
				}
			}
		}
		int max=1;
		for(int i=0;i<MAX+1;i++) { //최고 깊이 값(가장 나중에 연락받은 시간 구하기)
			if(max<visited[i])
				max = visited[i];
		}

		for(int i=0;i<MAX+1;i++) { //가장 나중에 연락받은 애들 중, 큰 숫자가 res(최종 정답)
			if(visited[i]==max) {
				res = Math.max(res, i);
			}
		}
		
	}

}
