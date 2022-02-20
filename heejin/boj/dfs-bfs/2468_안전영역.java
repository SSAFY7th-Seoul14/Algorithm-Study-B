package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ / 안전 영역 / S1 / 20분
//https://www.acmicpc.net/problem/2468
public class Main_2468 {
	
	static int N;
	static int map[][]; //map:높이 입력
	static boolean visited[][]; //방문여부
	static int R; //강수량
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int res; //안전영역 최대 개수(정답)
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int t=0;t<=100;t++) { //강수량 0부터 100까지 안전 영역 조사
			R = t; //강수량
			visited = new boolean[N][N]; //방문 배열 초기화
			int cnt=0; // 안전영역 개수
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j]>R && !visited[i][j]) {
						dfs(i,j);
						cnt++;
					}
						
				}
			}
			res = Math.max(res, cnt); //정답 갱신
		}
		System.out.println(res);
		
	}
	private static void dfs(int x, int y) {
		
		visited[x][y]=true;
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx>=0 && nx<N && ny>=0 && ny<N) {
				if(map[nx][ny]>R && !visited[nx][ny]) {
					dfs(nx,ny);
				}
			}
		}
	}

}
