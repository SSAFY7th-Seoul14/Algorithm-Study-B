package com;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[][] arr = new int[2][n];
			
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=1; i<n; i++) {
				if(i==1) {
					arr[0][i] += arr[1][0];
					arr[1][i] += arr[0][0];
				}else {
					arr[0][i] += Math.max(arr[1][i-2],arr[1][i-1]);
					arr[1][i] += Math.max(arr[0][i-2],arr[0][i-1]);
				}
			}
			System.out.println(Math.max(arr[0][n-1], arr[1][n-1]));
		}
	}
	
}