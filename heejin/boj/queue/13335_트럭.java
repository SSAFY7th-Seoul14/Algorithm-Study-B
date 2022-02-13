package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ / 트럭 / S1 / 2시간+
// https://www.acmicpc.net/problem/13335
// 프로그래머스 비슷한 문제: https://www.acmicpc.net/problem/5430
public class Main_13335 {
	
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		Queue<Integer> truck = new LinkedList<>(); //트럭 무게 저장
		Queue<Integer> bridge = new LinkedList<>(); //다리 무게 저장
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		//다리에 0 삽입
		for(int i=0;i<W;i++)
			bridge.offer(0);
		

		//다리 무게 입력받기
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			truck.offer(Integer.parseInt(st.nextToken()));
		}
		
		int total_weight = 0; //다리의 총 weight
		int time=0;
		
		while(!bridge.isEmpty()) {
			time++;
			total_weight -= bridge.poll();
			if(!truck.isEmpty()) {
				if(total_weight+truck.peek()<=L) {
					int weight = truck.poll();
					bridge.offer(weight);
					total_weight += weight;
				}
				else	//다리 무게 합이 L 이하 될 때까지 대기
					bridge.offer(0);
			}
		}
		System.out.println(time);
	}
}
