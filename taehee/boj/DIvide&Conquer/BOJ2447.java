package com;

import java.util.*;
import java.io.*;

public class Main {

	static char[][] arr;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		arr = new char[n][n];
		star(0,0,n);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	private static void star(int x, int y, int cnt) {
		if(cnt==1) {
			arr[x][y] = '*';
			return;
		}
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(i==1 && j==1) continue;
				star(x + i * cnt/3, y + j * cnt/3, cnt/3);
			}
		}
		return;
	}

}