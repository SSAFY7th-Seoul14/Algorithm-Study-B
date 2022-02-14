package com.ssafy;

import java.util.Scanner;

public class BOJ1436 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int i=1;
		int cnt = 0;
		while(true) {
			i++;
			if(String.valueOf(i).contains("666")) cnt++;
			if(cnt == n) {
				System.out.println(i);
				break;
			}
		}
	}

}
