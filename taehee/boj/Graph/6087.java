package com;

import java.io.*;
import java.util.*;

public class Main {
	static class Point{
		int x,y,d,cnt;

		public Point(int x, int y, int d, int cnt) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.cnt = cnt;
		}
		
	}
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		char[][] arr = new char[h][w];
		int[][] dp = new int[h][w]; // 최소값을 저장하기 위한 배열
		int[][] laser = new int[2][2];
		int idx = 0;		
		for (int i = 0; i < h; i++) {
			arr[i] = br.readLine().toCharArray();
			for (int j = 0; j < w; j++) {
				if(arr[i][j] == 'C') {
					laser[idx][0] = i;
					laser[idx++][1] = j;
				}
			}
		}
		for (int i = 0; i < h; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		dp[laser[0][0]][laser[0][1]] = -1; //0번째 레이저좌표를 출발점으로
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(laser[0][0],laser[0][1],4,-1));
		while(!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x+dx[i];
				int ny = p.y+dy[i];
				if(0<=nx && nx<h && 0<=ny && ny<w && arr[nx][ny] != '*') {//범위안이고 벽아닐때만
					int temp = p.cnt;
					if(p.d != i && p.d != -1) temp++;
					if(dp[nx][ny] < temp) continue;
					
					dp[nx][ny] = temp;
					q.add(new Point(nx,ny,i,temp));
				}
			}
		}
		System.out.println(dp[laser[1][0]][laser[1][1]]);
	}

}