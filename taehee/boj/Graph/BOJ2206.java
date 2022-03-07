package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
//2206번 다시 풀어보기
//visit 배열 3차원 [n][m][2] 벽부쉇는지 안부쉇는지
	static class P{
		int x,y,cnt;
		boolean flag; //부쉇으면  true
		public P(int x, int y, int cnt, boolean flag) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.flag = flag;
		}	
	}
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m];
		
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		boolean[][][] visit = new boolean[n][m][2];
		
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		
		Queue<P> q = new LinkedList<>();
		q.add(new P(0,0,1,false));
		visit[0][0][0] = true;
		while(!q.isEmpty()) {
			P now = q.poll();
			
			if(now.x == n-1 && now.y == m-1) {
				System.out.println(now.cnt);
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(0<= nx && nx < n && 0<= ny && ny < m) {
					//벽 x
					if(arr[nx][ny] == 0) {
						//부신적o 
						if(now.flag == true && !visit[nx][ny][1]) {
							visit[nx][ny][1] = true;
							q.add(new P(nx,ny,now.cnt+1,true));
						//부신적  x
						}else if(now.flag == false && !visit[nx][ny][0]){
							visit[nx][ny][0] = true;
							q.add(new P(nx,ny,now.cnt+1,false));
						}
					}else {
						if(now.flag == false && !visit[nx][ny][0]) {
							visit[nx][ny][1] = true;
							q.add(new P(nx,ny,now.cnt+1,true));							
						}
					}
				}
			}
		}
		System.out.println(-1);
	}
}