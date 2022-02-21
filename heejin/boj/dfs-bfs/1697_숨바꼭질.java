package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ / 숨바꼭질 / S1 / 50분
// https://www.acmicpc.net/problem/1697
// 참고:https://velog.io/@leeinae/Algorithm-%EB%B0%B1%EC%A4%801697-%EC%88%A8%EB%B0%94%EA%BC%AD%EC%A7%88
public class Main_1697 {
	static int N,K;
	static int[] time;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //수빈이 위치
		K = Integer.parseInt(st.nextToken()); //동생 위치
		
		time = new int[100001];
		
		if(N==K) // 둘 위치 같을 경우
			System.out.println(0); 
		else
			bfs(N);
		
		
	}
	private static void bfs(int n) {
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(n);


		while(!q.isEmpty()) {
			int cur = q.poll(); //현재 위치
			for(int i=0;i<3;i++) {
				int next=0; // 다음 위치
				if(i==0) { //X-1 이동
					next = cur-1;
				}
				else if(i==1) { //X+1 이동
					next = cur+1;
				}
				else { // 2*X 이동
					next = cur*2;
				}
				
				if(next==K) {
					System.out.println(time[cur]+1);
					return;
				}
				
				if(next>=0 && next<time.length && time[next]==0) {//아직 방문하지 않았고, 범위 안에 있을 경우
					q.add(next);
					time[next] = time[cur]+1;
				}
			}
		}
	}
	
}
