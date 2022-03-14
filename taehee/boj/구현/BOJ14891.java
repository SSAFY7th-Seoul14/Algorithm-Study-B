package com;

import java.util.*;
import java.io.*;

public class Main {
	static char[][] wheel;
	static int[] topnow = new int[4]; //12시 위치
	static int[] topnext = new int[4];
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		wheel = new char[4][];
		for (int i = 0; i < 4; i++) {
			wheel[i] = br.readLine().toCharArray();
		}
		
		int k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1; //0~3번으로
			int dir = Integer.parseInt(st.nextToken());
			rotate(num,dir);
		}
		int score = 0;
		for (int i = 0; i < 4; i++) {
			if(wheel[i][topnow[i]] == '1') {
				score += (int)Math.pow(2, i);
			}
		}
		System.out.println(score);
	}
	private static void rotate(int num, int dir) {
		topnext[num] = (topnow[num]-dir)%8; //현재 톱니 회전시키기
		if(topnext[num] == -1) topnext[num] = 7;
		
		int x = num;
		int d = dir;
		while(0 < x) {
			int left = x-1;
			//현재것의 왼쪽(12시 기준 2번째)과 왼쪽것의 오른쪽(12시기준 6번째)다르면 회전
			if(wheel[x][(topnow[x]+6)%8] != wheel[left][(topnow[left]+2)%8]) {
				topnext[left] = (topnow[left]+d)%8;
				if(topnext[left] == -1) topnext[left] = 7;
				x = left;
				d *= -1;
			}else {
				break;
			}
		}
		
		x = num;
		d = dir;
		while(x < 3) {
			int right = x+1;
			//현재것의 오른쪽과 오른쪽것의 왼쪽다르면 회전
			if(wheel[x][(topnow[x]+2)%8] != wheel[right][(topnow[right]+6)%8]) {
				topnext[right] = (topnow[right]+d)%8;
				if(topnext[right] == -1) topnext[right] = 7;
				x = right;
				d *= -1;
			}else {
				break;
			}
		}
		for (int i = 0; i < 4; i++) {
			topnow[i] = topnext[i];
		}
	}

} 