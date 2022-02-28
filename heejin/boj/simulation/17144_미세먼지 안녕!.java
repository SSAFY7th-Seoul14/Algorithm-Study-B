package com.ssafy.study;

import java.io.*;
import java.util.*;

// BOJ / 미세먼지 안녕!(리팩토링 버전) / G4
// https://www.acmicpc.net/problem/17144
class Dust{
	int x;
	int y;
	int amount;//양
	
	public Dust(int x, int y, int amount) {
		this.x = x;
		this.y = y;
		this.amount = amount;
	}
}


public class Main_17144 {
	static int R, C, T;
	static int map[][];
	static Queue<Dust> q = new LinkedList<>(); //미세먼지 x,y좌표와 양 저장
	static List<Integer> cleaner = new LinkedList<>(); //공기청정기 행값 저장
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		//미세먼지 분포 입력받기
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
				if(map[i][j]==-1) //공기 청정기 행 위치 저장
					cleaner.add(i);
			}
		}
		
		for(int t=1;t<=T;t++) {
			checkDust(); //미세먼지 위치 큐에 저장
			spreadDust(); //미세먼지 확산시키기
			airCleanerTop(); //위쪽 공기청정기 
			airCleanerBottom(); //아래쪽 공기청정기
		}
		//결과 계산
		int res = calcDust();
		System.out.println(res);
	}
	private static void checkDust() {
		//미세먼지 분포 입력받기
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(map[i][j]>0) {
					q.add(new Dust(i,j,map[i][j]));
				}
			}
		}
		
	}
	private static int calcDust() {
		int res=0;
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(map[i][j]>0)
					res+=map[i][j];
			}
		}
		return res;
		
	}
	private static void airCleanerTop() {
		int top = cleaner.get(0);
		for(int r=top-1;r>0;r--)
			map[r][0] = map[r-1][0];
		for(int c=0;c<C-1;c++)
			map[0][c] = map[0][c+1];
		for(int r=0;r<top;r++)
			map[r][C-1] = map[r+1][C-1];
		for(int c=C-1;c>1;c--)
			map[top][c] = map[top][c-1];
		map[top][1]=0;
	}
	private static void airCleanerBottom() {
		int bottom = cleaner.get(1);
		for(int r=bottom+1;r<R-1;r++)
			map[r][0] = map[r+1][0];
		for(int c=0;c<C-1;c++)
			map[R-1][c] = map[R-1][c+1];
		for(int r=R-1;r>bottom;r--)
			map[r][C-1] = map[r-1][C-1];
		for(int c=C-1;c>1;c--)
			map[bottom][c] = map[bottom][c-1];
		map[bottom][1]=0;
		
	}
	private static void spreadDust() {
		while(!q.isEmpty()) {
			Dust dust = q.poll();
			int dustAmount = (int)(dust.amount/5);
			if(dustAmount==0) continue;
			int cnt=0; //확산되는 횟수
			for(int i=0;i<4;i++) {
				int nx = dust.x + dx[i];
				int ny = dust.y + dy[i];
				if(nx>=0 && nx<R && ny>=0 && ny<C && map[nx][ny]!=-1) {
					map[nx][ny] += dustAmount;
					cnt++;
				}	
			}
			map[dust.x][dust.y] -= dustAmount*cnt;
		}
		
	}
	

}
