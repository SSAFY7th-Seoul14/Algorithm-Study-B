package com.ssafy.study;

import java.io.*;
import java.util.*;
// BOJ / 토마토 / G5 / 30분
// https://www.acmicpc.net/problem/7576
public class Main_7576 {
	static int M,N;
	static int map[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static Queue<int[]> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) { //토마토(시작점) 큐에 추가
					q.offer(new int[] {i,j});
				}
			}
		}
		
		bfs();
		
	}

	private static void bfs() {		
		while(!q.isEmpty()) {

			int[] cur = q.poll();
			int cx = cur[0]; int cy = cur[1];
			
			for(int i=0;i<4;i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if(nx>=0 && nx<N && ny>=0 && ny<M && map[nx][ny]==0) {
					q.offer(new int[] {nx,ny});
					map[nx][ny]=map[cx][cy]+1; //토마토 익히기

				}
			}
		}
		int day = Integer.MIN_VALUE; //토마토 익는데 걸린 시간 계산
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==0) {
					System.out.println(-1);
					return;
				}
				day = Math.max(day, map[i][j]);
					
			}
		}
		System.out.println(day-1);
		
	}
}
