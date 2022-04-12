package com;

import java.io.*;
import java.util.*;

public class Main {

	static int n, min = 1000;
	static int[] population; //인구수 배열
	static int[][] map;
	static ArrayList<Integer> area1;
	static ArrayList<Integer> area2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		population = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		map = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				int k = Integer.parseInt(st.nextToken());
				map[i][k] = 1;
				map[k][i] = 1;
			}
		}
		
		for(int i=1; i<(int)Math.pow(2, n-1); i++) {
			area1 = new ArrayList<>();
			area2 = new ArrayList<>();
			//두구역으로 나누기
			for (int j = 0; j < n; j++) {
				if( (i&(1<<j)) > 0) area1.add(j+1);
				else area2.add(j+1);
			}
			//나눠진 구역으로 확인해보기
			gary();
		}
		System.out.println(min==1000? -1 : min);
	}

	private static void gary() {
		//두구역으로 나누어졌는지 확인
		boolean flag = sep();
		if(flag) {
			int p1 = 0; //area1 인구수
			int p2 = 0;
			for (int i = 0; i < area1.size(); i++) {
				p1 += population[area1.get(i)];
			}
			for (int i = 0; i < area2.size(); i++) {
				p2 += population[area2.get(i)];
			}
			min = Math.min(min, Math.abs(p1-p2));
		}
	}

	private static boolean sep() {
		boolean[] visit = new boolean[n+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(area1.get(0));
		visit[area1.get(0)] = true;
		int cnt1 = 1;
		while(!q.isEmpty()) {
			int x = q.poll();
			for (int i = 1; i <= n; i++) {
				if(map[x][i] == 1 && area1.contains(i) && !visit[i]) {
					q.add(i);
					cnt1++;
					visit[i] = true;
				}
			}
		}
		q = new LinkedList<>();
		q.add(area2.get(0));
		visit[area2.get(0)] = true;
		int cnt2 = 1;
		while(!q.isEmpty()) {
			int x = q.poll();
			for (int i = 1; i <= n; i++) {
				if(map[x][i] == 1 && area2.contains(i) && !visit[i]) {
					q.add(i);
					cnt2++;
					visit[i] = true;
				}
			}
		}
		if(cnt1==area1.size() && cnt2==area2.size()) return true;
		return false;
	}

}