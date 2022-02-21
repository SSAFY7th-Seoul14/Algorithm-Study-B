package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// BOJ / 단지번호붙이기 / S1 / 30분
// https://www.acmicpc.net/problem/2667
public class Main_2667 {

	static int N;
	static int[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int townNum; //단지 수
	static int cnt; // 단지의 마을 수
	static List<Integer> cntList = new ArrayList<>();
	static boolean[][] visited;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = input.charAt(j)-'0';				
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==1 && !visited[i][j]) {
					cnt=1;
					dfs(i,j);
					townNum++;
					cntList.add(cnt);
				}
					
			}
		}
		System.out.println(townNum);
		Collections.sort(cntList);
		for(int i: cntList)
			System.out.println(i);
		
		
	}

	private static void dfs(int x, int y) {
		visited[x][y]=true;
		
		for(int i=0;i<4;i++) {
			int nx = x+ dx[i];
			int ny = y + dy[i];
			if(nx>=0 && nx<N && ny>=0 && ny<N) {
				if(isAvailable(nx,ny)) {
					dfs(nx,ny);
					cnt++;
				}
			}
		}
	}

	private static boolean isAvailable(int x, int y) {
		if(map[x][y]==1 && !visited[x][y]) return true;
		return false;
	}
	
	
}
