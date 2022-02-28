package com.ssafy.study;

import java.io.*;
import java.util.*;

// SEA / 정사각형 방 / D4 / 25
//
class Room implements Comparable<Room>{
	int num; //방 번호
	int cnt; //지나갈 수 있는 방
	public Room(int num, int cnt) {
		super();
		this.num = num;
		this.cnt = cnt;
	}
	
	@Override 
	public int compareTo(Room o) {
		if(this.cnt==o.cnt)
			return this.num-o.num;  //방 번호는 오름차순 
		else
			return o.cnt-this.cnt;//지나갈 수 있는 방은 내림차 순
	}
	
}
public class Solution_1861 {
	static int map[][];
	static int N;
	static boolean visited[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int cnt; //지나간 방 횟수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			List<Room> list = new ArrayList<>();
			
			//map 입력받기
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					cnt=1;
					visited = new boolean[N][N];
					dfs(i,j);
					list.add(new Room(map[i][j],cnt));
				}
			}
			Collections.sort(list); // 이동한 방 크기 순으로 정렬
			System.out.println("#"+t+" "+list.get(0).num+" "+list.get(0).cnt);
		}
	}

	private static void dfs(int x, int y) {
		visited[x][y]=true;
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx>=0 && nx<N && ny>=0 && ny<N) {
				if(!visited[nx][ny] && map[x][y]+1==map[nx][ny]) {
					cnt++; //이동 방 크기 증가
					dfs(nx,ny);
					
				}
			}	
		}
	}
}
