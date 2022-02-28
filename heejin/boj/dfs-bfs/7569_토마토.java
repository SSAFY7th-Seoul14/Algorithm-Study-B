package com.ssafy.study;

import java.io.*;
import java.util.*;

// BOJ / 토마토 / G5 / 20분
// https://www.acmicpc.net/problem/7569
public class Main_7569 {
	static int M,N,H;
	static int map[][][];
	static int dx[] = {-1,1,0,0,0,0}; //상, 하, 좌, 우, 앞, 뒤
	static int dy[] = {0,0,-1,1,0,0};
	static int dz[] = {0,0,0,0,-1,1};
	static Queue<int[]> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][N][M];
		
		for(int h=0;h<H;h++) {
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					map[h][i][j] = Integer.parseInt(st.nextToken());
					if(map[h][i][j]==1) {
						q.offer(new int[] {h,i,j});
					}
				}
			}
		}
		bfs();
		
	}

	private static void bfs() {
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[1]; int cy = cur[2]; int cz = cur[0];
			for(int i=0;i<6;i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				int nz = cz + dz[i];
				if(nx>=0 && nx<N && ny>=0 && ny<M && nz>=0 && nz<H && map[nz][nx][ny]==0) {
					q.offer(new int[] {nz,nx,ny});
					map[nz][nx][ny] = map[cz][cx][cy]+1;
				}
			}
		}
		int day = Integer.MIN_VALUE;
		
		for(int h=0;h<H;h++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[h][i][j]==0) {
						System.out.println(-1);
						return;
					}
					day = Math.max(day, map[h][i][j]);
					
				}
			}
		}
		System.out.println(day-1);
		
	}
	

}
