package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ / 맥주 마시면서 걸어가기 / S1 / 75분 
// https://www.acmicpc.net/problem/9205
public class Main_9205{
	static int start_x, start_y, end_x, end_y, N;
	static List<int[]> list;
	static boolean visited[];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine()); //편의점 개수
			
			StringTokenizer st = new StringTokenizer(br.readLine()); //상근이 집 좌표
			start_x = Integer.parseInt(st.nextToken());
			start_y = Integer.parseInt(st.nextToken());
			
			list = new ArrayList<>(); //편의점 리스트 저장
			for(int i=0;i<N;i++) { // N개의 편의점 입력받기
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				list.add(new int[] {x,y});
			}
			st = new StringTokenizer(br.readLine()); //락 페스티벌 좌표
			end_x = Integer.parseInt(st.nextToken());
			end_y = Integer.parseInt(st.nextToken());

			visited = new boolean[N];
			
			bfs(start_x,start_y);
			
		}
	}

	private static void bfs(int x, int y) {
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x,y});
		
		int con_x, con_y, cur_x, cur_y; //편의점 x,y좌표/현재 x,y좌표
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			cur_x = cur[0];
			cur_y = cur[1];
			if(Math.abs(cur_x-end_x)+Math.abs(cur_y-end_y)<=1000) {
				System.out.println("happy");
				return;
			}
			//현재 위치에서 1000m 안에 있는 편의점 큐에 넣기
			for(int i=0;i<N;i++) {
				con_x = list.get(i)[0];
				con_y = list.get(i)[1];

				if(!visited[i] && Math.abs(con_x-cur_x)+Math.abs(con_y-cur_y)<=1000) {

					q.offer(new int[] {con_x,con_y});
					visited[i]=true;
				}
			}
		}
		System.out.println("sad");
		
	}
}
