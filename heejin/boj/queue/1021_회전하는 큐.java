package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//BOJ / 회전하는 큐 / S4 / 
// https://www.acmicpc.net/problem/1021
public class Main_1021 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Integer> dq = new ArrayList<>();
		int cnt=0;//총 연산 횟수
		
		int N = Integer.parseInt(st.nextToken()); // 큐 크기
		int M = Integer.parseInt(st.nextToken()); // 뽑는 수 개수
		
		for(int i=1;i<=N;i++) //N개의 원소 갖는 덱 생성
			dq.add(i);
		
		int popIdx[] = new int[M]; //뽑아야 하는 원소들
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++)
			popIdx[i] = Integer.parseInt(st.nextToken());
		
		
		for(int i=0;i<M;i++) {
				if(dq.get(0)==popIdx[i]) { //1번연산
					dq.remove(0);
				}
				else {
					int idx = dq.indexOf(popIdx[i]);
					if(idx<=dq.size()/2) {
						//왼쪽
						while(!(dq.get(0)==popIdx[i])) {
							int front = dq.get(0);
							
							dq.add(front);
							dq.remove(0);
							cnt++;
						}
						dq.remove(0);
					}
					else {
						//오른쪽
						while(!(dq.get(0)==popIdx[i])) {
							int rear = dq.get(dq.size()-1);
							
							dq.add(0,rear);
							dq.remove(dq.size()-1);
							cnt++;
						}
						dq.remove(0);
					}
				}
		}
		System.out.println(cnt);
		br.close();
		
	}

}
