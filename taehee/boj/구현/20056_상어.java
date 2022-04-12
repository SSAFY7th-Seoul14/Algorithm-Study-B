package com;

import java.io.*;
import java.util.*;

public class Main {
	static class Fire{
		int r,c,m,s,d;

		public Fire(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		
	}
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,1,1,1,0,-1,-1,-1};
	static ArrayList<Fire>[][] arr;
	static ArrayList<Fire> fires;
	static int N,M,K;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //배열크기
		M = Integer.parseInt(st.nextToken()); //파이어볼 처음개수
		K = Integer.parseInt(st.nextToken()); //명령 수

		arr = new ArrayList[N][N];
		fires = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = new ArrayList<Fire>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			Fire f = new Fire(r,c,m,s,d);
			fires.add(f);
			arr[r][c].add(f);
		}
		for (int i = 0; i < K; i++) {
			move();
		}
		int ans =0;
		for(Fire f : fires) {
			ans += f.m;
		}
		System.out.println(ans);
	}

	private static void move() {
		ArrayList<Fire> newFires = new ArrayList<>();

		for(Fire f : fires) { //d방향으로 s만큼 더하기
			int nx = (f.r + dx[f.d]*f.s) % N;
			if(nx < 0) {
				while(nx<0) nx+=N;
			}
			int ny = (f.c + dy[f.d]*f.s) % N;
			if(ny < 0) {
				while(ny<0) ny+=N;
			}
			arr[nx][ny].add(new Fire(nx,ny,f.m,f.s,f.d));
			arr[f.r][f.c].remove(f);
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j].size() == 1) newFires.add(arr[i][j].get(0));
				else if(arr[i][j].size() > 1) {
					boolean flag = false;//모두 홀수거나 짝수면 false
					if(arr[i][j].get(0).d % 2 == 0) {//처음거 짝수면
						for(Fire f : arr[i][j]) {
							if(f.d %2 == 1) {
								flag = true;
								break;
							}
						}
					}else { //처음거 홀수
						for(Fire f : arr[i][j]) {
							if(f.d %2 == 0) {
								flag = true;
								break;
							}
						}
					}
					int newS = 0;
					int newM = 0;
					for(Fire f : arr[i][j]) {
						newM += f.m;
						newS += f.s;
					}
					newS /= arr[i][j].size();
					newM /= 5;
					arr[i][j].clear();
					if(flag && newM > 0) { //1357
						for (int k = 0; k < 4; k++) {
							Fire newF = new Fire(i,j,newM,newS,2*k+1);
							arr[i][j].add(newF);
							newFires.add(newF);
						}
					}else if(!flag && newM > 0) { //0246
						for (int k = 0; k < 4; k++) {
							Fire newF = new Fire(i,j,newM,newS,2*k);
							arr[i][j].add(newF);
							newFires.add(newF);
						}
					}
					
				}//size 2이상 이동끝
			}
		}
		fires = newFires;
	}
}