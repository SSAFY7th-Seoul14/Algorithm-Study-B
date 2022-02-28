package com;

import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int tc = 0; tc<t; tc++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			int[][] arr = new int[n+1][n+1];
			for(int i=0; i<m; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				arr[x][y] = 1;
				arr[y][x] = 1;
			}
			System.out.println(n-1);
		}
	}
}