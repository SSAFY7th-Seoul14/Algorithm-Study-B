package com.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13335 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int t = 0;//시간
		int onRoad = 0; // 다리 위 트럭무게합

		Queue<Integer> truck = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n;i++) {
			truck.offer(Integer.parseInt(st.nextToken()));
		}

		Queue<Integer> q = new LinkedList<>(); //다리
		for (int i = 0; i < w; i++) {
			q.add(0);
		}
		
		
		while(!q.isEmpty()) {
			t++;
			onRoad -= q.poll(); //왼쪽거 빼고
			
			if(!truck.isEmpty()) {
				if(onRoad + truck.peek() <= l) {
					int top = truck.poll();
					q.offer(top);
					onRoad += top;
				}else {
					q.offer(0);
				}
			}
		}
		System.out.println(t);
	}

}
