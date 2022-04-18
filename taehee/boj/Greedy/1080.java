package com;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		int[][] a = new int[n][m];
		int[][] b = new int[n][m];
		for (int i = 0; i < 2*n; i++) {
			String str = br.readLine();
			if(i<n) {
				for (int j = 0; j < m; j++) {
					a[i][j] = str.charAt(j)-'0';
				}
			}else {
				for (int j = 0; j < m; j++) {
					b[i-n][j] = str.charAt(j)-'0';
				}				
			}
		}
		int cnt = 0;
		for (int i = 0; i < n-2; i++) {
			for (int j = 0; j < m-2; j++) {
				if(a[i][j] != b[i][j]) {
					flip(a,i,j);
					cnt++;
				}
			}
		}
		boolean flag = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(a[i][j] != b[i][j]) {
					flag = false;
					break;
				}
			}
		}
		System.out.println(flag ? cnt : -1);
		
	}
	private static void flip(int[][] arr, int x, int y) {
		for(int i=x; i<x+3; i++) {
			for(int j=y; j<y+3; j++) {
				arr[i][j] = 1 - arr[i][j];
			}
		}
	}
}