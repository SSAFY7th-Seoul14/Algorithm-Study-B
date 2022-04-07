package com;

import java.io.*;
import java.util.*;

public class Main {
	static class Point{
		int x,y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static ArrayList<Point> c = new ArrayList<>(); // 치킨집 리스트
	static ArrayList<Point> h = new ArrayList<>(); // 집 리스트
	static Point[] chicken;
	static int[][] arr;
	static int n,m,min=Integer.MAX_VALUE;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		chicken = new Point[m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1) h.add(new Point(i,j));
				if(arr[i][j] == 2) c.add(new Point(i,j));
			}
		}
		comb(0,0);
		System.out.println(min);
	}

	private static void comb(int start, int cnt) {
		if(cnt==m) { //선택된 m개의 치킨집에 대해서만
			int[][] copy = new int[n][n];
			for(Point p : chicken) {
				copy[p.x][p.y] = 2;
			}
			for(Point p : h) {
				copy[p.x][p.y] = 1;
			}
			
			int res = 0;
			for (int i = 0; i < h.size(); i++) { // 각 집에 대해서 최솟값 구해주기
				res += dist(copy,i);
			}
			min = Math.min(res, min);
			return;
		}
		for (int i = start; i < c.size(); i++) {
			chicken[cnt] = c.get(i);
			comb(i+1,cnt+1);
		}
	}

	private static int dist(int[][] copy, int i) {
		int ans = Integer.MAX_VALUE;
		for(Point p : chicken) {
			ans = Math.min(ans, Math.abs(h.get(i).x-p.x) + Math.abs(h.get(i).y-p.y));
		}
		return ans;
	}
	
}