package com;

import java.util.Scanner;

public class Main {

	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int[] op = new int[4];
	static int[] number;
	static int n;
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		//수열
		number = new int[n];
		for (int i = 0; i < n; i++) {
			number[i] = sc.nextInt();
		}
		
		for(int i=0; i<4; i++) {
			op[i] = sc.nextInt();
		}
		
		dfs(number[0],1);
		
		System.out.println(max);
		System.out.println(min);
	}

	private static void dfs(int num, int cnt) {
		if(cnt == n) {
			if(max < num) max = num;
			if(min > num) min = num;
			return;
		}
		for (int i = 0; i < 4; i++) {
			if(op[i] > 0) {
				op[i]--;
				
				if(i==0) dfs(num+number[cnt], cnt+1);
				if(i==1) dfs(num-number[cnt], cnt+1);
				if(i==2) dfs(num*number[cnt], cnt+1);
				if(i==3) dfs(num/number[cnt], cnt+1);
				
				op[i]++;
			}
		}
	}
	
}