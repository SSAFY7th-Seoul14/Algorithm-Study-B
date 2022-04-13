package com;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			int n = Integer.parseInt(br.readLine());
			ArrayList<int[]> stores = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				stores.add(new int[] {x,y});
			}
			st = new StringTokenizer(br.readLine());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			stores.add(new int[] {endX,endY});
			
			boolean flag = false;
			boolean[] visit = new boolean[n+1];
			Queue<int[]> q = new LinkedList<>();
			q.offer(new int[] {startX,startY});
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				if(cur[0] == endX && cur[1] == endY) {
					System.out.println("happy");
					flag = true;
					break;
				}
				for(int i=0; i<n+1; i++) {
					if(!visit[i] && distance(cur,stores.get(i)) <= 1000) {
						q.add(stores.get(i));
						visit[i] = true;
					}
				}
			}
			if(!flag) System.out.println("sad");
		}
	}

	private static int distance(int[] p1, int[] p2) {
		return Math.abs(p1[0]-p2[0]) + Math.abs(p1[1]-p2[1]);
	}

}