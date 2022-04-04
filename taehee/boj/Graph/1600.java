package com;

import java.io.*;
import java.util.*;

public class Main {
	static int[] hdx = {-2,-2,-1,-1,1,1,2,2};
	static int[] hdy = {-1,1,-2,2,-2,2,-1,1};
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int k = Integer.parseInt(st.nextToken());
    	st = new StringTokenizer(br.readLine());
    	int w = Integer.parseInt(st.nextToken());
    	int h = Integer.parseInt(st.nextToken());
    	int[][] arr = new int[h][w];
    	boolean[][][] visit = new boolean[h][w][k+1];
    	
    	for (int i = 0; i < h; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j = 0; j < w; j++) {
    			arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
    	Queue<int[]> q = new LinkedList<>(); //[x,y,k,cnt] k사용횟수, 동작횟수
    	q.add(new int[] {0,0,0,0});
    	visit[0][0][0] = true;
    	boolean flag = false;
    	
    	while(!q.isEmpty()) {
    		int[] m = q.poll();
    		
    		if(m[0] == h-1 && m[1] == w-1) {
    			System.out.println(m[3]);
    			flag = true;
    			break;
    		}
    		
    		if(m[2] < k) { //말처럼 이동
    			for (int i = 0; i < 8; i++) {
					int nx = m[0] + hdx[i];
					int ny = m[1] + hdy[i];
					if(0<=nx && nx<h && 0<=ny && ny<w && arr[nx][ny] == 0 && !visit[nx][ny][m[2]+1]) {
						q.add(new int[] {nx,ny,m[2]+1,m[3]+1});
						visit[nx][ny][m[2]+1] = true;
					}
				}
    		}
			for (int i = 0; i < 4; i++) {
				int nx = m[0] + dx[i];
				int ny = m[1] + dy[i];
				if(0<=nx && nx<h && 0<=ny && ny<w && arr[nx][ny] == 0 && !visit[nx][ny][m[2]]) {
					q.add(new int[] {nx,ny,m[2],m[3]+1});
					visit[nx][ny][m[2]] = true;
				}
			}
    	}
    	if(!flag) System.out.println(-1);
    }
    
}