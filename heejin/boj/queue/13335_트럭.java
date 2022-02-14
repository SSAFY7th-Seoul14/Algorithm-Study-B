package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ / Ʈ�� / S1 / 2�ð�+
// https://www.acmicpc.net/problem/13335
// ���α׷��ӽ� ����� ����: https://www.acmicpc.net/problem/5430
public class Main_13335 {
	
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		Queue<Integer> truck = new LinkedList<>(); //Ʈ�� ���� ����
		Queue<Integer> bridge = new LinkedList<>(); //�ٸ� ���� ����
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		//�ٸ��� 0 ����
		for(int i=0;i<W;i++)
			bridge.offer(0);
		

		//�ٸ� ���� �Է¹ޱ�
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			truck.offer(Integer.parseInt(st.nextToken()));
		}
		
		int total_weight = 0; //�ٸ��� �� weight
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
				else	//�ٸ� ���� ���� L ���� �� ������ ���
					bridge.offer(0);
			}
		}
		System.out.println(time);
	}
}
