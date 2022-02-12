package com.ssasfy;

import java.util.LinkedList;
import java.util.Scanner;

public class BOJ1966 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		
		for(int t=0; t<tc; t++) {
			int n = sc.nextInt(); //문서의 개수
			int m = sc.nextInt(); //궁금한 문서 몇번째
			int cnt = 0; //답
			
			LinkedList<int[]> q = new LinkedList<>();
			for(int i=0;i<n;i++) {
				q.offer(new int[] {i, sc.nextInt()}); //초기위치, 중요도 
			}
			
			while(true) {
				int[] item = q.poll();
				boolean isMax = true;
				
				for(int i=0;i<q.size(); i++) {
					if(item[1] < q.get(i)[1]) {
						q.offer(item);
						
						//큰 값 앞에거를 전부 뒤로 보내기
						for(int j=0; j<i; j++) {
							q.offer(q.poll());
						}
						isMax = false;
						break;
					}
				}
				
				if(isMax) {
					cnt++;
					if(item[0] == m) break;
				}
			}
			System.out.println(cnt);
		}
	}

}
