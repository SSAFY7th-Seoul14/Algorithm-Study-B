package com;

import java.io.*;
import java.util.*;

public class Main {
	
	static char[][] arr = new char[5][5];
	static ArrayList<int[]> points = new ArrayList<>();
	static int[][] select = new int[7][2];
	static int ans = 0;
			
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	for (int i = 0; i < 5; i++) {
			arr[i] = br.readLine().toCharArray();
			
			for (int j = 0; j < 5; j++) {
				points.add(new int[] {i,j});
			}
		}
    	
    	comb(0,0);
    	System.out.println(ans);
    }

	private static void comb(int cnt, int start) {
		if(cnt == 7) {
			int sCnt = 0;
			//각 조합에 대해서 모두 이어져있는지 판단
			int[][] list = new int[5][5];
			//모두 이어넣고
			for (int i = 0; i < 7; i++) {
				list[select[i][0]][select[i][1]] = 1;
			}
			//다이어져있는지
			int linked = bfs(list,select[0][0],select[0][1]);
			//다이어져있으면  S 갯수세기
			if(linked == 7) {
				for (int i = 0; i < 7; i++) {
					if(arr[select[i][0]][select[i][1]] == 'S') sCnt++;
				}
				if(sCnt >= 4) ans++;
			}
			return;
		}
		for (int i = start; i < 25; i++) {
			select[cnt] = points.get(i);
			comb(cnt+1, i+1);
		}
	}

	private static int bfs(int[][] list, int x, int y) {
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		
		int cnt = 1;
		
		boolean[][] visit = new boolean[5][5];
		visit[x][y] = true;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			for(int d =0; d<4; d++) {
				int nx = p[0] + dx[d];
				int ny = p[1] + dy[d];
				if(0<= nx && nx < 5 && 0<= ny && ny < 5) {
					if(!visit[nx][ny] && list[nx][ny] == 1) {
						visit[nx][ny] = true;
						q.add(new int[] {nx,ny});
						cnt++;							
					}
				}				
			}
		}
		return cnt;
	}

}