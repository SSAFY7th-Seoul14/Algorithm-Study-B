package com;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception{
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] meetings = new int[n][2];
		
		for(int i=0; i<n; i++) {
			meetings[i][0] = sc.nextInt();
			meetings[i][1] = sc.nextInt();
		}
		
		Arrays.sort(meetings, new Comparator<int[]>() {

			@Override
			public int compare(int[] m1, int[] m2) {
				if(m1[1] == m2[1]) {
					return m1[0] - m2[0];
				}
				return m1[1]  - m2[1];
			}
			
		});
		
		int end = meetings[0][1];
		int cnt = 1;
		for(int i=1; i<n; i++) {
			if(meetings[i][0] >= end) {
				cnt++;
				end = meetings[i][1];
			}
		}
		System.out.println(cnt);
	}
	
}
