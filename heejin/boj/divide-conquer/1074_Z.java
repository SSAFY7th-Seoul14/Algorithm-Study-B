package com.ssafy.exam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ / Z / S1 / 50분
// https://www.acmicpc.net/problem/1074
//1차 - 전체 탐색(시간초과) 2차 - 특정 사분면 검색
//참고: https://lotuslee.tistory.com/86
public class Main_1074_1 {
	static int r,c; //행, 열
	static int cnt; // 숫자
	static int size; //가로, 세로 길이

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		size = (int)Math.pow(2, N); //2^n
		
		divideConquer(size,r,c);
		System.out.println(cnt);
	}
	
	public static void divideConquer(int len, int row, int col) {
		if(len==1) return;
		
		if(row<len/2 && col < len/2) { //2사분면
			divideConquer(len/2, row, col);
		}
		else if(row<len/2 && col >= len/2) { //1사분면
			cnt += len*len/4;
			divideConquer(len/2,row,col-len/2);
		}
		else if(row>=len/2 && col < len/2) { //3사분면
			cnt += (len*len/4)*2;
			divideConquer(len/2,row-len/2, col);
		}
		else if(row>=len/2 && col >= len/2) { //4사분면
			cnt += (len*len/4)*3;
			divideConquer(len/2,row-len/2,col-len/2);
		}
	}
}
