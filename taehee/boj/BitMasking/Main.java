package com;

import java.io.*;
import java.util.*;

public class Main {
	static class Point{
		int x,j;

		public Point(int x, int j) {
			this.x = x;
			this.j = j;
		}
	}
	static int k;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[n+1][n+1]; //다리 정보 표현 배열
		
		int[] jewel = new int[n+1]; //보석 가진섬,,몇번쨰로 가진지
		int j = 1;
		for (int i = 0; i < k; i++) {
			int x = Integer.parseInt(br.readLine());
			jewel[x] = j++;
		}
		boolean[][] visit = new boolean[n+1][(int)Math.pow(2, k)];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[a][b] = c;
			arr[b][a] = c;
		}
		Queue<Point> q = new LinkedList<>();
		if(jewel[1] > 0) {
			q.offer(new Point(1, 1<<(jewel[1]-1)));
			visit[1][1 << (jewel[1]-1)] = true;
		}
		q.offer(new Point(1,0));
		visit[1][0] = true;
		int max = 0;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			if(p.x == 1) {
				max = Math.max(max, count(p.j));
			}
			for(int i=1; i<n+1; i++) {
				if(arr[p.x][i] == 0) continue; //갈수없는 경우
				if(count(p.j) > arr[p.x][i]) continue; //다리무게 초과
				if(visit[i][p.j]) continue; //똑같이 들고 방문 pass
				
				if(jewel[i] > 0) { //들고가는경우
					q.offer(new Point(i,p.j | (1<<(jewel[i]-1))));
					visit[i][p.j | (1<<(jewel[i]-1))] = true;
				}
				//안들고가는경우
				q.offer(new Point(i,p.j));
				visit[i][p.j] = true;
			}
		}
		System.out.println(max);
		
	}
	private static int count(int x) {
		int ans = 0;
		for(int i=0; i<k; i++) {
			if((x & (1<<i)) > 0) ans++;
		}
		return ans;
	}

}
