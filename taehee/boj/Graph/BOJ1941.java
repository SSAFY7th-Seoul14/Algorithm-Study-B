package com;

import java.io.*;
import java.util.*;

public class Main {
	static class Count{
		int x,y,totalCnt, sCnt;
		ArrayList<int[]> routes;
		
		public Count(int x, int y, int totalCnt, int sCnt, ArrayList<int[]> routes) {
			this.x = x;
			this.y = y;
			this.totalCnt = totalCnt;
			this.sCnt = sCnt;
			this.routes = routes;
		}
	}
	static char[][] arr = new char[5][5];
	static boolean[][] visit = new boolean[5][5];
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	for (int i = 0; i < 5; i++) {
			arr[i] = br.readLine().toCharArray();
		}
    	
    	int cnt = 0;
    	for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				cnt += bfs(i,j);
			}
		}
    	System.out.println(cnt);
    }

	private static int bfs(int x, int y) {
		int ans = 0;
		
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		
		Queue<Count> q = new LinkedList<>();
		ArrayList<int[]> list = new ArrayList<>();
		list.add(new int[] {x,y});
		
		if(arr[x][y] == 'S') q.add(new Count(x,y,1,1,list));
		else q.add(new Count(x,y,1,0,list));
		
		while(!q.isEmpty()) {
			Count c = q.poll();
			if(c.totalCnt == 7) {
				if(c.sCnt >= 4) {//S 4명이상일때만
					boolean flag = false;
					for(int[] route : c.routes) {
						if( !visit[route[0]][route[1]] ) { //하나라도 이거로 7공주 구성한 적 x
							flag = true; //7공주 구성가능 
						}
					}
					if(flag) {
						for(int[] route : c.routes) {
							visit[route[0]][route[1]] = true; // 7공주 구성한 경로 true로
						}
						ans++;
					}					
				}
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = c.x + dx[i];
				int ny = c.y + dy[i];
				if(0<= nx && nx < 5 && 0<= ny && ny <5 && !inRoutes(nx,ny,c.routes)) {
					ArrayList<int[]> a = new ArrayList<>();
					for(int[] l : c.routes) {
						a.add(l);
					}
					a.add(new int[] {nx,ny});
					
					if(arr[nx][ny] == 'S') {
						q.add(new Count(nx,ny,c.totalCnt+1,c.sCnt+1,a));
					}else {
						q.add(new Count(nx,ny,c.totalCnt+1,c.sCnt,a));						
					}
				}
			}
		}
			
		return ans;
	}

//	[nx,ny] 가 지나온경로에 있는지
	private static boolean inRoutes(int nx, int ny, ArrayList<int[]> routes) {
		for(int[] route : routes) {
			if(nx==route[0] && ny==route[1]) return true; 
		}
		return false;
	}

}