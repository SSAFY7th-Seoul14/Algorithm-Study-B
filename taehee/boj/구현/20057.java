package com;

import java.io.*;
import java.util.*;

public class Main {

	static int[][] arr;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static int n, sum=0; //밖으로 나가는 모래
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int x = n/2;
		int y = n/2; //토네이도 좌표
		int dir = 1; //토네이도 방향,,왼쪽시작
		int len = 1; //토네이도 길이
		int cnt = 0; //토네이도 전진횟수
		while(true) {
			if(x+y == 0) break;
			int nx = x+dx[dir];
			int ny = y+dy[dir];
			
			blow(x,y,nx,ny,dir); //모래 날리기
			x = nx;
			y = ny; // 토네이도 전진
			cnt++;
			if(cnt % len == 0) dir = (dir+1)%4; //방향꺾기
			if(cnt==2*len) { //길이증가
				len++;
				cnt = 0;
			}
		}
		System.out.println(sum);
	}

	private static void blow(int x, int y, int nx, int ny, int dir) {
		int minus = (dir-1 == -1) ? 3 : dir-1;
		int plus = (dir+1) % 4;
		int r = 0; // 이동 모래
		
		int tx = x + dx[minus];
		int ty = y + dy[minus];
		if(range(tx,ty)) arr[tx][ty] += arr[nx][ny] / 100; //1퍼
		else sum += arr[nx][ny] / 100;
		r += arr[nx][ny] / 100;
		
		tx = x + dx[plus];
		ty = y + dy[plus];
		if(range(tx,ty)) arr[tx][ty] += arr[nx][ny] / 100; //1퍼
		else sum += arr[nx][ny] / 100;
		r += arr[nx][ny] / 100;
		
		tx = nx + dx[minus];
		ty = ny + dy[minus];
		if(range(tx,ty)) arr[tx][ty] += arr[nx][ny] * 7 / 100; //7퍼
		else sum += arr[nx][ny] * 7 / 100;
		r += arr[nx][ny] * 7 / 100;
		
		tx = nx + dx[plus];
		ty = ny + dy[plus];
		if(range(tx,ty)) arr[tx][ty] += arr[nx][ny] * 7 / 100; //7퍼
		else sum += arr[nx][ny] * 7 / 100;
		r += arr[nx][ny] * 7 / 100;
		
		tx = nx + 2 * dx[plus];
		ty = ny + 2 * dy[plus];
		if(range(tx,ty)) arr[tx][ty] += arr[nx][ny] / 50; //2퍼
		else sum += arr[nx][ny] / 50;
		r += arr[nx][ny] / 50;
		
		tx = nx + 2 * dx[minus];
		ty = ny + 2 * dy[minus];
		if(range(tx,ty)) arr[tx][ty] += arr[nx][ny] / 50; //2퍼
		else sum += arr[nx][ny] / 50;
		r += arr[nx][ny] / 50;
		
		tx = nx + dx[dir] + dx[minus];
		ty = ny + dy[dir] + dy[minus];
		if(range(tx,ty)) arr[tx][ty] += arr[nx][ny] / 10; //10퍼
		else sum += arr[nx][ny] / 10;
		r += arr[nx][ny] / 10;
		
		tx = nx + dx[dir] + dx[plus];
		ty = ny + dy[dir] + dy[plus];
		if(range(tx,ty)) arr[tx][ty] += arr[nx][ny] / 10; //10퍼
		else sum += arr[nx][ny] / 10;
		r += arr[nx][ny] / 10;
		
		tx = nx + 2*dx[dir];
		ty = ny + 2*dy[dir];
		if(range(tx,ty)) arr[tx][ty] += arr[nx][ny] / 20; //5퍼
		else sum += arr[nx][ny] / 20;
		r += arr[nx][ny] / 20;
		
		tx = nx + dx[dir];
		ty = ny + dy[dir];
		if(range(tx,ty)) arr[tx][ty] += arr[nx][ny] - r; //남은 모래
		else sum += arr[nx][ny] - r;
		
		arr[nx][ny] = 0;
	}

	private static boolean range(int tx, int ty) {
		if(0<=tx && tx<n && 0<=ty && ty<n) return true;
		return false;
	}

}