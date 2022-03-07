package com;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] h;
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int c = sc.nextInt();
		h = new int[n];
		for(int i=0; i<n; i++) {
			h[i] = sc.nextInt();
		}
		//이분탐색하기 위해 먼저 정렬
		Arrays.sort(h);
		
		int min = 1; //최소거리
		int max = h[n-1] - h[0] + 1; // 최소거리의 최댓값
		
		while(min < max) {
			int mid = (max + min) / 2;
			
			//가능한게 c보다 작으면 max 줄임
			if(possible(mid) < c) {
				max = mid;
			//c이상이면 최소거리를 1씩 증가해본다
			}else {
				min = mid + 1;
			}
		}
		System.out.println(min - 1);
	}

	//distance에 대해서 몇대를 설치할 수 있는지 return
	private static int possible(int distance) {
		
		int cnt = 1;
		//제일 마지막에 공유기 설치한 곳
		//여기랑 비교해서 설치할 수 있는지 파악
		int last = h[0]; 
		
		for (int i = 1; i < h.length; i++) {
			int now = h[i];
			
			if(now - last >= distance) {
				cnt++;
				last = now;
			}
		}
		return cnt;
	}
}