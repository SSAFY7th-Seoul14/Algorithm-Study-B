package com;

import java.io.*;
import java.util.*;

public class Main {
	
	static int r,c;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	r = Integer.parseInt(st.nextToken());
    	c = Integer.parseInt(st.nextToken());
    	int[][] arr = new int[r][c];
    	for (int i = 0; i < r; i++) {
    		st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
    	int time = 0; //다 녹는데 걸리는 시간
    	

    	while(true) {
    		ArrayList<int[]> airs = bfs(arr);
    		ArrayList<int[]> meltList = new ArrayList<>();
    		int cheeseCount = 0;
    		//녹일 치즈찾기
    		meltList = bfs(arr,airs);
    		
    		//총 치즈갯수 찾기
    		for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if(arr[i][j] == 1) cheeseCount++;
				}
			}
    		
    		if(meltList.size() == cheeseCount) { //녹기 한시간전
    			System.out.println(time+1);
    			System.out.println(cheeseCount);
    			break;
    		}else {
    			time++;
    			for(int[] i : meltList) {
    				arr[i[0]][i[1]] = 0;
    			}
    		}
    	}
    }
	private static ArrayList<int[]> bfs(int[][] arr, ArrayList<int[]> airs) {
		ArrayList<int[]> list = new ArrayList<>();
		boolean[][] visit = new boolean[r][c];
		for(int[] air : airs) {
			int x = air[0];
			int y = air[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(0<=nx && nx < r && 0<=ny && ny < c && arr[nx][ny] == 1 && !visit[nx][ny]) {
					visit[nx][ny] = true;
					list.add(new int[] {nx,ny});
				}
			}
		}
		
		return list;
	}
	private static ArrayList<int[]> bfs(int[][] arr) {
		ArrayList<int[]> list = new ArrayList<>();
    	boolean[][] visit = new boolean[r][c];
    	
    	Queue<int[]> q = new LinkedList<>();
    	q.add(new int[] {0,0});
    	visit[0][0] = true;
		
    	while(!q.isEmpty()) {
    		int[] p = q.poll();
    		for (int i = 0; i < 4; i++) {
				int nx = p[0] + dx[i];
				int ny = p[1] + dy[i];
				if(0<=nx && nx < r && 0<=ny && ny < c && arr[nx][ny] == 0 && !visit[nx][ny]) {
					list.add(new int[] {nx,ny});
					q.add(new int[] {nx,ny});
					visit[nx][ny] = true;
				}
			}
    	}
		return list;
	}
    
}