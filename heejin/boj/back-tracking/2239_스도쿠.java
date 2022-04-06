package com.boj;

import java.io.*;
import java.util.*;


// BOJ / 스도쿠 / G4 / 30분
// https://www.acmicpc.net/problem/2239
public class Main_2239 {
	static int[][] map;
	static List<int[]> list; //0인 곳의 좌표
	static int cnt;	//처음만 출력하기 위한 변수
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		list = new ArrayList<>();
		for(int i=0;i<9;i++) {
			String input = br.readLine();
			for(int j=0;j<9;j++) {
				map[i][j] = input.charAt(j)-'0';
				if(map[i][j]==0)
					list.add(new int[] {i,j});
			}
		}
		dfs(0,map,list);
	
	}

	private static void dfs(int idx, int[][] map, List<int[]> list) {
		if(cnt>0) return;	//이미 한 번 출력했으면 더 이상 dfs 실행 X
		if(idx==list.size()) {
			//출력
			if(cnt==0) {	//초반 출력을 위한 것
				for(int i=0;i<9;i++) {
					for(int j=0;j<9;j++)
						System.out.print(map[i][j]);
					System.out.println();
				}
			}
			cnt++;
			return;
		}
		for(int i=1;i<=9;i++) { //0인 곳에 1~9까지 숫자 넣어보기
			int[] cur = list.get(idx);
			int x = cur[0], y = cur[1];
			map[x][y] = i;
			if(isPromising(x,y,map))	//유망할 경우
				dfs(idx+1,map,list);
			map[x][y] = 0; 	
		}
	}
	private static boolean isPromising(int x, int y, int[][] map) {
		int cur = map[x][y]; //현재 삽입한 숫자
		// 행에 같은 숫자 존재
		for(int c=0;c<9;c++) {
			if(c!=y && map[x][c]==cur)	//같은 행에 같은 값 존재하는 경우
				return false;
		}
		// 열에 같은 숫자 존재
		for(int r=0;r<9;r++) {
			if(r!=x && map[r][y]==cur) //같은 열에 같은 값 존재하는 경우
				return false;
		}
		// 3x3에 같은 숫자 존재
		int start_x = x - (x%3);
		int start_y = y - (y%3);
		for(int i=start_x;i<start_x+3;i++) {
			for(int j=start_y;j<start_y+3;j++) {
				if(i==x && y==j) continue;
				if(map[i][j]==cur)
					return false;
			}
		}
		return true;
	}
}
