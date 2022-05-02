package com;

import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point>{
		int x;
		int y;
		int cnt;
		
		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Point o) {
			return this.cnt - o.cnt;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			char[] a = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				arr[i][j] = a[j] - '0';
			}
		}
		
		boolean[][] visit = new boolean[n][m];
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		PriorityQueue<Point> q = new PriorityQueue<>();
		q.offer(new Point(0,0,0));
		visit[0][0] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			if(p.x==n-1 && p.y==m-1) {
				System.out.println(p.cnt);
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(0<=nx && nx<n && 0<=ny && ny<m && !visit[nx][ny]) {
					if(arr[nx][ny] == 0) {
						q.offer(new Point(nx,ny,p.cnt));
					}
					else {
						q.offer(new Point(nx,ny,p.cnt+1));
					}
					visit[nx][ny] = true;
				}
			}
		}
	}

}
