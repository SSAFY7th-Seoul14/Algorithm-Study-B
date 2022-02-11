package com.ssafy.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//[BOJ] 요세푸스 문제 / S5 / 12분
//https://www.acmicpc.net/problem/1158
public class Main_1158 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Integer> q = new LinkedList<>();
		sb.append("<");
		
		//N, K 입력
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//숫자 1~N까지 Queue에 입력
		for(int i=1;i<=N;i++) {
			q.offer(i);
		}
	
		//원형 큐에 넣어서 k번 반복시키기
		while(!q.isEmpty()) {
			for(int i=1;i<=K;i++) {
				if(i==K && !q.isEmpty()) { //K번째 숫자이면 제거
					sb.append(q.poll()+", ");
				}
				else { //K번째 숫자 아니면 Queue 맨 뒤로 이동
					if(!q.isEmpty())
						q.offer(q.poll());
				}
			}
		}
		sb.setLength(sb.length()-2);
		sb.append(">");
		bw.write(sb.toString());
		bw.close();
		br.close();
		
	}
}
