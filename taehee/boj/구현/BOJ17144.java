package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17144 {
	
	static class dust{
		int x,y,nx,ny,amount;

		public dust(int x, int y, int nx, int ny, int amount) {
			this.x = x;
			this.y = y;
			this.nx = nx;
			this.ny = ny;
			this.amount = amount;
		}
		
	}
	static int[][] arr;
	static int r,c;
	static int up, down; //공기청정기 (up,0),(down,0)
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,-1,0,1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());//몇초후
		
		arr = new int[r][c];
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == -1) {
					up = i-1;
					down = i;
				}
			}
		}
		
		for(int i =0; i<t; i++) {
			bfs();
			purify();
		}
		
		int ans = 0;
		for (int i = 0; i < r; i++) {
			for(int j = 0; j<c; j++) {
				if(arr[i][j] != 0) ans += arr[i][j];
			}
		}
		System.out.println(ans+2);
	}

	private static void purify() {
		//위 공기청정기
		//밑으로
		for(int i=up-1; i>0; i--) {
			arr[i][0] = arr[i-1][0];
		}
		//왼쪽
		for(int i=0; i<c-1; i++) {
			arr[0][i] = arr[0][i+1];
		}
		//위로
		for(int i=0; i<up; i++) {
			arr[i][c-1] = arr[i+1][c-1];
		}
		//오른쪽
		for(int i=c-1; i>0; i--) {
			arr[up][i] = arr[up][i-1];
		}
		arr[up][1] = 0;
		
		//아래 골기청정기
		//상
		for(int i=down+1 ; i< r-1; i++) {
			arr[i][0] = arr[i+1][0];
		}
		//좌
		for(int i = 0; i<c-1; i++) {
			arr[r-1][i] = arr[r-1][i+1];
		}
		//하
		for(int i=r-1; i>down; i--) {
			arr[i][c-1] = arr[i-1][c-1];
		}
		//우
		for(int i=c-1; i>0; i--) {
			arr[down][i] = arr[down][i-1];
		}
		arr[down][1] = 0;
	}

	private static void bfs() {

		Queue<dust> q = new LinkedList<>();
		for (int i = 0; i < r; i++) {
			for(int j = 0; j<c; j++) {
				if(arr[i][j] > 0) {
					for(int d=0; d<4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if(0<= nx && nx <r && 0<= ny && ny <c && arr[nx][ny] != -1) {
							q.add(new dust(i,j,nx,ny,arr[i][j]/5)); 
						}
					}
				};
			}
		}
		while(!q.isEmpty()) {
			dust d = q.poll();
			arr[d.x][d.y] -= d.amount;
			arr[d.nx][d.ny] += d.amount;
		}
	}

}
