package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ / 유기농 배추 / S2 / 10분
// https://www.acmicpc.net/problem/1012
public class Main_1012 {
	
	static int T;
	static int map[][];
	static int bug; //배추흰지렁이 개수
	static int N,M,K; //세로, 가로, 배추 심어져 있는 개수
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for(int i=0;i<K;i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[y][x] = 1;
			}
			bug=0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j]==1 && !visited[i][j]) {
						dfs(i,j);
						bug++;
					}
				}
			}
			System.out.println(bug);
			
		}
	}
	private static void dfs(int x, int y) {
		visited[x][y]=true;
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx>=0 && nx <N && ny>=0 && ny<M) {
				if(isAvailable(nx,ny)) {
					dfs(nx,ny);
				}
			}
		}
		
		
	}
	private static boolean isAvailable(int x, int y) {
		if(map[x][y]==1 && !visited[x][y]) return true;
		return false;
	}

}
