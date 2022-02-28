package com.hw;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SW1238 {
	
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		for(int t=1; t<=10; t++) {
			int l = sc.nextInt();
			int start = sc.nextInt();
			
			int[][] arr = new int[101][101];
			boolean[] visit = new boolean[101];
			
			for(int i=0; i<l/2; i++) {
				int from =  sc.nextInt();
				int to = sc.nextInt();
				arr[from][to] = 1;
			}
			
			int maxCnt = 0;
			Queue<int[]> q = new LinkedList<>();
			q.offer(new int[] {start,0});
			
			while(!q.isEmpty()) {
				int[] temp = q.poll();
				int now = temp[0];
				int cnt = temp[1];
				maxCnt = cnt;
				
				for(int i=1; i<101; i++) {
					if(arr[now][i] != 0 && !visit[i]) {
						q.offer(new int[] {i,cnt+1});
						visit[i] = true;
						arr[now][i] = cnt + 1;
					}
				}
			}
			int maxIdx = 0;
			for(int i=1; i<101; i++) {
				for(int j=1; j<101; j++) {
					if(arr[i][j] == maxCnt && visit[j]) {
						maxIdx = Math.max(j, maxIdx);
					}
				}
			}
			System.out.println("#"+t + " "+maxIdx);
		}
	}
	
}
