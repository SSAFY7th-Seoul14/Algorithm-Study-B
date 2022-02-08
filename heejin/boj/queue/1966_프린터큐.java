package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ 1966 프린터큐 Silver3
public class BOJ_1966 {
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		Queue<int[]> datas = new LinkedList<int[]>();
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); //중요도 저장(내림차순)
		StringTokenizer st;
		int num, curMax, cnt; //num: 입력 수, curMax: queue 안의 최고값, cnt: 총 횟수
		int [] cur; //cur: queue 맨 앞
		
		for(int t=1;t<=T;t++) {
			cnt=0; //TC마다 초기화
			pq.clear();
			datas.clear();
			
			st = new StringTokenizer(br.readLine()," ");
			N= Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			//문서 입력받기
			st = new StringTokenizer(br.readLine()," ");
			for(int i=0;i<N;i++) {
				num = Integer.parseInt(st.nextToken());
				datas.offer(new int[] {i,num});	
				pq.add(num);
			}
			curMax = pq.poll(); //현재 최고값
			
			//처리
			while(true) {
				if(!datas.isEmpty()) {
					cur=datas.poll();
					
					if(cur[1]==curMax) { //현재 큐 안의 맨 앞 값이 최대값일 경우
						cnt++; //문서 출력 작업 실행
						if(cur[0]==M) //만약 최대값인 것이  M번째 문서일 경우
							break;
						else {
							curMax = pq.poll(); //M번째 문서 아닐 경우, 최대값 갱신
						}
					}
					else
						datas.offer(cur); //큐 안에 다른 최대 값이 있으면, 현재 값 큐 맨 뒤로 이동
				}
			}
			System.out.println(cnt);
		}
			
	}
}
