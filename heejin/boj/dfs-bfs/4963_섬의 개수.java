package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ / 섬의 개수 / S2 / 20분
// https://www.acmicpc.net/problem/4963
public class Main_4963 {
	static int h,w; //높이, 너비
	static int[][] map;
	static boolean[][] visited;
	static int dx[]= {-1,-1,0,1,1,1,0,-1};
	static int dy[] = {0,1,1,1,0,-1,-1,-1};
	static int cnt; // 섬개수(정답)
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			cnt=0; // 섬 개수 초기화
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken()); //w, h 입력
			h = Integer.parseInt(st.nextToken());
			if(w==0 && h ==0) // 더 이상 입력 X
				break;
			map = new int[h][w]; //map, visited 초기화
			visited = new boolean[h][w];
			for(int i=0;i<h;i++) {
				 st = new StringTokenizer(br.readLine());
				for(int j=0;j<w;j++){
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					if(!visited[i][j] && map[i][j]==1) { //아직 방문하지 않은 섬인 경우 dfs 시작
						dfs(i,j);
						cnt++; // 섬 개수 증가
					}
				}
			}
			System.out.println(cnt);
		}
	}

	private static void dfs(int x, int y) {
		visited[x][y]=true;
		for(int i=0;i<8;i++) { //상하좌우, 대각선 8방향 탐색
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx>=0 && nx<h && ny>=0 && ny<w) {
				if(!visited[nx][ny] && map[nx][ny]==1)
					dfs(nx,ny);
			}
		}
	}

}
