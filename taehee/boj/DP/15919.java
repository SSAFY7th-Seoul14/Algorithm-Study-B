package com;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //여행구간 길이
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken()); //여행기간 후보 수
		
		int[][] arr = new int[m][2];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[i][0] = a;
			arr[i][1] = b;
		}
		Arrays.sort(arr,(x1,x2)->{ //끝나는순서 기준, 같으면 시작기준
			if(x1[1] == x2[1]) return x1[0]-x2[0];
			return x1[1]-x2[1];
		});
		boolean[] visit = new boolean[n];
		int start = 1; //여행시작
		int end = 1; //여행끝
		int maxCnt = 0;
		for(int[] cur: arr) {
			if(cur[0] >= end) {
				int cnt = cur[0] - end;
				maxCnt = Math.max(maxCnt, cnt);
				start = cur[0];
				end = cur[1]+1;//다음 여행 가능 일자
			}
		}
		System.out.println(maxCnt);
	}
}