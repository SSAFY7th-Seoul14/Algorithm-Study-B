package com;

import java.io.*;
import java.util.*;

public class Main {
	
	static int n,m,h,ans;
	static int[][] arr;
	static boolean finish;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		arr = new int[h+1][n+1]; //0:밑, 1:오른쪽, 2:왼쪽
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = 1;
			arr[a][b+1] = 2;
		}

		for (int i = 0; i <= 3; i++) {
			ans = i;
			dfs(1,1,0);
			if(finish) break;
		}
		System.out.println(finish ? ans : -1);
	}

	private static void dfs(int x, int y, int num) {
		if(finish) return;
		if(ans == num) { //추가완료
			if(check()) finish = true;
			return;
		}
		for (int i = y; i <= h; i++) {
			for (int j = x; j < n; j++) {
				if(arr[i][j] == 0 && arr[i][j+1] == 0) { //사다리 놓을 수 있을때
					arr[i][j] = 1;
					arr[i][j+1] = 2; //사다리 배치
					
					dfs(1,1,num+1);
					
					arr[i][j] = 0;
					arr[i][j+1] = 0; //돌려놓기
				}
			}
		}
	}

	private static boolean check() {
		for (int i = 1; i <= n; i++) { //각 세로선 도착확인
			int nx = 1;
			int ny = i;
			while(nx <= h) {
				if(arr[nx][ny] == 1) ny++; //오른쪽으로
				else if(arr[nx][ny] == 2) ny--; //왼쪽으로
				nx++;
			}
			if(ny != i) return false;
		}
		return true;
	}
	
}