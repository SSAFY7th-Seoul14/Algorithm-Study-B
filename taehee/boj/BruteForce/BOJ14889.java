package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n,r;
	static int diff = Integer.MAX_VALUE;
	static int[][] s;
	static int[] numbers;
	static boolean isSelected[];
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		r = n/2;
		s = new int[n][n];
		numbers = new int[n];
		//조합에서 선택됐으면 true, 안됐으면  false
		isSelected = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				s[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0,0);
		System.out.println(diff);
	}

	private static void comb(int cnt, int start) {
		if(cnt == r) {
			int startSum = 0;
			int linkSum = 0;
			
			for(int i=0; i<n; i++) {
				for(int j=0;j<n; j++) {
					if(i != j) {
						//i j 모두 선택됐을 때
						if(isSelected[i] && isSelected[j]) {
							startSum += s[i][j];
						}
						//i j 모두 선택 안됐을 때
						else if(!isSelected[i] && !isSelected[j]) {
							linkSum += s[i][j];
						}						
					}
				}
			}
			if(Math.abs(startSum - linkSum) < diff) {
				diff = Math.abs(startSum - linkSum);
			}
		}
		for(int i=start; i<n; i++) {
			isSelected[i] = true;
			comb(cnt+1, i+1);
			isSelected[i] = false;
		}
	}
	
}
