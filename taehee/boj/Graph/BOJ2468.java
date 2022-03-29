package com;

import java.io.*;
import java.util.*;

public class Main {
			
	static int[][] arr;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int n;
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());

    	int maxH = 0;
    	arr = new int[n][n];
    	for (int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				maxH = Math.max(maxH, arr[i][j]);
			}
		}
    	int cnt = 0;
    	for (int i = 0; i <= maxH; i++) {
			cnt = Math.max(cnt, bfs(i));
		}
    	System.out.println(cnt);
    }
	private static int bfs(int h) {
		int[][] visit = new int[n][n];
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j] > h && visit[i][j] == 0) {
					Queue<int[]> q = new LinkedList<>();
					q.add(new int[] {i,j});
					visit[i][j] = 1;
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						int x = cur[0];
						int y = cur[1];
						for (int d = 0; d < 4; d++) {
							int nx = x + dx[d];
							int ny = y + dy[d];
							if(0<= nx && nx < n && 0 <= ny && ny < n && h < arr[nx][ny] && visit[nx][ny] == 0) {
								q.add(new int[] {nx,ny});
								visit[nx][ny] = 1;
							}
						}
					}
					count++;
				}
			}
		}
		return count;
	}
    
}