package com;

import java.io.*;
import java.util.*;

public class Main {
	static class Point{
		int x, y, n, cnt;

		public Point(int x, int y, int n, int cnt) {
			this.x = x;
			this.y = y;
			this.n = n; //방문한 더러운칸
			this.cnt = cnt; //이동횟수
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			if(w+h == 0) break;
			
			char[][] arr = new char[h][w];
			boolean[][][] visit = new boolean[h][w][1024];
			
			ArrayList<int[]> dirty = new ArrayList<>();
			Queue<Point> q = new LinkedList<>();
			
			for (int i = 0; i < h; i++) {
				arr[i] = br.readLine().toCharArray();
				for (int j = 0; j < w; j++) {
					if(arr[i][j] == 'o') { //청소기
						q.offer(new Point(i,j,0,0));
						visit[i][j][0] = true;
					}else if(arr[i][j] == '*') { //더러운 칸
						dirty.add(new int[] {i,j});
					}
				}
			}
			int size = dirty.size(); //더러운 칸 개수
			int ans = 10000;
			int[] dx = {1,-1,0,0};
			int[] dy = {0,0,1,-1};
			
			while(!q.isEmpty()) {
				Point p = q.poll();
				if(p.n == (int)Math.pow(2, size) -1 ) {
					ans = Math.min(ans, p.cnt);
					continue;
				}
				for (int i = 0; i < 4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					if(0<=nx && nx<h && 0<=ny && ny<w 
							&& arr[nx][ny] != 'x' && !visit[nx][ny][p.n]) {
						int n = p.n; //방문 칸
						if(arr[nx][ny] == '*') {
							int idx = 0; // 몇번째 더러운칸인지
							for(int k=0; k<size; k++) {
								if(dirty.get(k)[0] == nx && dirty.get(k)[1] == ny) {
									idx = k;
									break;
								}
							}
							n |= 1<<idx;
						}
						visit[nx][ny][n] = true;
						q.offer(new Point(nx,ny,n,p.cnt+1));
					}
				}
			}
			System.out.println(ans==10000 ? -1 : ans);
		}
	}
}