package com;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	
    	char[][] arr = new char[n][n];
    	boolean[][] visit = new boolean[n][n];
    	for (int i = 0; i < n; i++) {
			arr[i] = br.readLine().toCharArray();
		}
    	ArrayList<Integer> list = new ArrayList<>(); //단지
    	int[] dx = {1,-1,0,0};
    	int[] dy = {0,0,1,-1};
    	
    	for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j] == '1' && !visit[i][j]) {
					int cnt = 1;
					Queue<int[]> q = new LinkedList<>();
					q.add(new int[] {i,j});
					visit[i][j] = true;
					
					while(!q.isEmpty()) {
						int[] p = q.poll();
						for (int d = 0; d < 4; d++) {
							int nx = p[0] + dx[d];
							int ny = p[1] + dy[d];
							if(0<=nx && nx<n && 0<= ny && ny<n && !visit[nx][ny] && arr[nx][ny] == '1') {
								q.add(new int[] {nx,ny});
								cnt++;
								visit[nx][ny] = true;
							}
						}
					}
					list.add(cnt);
				}
			}
		}
    	Collections.sort(list);
    	System.out.println(list.size());
    	for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
    }
    
}