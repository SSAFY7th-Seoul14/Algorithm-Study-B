package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ / 빵집 / G2 / 90분
// https://www.acmicpc.net/problem/3109
public class Main_3109 {
	
	static int R,C;
	static char[][] map;
	static int cnt; //파이프  개수
	static int[] dx = {-1, 0, 1}; //오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선 (순서중요)
	static int[] dy = {1, 1, 1};

	static boolean[][] visited; //재방문 방지
	static boolean finish; //통로 재사용 금지
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		//맵 입력받기
		map = new char[R][C];
		for(int i=0;i<R;i++) {
			String input = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = input.charAt(j);
			}
		}

		visited= new boolean[R][C];
		//탐색 시작
		for(int i=0;i<R;i++) {
			finish = false; //초기화
			dfs(i,0);
		}
		System.out.println(cnt);
		
	}
	private static void dfs(int x,int y) {
		if(y==C-1) {
			finish = true;
			cnt++;
			return;
		}
		visited[x][y]=true;
		for(int i=0;i<3;i++) { //3방향 탐색
			int nx = x+dx[i];
			int ny= y+dy[i];
			if(nx>=0 && nx<R && ny >=0 && ny<C) {
				if(!visited[nx][ny] && map[nx][ny]=='.') {
					dfs(nx,ny);
					if(finish) return; //3방향 중 이미 탐색 완료 되었으면 나머지 방향 탐색x
				}
			}
		}
	}
}
