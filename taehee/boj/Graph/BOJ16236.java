package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236{
	static class Fish implements Comparable<Fish>{
		int x,y,d;

		public Fish(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public int compareTo(Fish o) {
			if(this.d == o.d) {
				if(this.x == o.x) {
					return this.y - o.y;
				}
				return this.x-o.x;
			}
			return this.d-o.d;
		}
		
	}
	static int n;
	static int[][] arr;
	static int x =0 ,y =0;//상어좌표
	static int sharkSize = 2;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 9) {
					x = i;
					y = j;
				}
			}
		}
		int cnt = 0; //물고기 먹은 횟수
		int time = 0; //이동시간

		while(true) {
			//bfs로 다음 좌표알아내기
			ArrayList<Fish> next = bfs();
			if(next.size()==0) break;
			
			Collections.sort(next);
			int nx = next.get(0).x;
			int ny = next.get(0).y;
			int count = next.get(0).d;
			//상어이동
			arr[x][y] = 0;
			x = nx;
			y = ny;
			arr[x][y] = 9;
			
			cnt++;
			time += count;
			if(cnt == sharkSize) {
				sharkSize++;
				cnt = 0;
			}
		}
		System.out.println(time);
	}

	private static ArrayList<Fish> bfs() {
		int[] dx = {-1,0,1,0};
		int[] dy = {0,-1,0,1}; 
		ArrayList<Fish> ans = new ArrayList<>();
		boolean[][] visit = new boolean[n][n];
		visit[x][y] = true;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x,y,0});//상어 현재좌표로 시작
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int x = tmp[0];
			int y = tmp[1];
			int cnt = tmp[2];
			//지금 최소거리랑 같으면 스탑
			if(ans.size() > 0 && ans.get(0).d == cnt) continue;
			
			for(int d=0; d<4; d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				//범위안에있고 
				if(0<=nx && nx<n && 0<= ny && ny<n) {
					//방문안헀고 상어size 이하일때 이동
					if(arr[nx][ny] <= sharkSize && !visit[nx][ny]) {
						if(arr[nx][ny] != 0 && arr[nx][ny] < sharkSize) {
							//이동할 곳 찾음
							ans.add(new Fish(nx,ny,cnt+1));
						}else {
							q.offer(new int[] {nx,ny,cnt+1});
							visit[nx][ny] = true;
						}
					}
				}
			}
		}
		return ans;
	}
}