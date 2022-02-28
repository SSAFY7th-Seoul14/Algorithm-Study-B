package com.ssafy.study;

import java.io.*;
import java.util.*;

// BOJ / 인구이동 / G5 / 3시간+
// https://www.acmicpc.net/problem/16234
public class Main_16234 {
	static int N,L,R;
	static int map[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static boolean visited[][];//영역인지 유무
	static List<int[]> list;
	static int day; //인구이동 일어나는 일 수
	static boolean isMove; //인구이동 여부
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			isMove = false; //인구이동 여부 초기화
			visited = new boolean[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!visited[i][j]) {
						movePeople(i,j); //인구이동 시작
					}
				}
			}
			if(isMove) //인구이동 일어났을 경우, 일 수 증가
				day++;
			else //인구 이동 일어나지 않으면 종료
				break;
		}
		System.out.println(day);
	}

	

	private static void movePeople(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		list = new ArrayList<>(); //연합 좌표 저장
		
		visited[x][y]=true;
		q.offer(new int[] {x,y});
		list.add(new int[] {x,y});
		
		int cnt = 1; int total=map[x][y]; //cnt: 연합 수, total: 연합 총인원
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0]; int cy = cur[1];
			for(int i=0;i<4;i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if(nx>=0 && nx<N && ny>=0 && ny<N) {
					int diff = Math.abs(map[cx][cy]-map[nx][ny]);
					if(!visited[nx][ny] && diff>=L && diff<=R) {
						visited[nx][ny]=true;
						q.offer(new int[] {nx,ny});
						list.add(new int[] {nx,ny});
						cnt++;
						total+=map[nx][ny];
					}
				}
			}
		}
		if(cnt==1) return; //연합 안 만들어짐 -> isMove flag가 false 유지됨
		isMove = true;
		int avg = (int)(total/cnt);
		
		
		for(int i=0;i<list.size();i++) { //연합 간에 인구 이동 시작
			int cx = list.get(i)[0];
			int cy = list.get(i)[1];
			map[cx][cy] = avg;
		}

	}
}
