package com;

import java.io.*;
import java.util.*;

public class Main {
	static class Shark{
		int r,c,s,d,z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		
	}
	static int[] dx = {0,-1,1,0,0};
	static int[] dy = {0,0,0,1,-1}; //상하우좌
	static ArrayList<Shark> sList = new ArrayList<>(); //상어리스트
	static Shark[][] arr;
	static int r,c;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new Shark[r+1][c+1];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			Shark sh = new Shark(r,c,s,d,z);
			arr[r][c] = sh;
			sList.add(sh);
		}
		int person = 0;
		int ans = 0;
		while(person < c) {
			person++; //1.낚시꾼 이동
			for(int i=1; i<=r; i++) { //2.상어잡기
				if(arr[i][person] != null) {
					Shark s = arr[i][person];
					ans += s.z;
					arr[i][person] = null;
					sList.remove(s);
					break;
				}
			}
			move();//3.상어이동
		}
		System.out.println(ans);
	}

	private static void move() {
		Shark[][] next = new Shark[r+1][c+1];
		for(int i=0; i<sList.size(); i++) { //상어별로
			Shark s = sList.get(i);
			int speed = s.s;
			if(s.d==1||s.d==2) speed %= ((r-1)*2);
			else speed %= ((c-1)*2);
			for(int j=0; j<speed; j++) { //상어속력만큼 이동, 한칸씩움직이면 시간초과
				//경계면 방향전환
				if(s.d == 1 && s.r == 1) s.d=2;
				else if(s.d == 2 && s.r == r) s.d=1;
				else if(s.d == 3 && s.c == c) s.d=4;
				else if(s.d == 4 && s.c == 1) s.d=3;
				s.r += dx[s.d];
				s.c += dy[s.d];
			}
			if(next[s.r][s.c] != null) { //이미 상어있을때
				Shark ss = next[s.r][s.c];
				if(s.z < ss.z) { //있던게 크면
					sList.remove(i--);
				}else { //새로운게 크면
					next[s.r][s.c] = s;
					sList.remove(ss);
					i--;
				}
			}else {
				next[s.r][s.c] = s;
			}
		}
		arr = next; //덮기
	}

}