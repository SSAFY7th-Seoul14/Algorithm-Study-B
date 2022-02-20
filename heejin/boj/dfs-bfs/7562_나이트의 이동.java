package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ / 나이트의 이동 / S2 / 15분
// https://www.acmicpc.net/problem/7562
public class Main_7562 {
	static int L, end_x, end_y; //한변 길이, 도착점 x,y
	static int dx[] = {-2,-1,1,2,2,1,-1,-2};
	static int dy[] = {1,2,2,1,-1,-2,-2,-1};
	static boolean visited[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			L = Integer.parseInt(br.readLine());
			
			visited = new boolean[L][L];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int start_x = Integer.parseInt(st.nextToken());
			int start_y = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			end_x = Integer.parseInt(st.nextToken());
			end_y = Integer.parseInt(st.nextToken());
			if(start_x==end_x && start_y==end_y) //시작점=도착점일 경우 0 반환
				System.out.println(0);
			else
				bfs(start_x, start_y);
		}
	}

	private static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		
		q.offer(new int[] {x,y,0});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int i=0;i<8;i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				int ntime = cur[2] + 1; //현재 위치까지 걸린 시간

				if(nx>=0 && nx<L && ny>=0 && ny<L) {
					if(nx==end_x && ny == end_y) {
						System.out.println(ntime);
						return;
					}
					if(!visited[nx][ny]) {
						q.offer(new int[] {nx,ny,ntime});
						visited[nx][ny]=true;
					}
				}
			}
		}
		
	}
}
