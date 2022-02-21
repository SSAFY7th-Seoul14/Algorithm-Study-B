package com;

import java.util.Scanner;

public class Main {

	static int n,s;
	static int cnt = 0;
	static int[] arr;
	static boolean[] isSelected;
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		s = sc.nextInt();
		arr = new int[n];
		isSelected = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		subset(0);
		System.out.println( (s==0) ? cnt -1 : cnt );
	}
	
	private static void subset(int count) {
		if(count == n) {
			int sum = 0;
			for (int i = 0; i < n; i++) {
				if(isSelected[i]) {
					sum += arr[i];
				}
			}
			if(sum == s) {
				cnt++;
			}
			return;
		}
		isSelected[count] = true;
		subset(count+1);
		isSelected[count] = false;
		subset(count+1);
		
	}

}
