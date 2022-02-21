package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class AP implements Comparable<AP>{
	int no,x,y,c,p;
	public AP(int no, int x, int y, int c, int p) {
		this.no = no;
		this.x = x;
		this.y = y;
		this.c = c;
		this.p = p;
	}
	@Override
	public int compareTo(AP o) {
		if(this.p < o.p) return 1;
		else return -1;
	}
	
}

public class Solution {

	static int answer;
	static int[] moveA, moveB;
	static AP[] apList;
	static int xa,ya,xb,yb;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t<=T; t++) {
			//(1,1) ~ (10,10)
			//방향정보
			int[] dx = {0,0,1,0,-1};
			int[] dy = {0,-1,0,1,0};
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int m = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			// A B 이동정보 초기화, 0초부터 충전위해 +1
			moveA = new int[m+1];
			moveB = new int[m+1];
			st = new StringTokenizer(br.readLine()," ");
			for(int i=1; i<=m; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine()," ");
			for(int i=1; i<=m; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			// AP 정보 초기화
			apList = new AP[a]; //1~a번, x,y,c,p
			for(int i=0; i<a; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				apList[i] = new AP(i+1,x,y,c,p);
			}
			//답 초기화
			answer = 0;
			//A B 좌표 초기화
			xa = 1;
			ya = 1;
			xb = 10;
			yb = 10;
			
			for(int i=0; i<m+1; i++) {
				//이동하고
				xa += dx[moveA[i]];
				ya += dy[moveA[i]];
				xb += dx[moveB[i]];
				yb += dy[moveB[i]];
				//충전
				charge();
			}
			System.out.println("#"+t+" "+answer);
		}
	}
	
	private static void charge() {
		//각각 닿는 AP의 파워를 담은 배열
		ArrayList<AP> a = new ArrayList<>();
		ArrayList<AP> b = new ArrayList<>();
		for(AP ap : apList) {
			if(distance(ap.x, ap.y, xa, ya) <= ap.c) {
				a.add(ap);
			}else {
				a.add(new AP(0,0,0,0,0));
			}
			if(distance(ap.x, ap.y, xb, yb) <= ap.c) {
				b.add(ap);
			}else {
				b.add(new AP(0,0,0,0,0));
			}
		}
		answer += getCharge(a,b);
		
	}

	private static int getCharge(ArrayList<AP> a, ArrayList<AP> b) {
		//p순으로 내림차순 정렬
		a.add(new AP(0,0,0,0,0));
		b.add(new AP(0,0,0,0,0));
		
		Collections.sort(a);
		Collections.sort(b);
		//안겹치는경우
		if(a.get(0).no != b.get(0).no) {
			return a.get(0).p + b.get(0).p;
		}else { //겹치는 경우
			int x1 = a.get(0).p + b.get(1).p;
			int x2 = a.get(1).p + b.get(0).p;
			return Math.max(x1, x2);
		}
	}

	public static int distance(int x1,int y1,int x2,int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
}
