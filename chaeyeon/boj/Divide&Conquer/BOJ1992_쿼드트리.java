package com.ssafy.webX;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//20220216
//30분
public class BOJ1992_쿼드트리 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		
		conquer(0, 0, N);
		System.out.println(sb);
		
	}
	
	public static void conquer(int r, int c, int l) {
		int sum = sum(r,c,l);
		if(sum == 0) {
			sb.append(0);
			return;
		}
		if(sum == l*l) {
			sb.append(1);
			return;
		}
		int half = l/2;
		sb.append("(");
		conquer(r,c,half);
		conquer(r,c+half,half);
		conquer(r+half,c,half);
		conquer(r+half,c+half,half);
		sb.append(")");
		
	}

	private static int sum(int r, int c, int l) {
		int sum = 0;
		for(int i = r; i < r+l; i++) {
			for (int j = c; j < c+l; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}

}
