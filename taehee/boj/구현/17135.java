package com;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] archor = new int[3];
	static int[][] arr;
	static int n,m,d,max=0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0,0); //3명궁수 배치
		System.out.println(max);
	}
	private static void comb(int start, int cnt) {
		if(cnt == 3) {//궁수 위치 archor
			int[][] temp = copy(arr);
			attack(temp);
			return;
		}
		for (int i = start; i < m; i++) {
			archor[cnt] = i;
			comb(i+1,cnt+1);
		}
	}
	private static void attack(int[][] temp) {
		int kill = 0;
		while(true) {
			ArrayList<int[]> list = new ArrayList<>(); //찾은 적  .iist담기
			for (int i = 0; i < 3; i++) {
				int minD = 1000; //각 궁수별로 우선순위 가장 높은 적 찾기
				int minX = 15;
				int minY = 15;
				for(int x=0; x<n; x++) {
					for(int y=0; y<m; y++) {
						//적이면서 공격가능 거리일때
						if(temp[x][y] == 1) {
							int dist = distance(n,archor[i],x,y);
							if(dist <= d) {
								if(minD == dist) {//거리같을때
									if(y < minY) {
										minX = x;
										minY = y;
									}
								}else if(dist< minD) {
									minD = dist;
									minX = x;
									minY = y;
								}
							}
						}
					}
				}//죽일 적 찾아냄
				if(minD <= d) list.add(new int[] {minX,minY});
			} //죽이면 0
			for(int[] cur : list) {
				if(temp[cur[0]][cur[1]] == 1) {
					temp[cur[0]][cur[1]] = 0;
					kill++;					
				}
			}
			//i = i-1
			for (int i = n-1; i > 0; i--) {
				for (int j = 0; j < m; j++) {
					temp[i][j] = temp[i-1][j];
				}			
			}
			//맨윗줄 0
			for(int i=0; i<m; i++) {
				temp[0][i] = 0;
			}
			boolean flag = false;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(temp[i][j] == 1) flag = true;
				}
			}
			if(!flag) break;
		}
		max = Math.max(max, kill);
	}
	private static int distance(int x1, int y1, int x2, int y2) {
		return x1-x2 + Math.abs(y1 - y2);
	}
	private static int[][] copy(int[][] a) {
		int[][] c = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				c[i][j] = a[i][j];
			}
		}
		return c;
	}

}