package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ / 알파벳 / G4 / 1시간+
// https://www.acmicpc.net/problem/1987

public class Main_1987 {
	static int R,C;
	static char[][] map;
	static boolean visited[] = new boolean[26]; //A~Z 방문 여부 확인
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int res=Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int i=0;i<R;i++) {
			String input = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = input.charAt(j);				
			}
		}
		
		dfs(0,0,0);

		System.out.println(res);
		
	}
	private static void dfs(int x, int y,int count) {
		
		if(visited[map[x][y]-'A']==true) { // 이미 방문한 알파벳일 경우 
			res = Math.max(res, count); // 최고 길이 값 갱신 후 재귀 종료
			return;
		}

		visited[map[x][y]-'A']=true; // 현재 알파벳 방문 처리
		for(int i=0;i<4;i++) { //4방 탐색
			int nx = x + dx[i];
			int ny = y+ dy[i];
			if(nx>=0 && nx<R && ny>=0 && ny<C) {
				dfs(nx,ny,count+1);
			}
		}
		visited[map[x][y]-'A']=false; // 다음 재귀를 위해 현재 알파벳 방문 처리 취소
		
	}

}
