package com.hw;

import java.util.Scanner;

public class BOJ2961 {

	static int[] s,b;
	static int n;
	static int ans = Integer.MAX_VALUE;
	static boolean[] isSelected;
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		s = new int[n]; //신맛배열
		b = new int[n]; //쓴맛배열
		isSelected = new boolean[n];
		
		for(int i=0; i<n; i++) {
			s[i] = sc.nextInt();
			b[i] = sc.nextInt();
		}
		
		subset(0);
		System.out.println(ans);
	}

	private static void subset(int cnt) {
		if(cnt == n) {
			int sum_s = 1;
			int sum_b = 0;
			//부분집합 완성됐을 때 선택된 신맛 다곱하고 쓴맛 다더하기 
			for (int i = 0; i < n; i++) {
				if(isSelected[i]) {
					sum_s *= s[i];
					sum_b += b[i];
				}
			}
			//sum_s =1 이고 sum_b = 0이면 전부 선택안한 부분집합이므로 무시해야함
			if( sum_s != 1 && sum_b != 0) {
				int diff = Math.abs(sum_s - sum_b);
				//현재 차이값과 비교해서 작은 값으로 업데이트
				ans = Math.min(ans, diff);
				
				return;
			}
			return;
		}
		
		isSelected[cnt] = true;
		subset(cnt+1);
		isSelected[cnt] = false;
		subset(cnt+1);
	}

}
