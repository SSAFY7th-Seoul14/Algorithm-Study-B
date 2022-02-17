package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// BOJ / N-Queen / G5 / 40분
// https://www.acmicpc.net/problem/9663
//setQueen 호출 자리 헷갈림,.,.,ㅂㄷㅂㄷ
public class Main_9663 {

	static int[] cols; // 각 행마다 퀸의 열 위치 저장
	static int N;
	static int cnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N= Integer.parseInt(br.readLine());
		cols = new int[N+1];
		setQueen(1);
		System.out.println(cnt);
	}
	
	private static void setQueen(int row) {
		if(row==N+1) {
			cnt++;
			return;
		}
		//1열부터 n열까지 하나씩 돌며 같은 열/대각선에 있는지 체크하고, 괜찮으면 퀸 놓기
		for(int i=1;i<=N;i++){
			cols[row]=i;
			if(isAvailable(row))
				setQueen(row+1); // 전부 괜찮으면 queen 놓기
		}
	}
	
	private static boolean isAvailable(int row) {
		for(int i=1;i<row;i++) {
			if(cols[i]==cols[row] || row-i==Math.abs(cols[i]-cols[row])) return false;
		}
		return true;
	}
}
