package com;

import java.util.*;
import java.io.*;

public class Main {
	static class Pipe{
		int x1,y1,x2,y2,shape;

		public Pipe(int x1, int y1, int x2, int y2, int shape) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.shape = shape; //가로 0 세로 1 대각선 2
		}
	}
	static int[][] d = {{0,1},{1,0},{1,1}}; //미는방향
	static int[][] arr;
	static int n;
	static Queue<Pipe> q = new LinkedList<>();
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0;
		if(arr[n-1][n-1] == 1) {
			System.out.println(0);
			return;
		}
		q.add(new Pipe(0,0,0,1,0));
		while(!q.isEmpty()) {
			Pipe p = q.poll();
			if(p.x2== n-1 && p.y2 == n-1) {
				cnt++;
				continue;
			}
			move(p);
		}
		System.out.println(cnt);
	}

	private static boolean possible(int x1, int y1, int x2, int y2, int i) {
		//범위 안이면서
		if(x2<n && y2<n && arr[x2][y2]==0) {
			//대각선으로 밀때
			if(i==2) {
				if(arr[x2-1][y2] == 0 && arr[x2][y2-1] ==0) return true;
			}else {
				return true;
			}
		}
		return false;
	}
	private static void move(Pipe p) {
		for (int i = 0; i < 3; i++) {
			if(p.shape==0 && i==1) continue;
			if(p.shape==1 && i==0) continue;
			int nx1 = p.x2;
			int ny1 = p.y2;
			int nx2 = p.x2 + d[i][0];
			int ny2 = p.y2 + d[i][1];
			int s = i;
			if(possible(nx1,ny1,nx2,ny2,i)) {
				q.add(new Pipe(nx1,ny1,nx2,ny2,s));							
			}
		}
	}

} 