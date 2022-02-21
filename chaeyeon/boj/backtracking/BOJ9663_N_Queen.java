package com.ssafy.webX;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ9663_N_Queen {
	static int N;
	static int ans;
	static int[] visit;
	public static void calc(int row) {
		
		if(row == N) {
			ans++;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			visit[row] = i;
			if(check(row, i)) {//지금 놓은 자리가 괜찮다면 다음 꺼 놓기
				calc(row+1);
			}
			
		}
	}
	

	
	public static boolean check(int row, int col) {
		for(int j = 0; j < row; j++) {
			if(visit[j] == col || Math.abs(row-j)-Math.abs(col-visit[j])==0) {//열이 겹칠때, 대각선이 겹칠 때
				return false;
			}
		}
		
		return true;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		visit = new int[N];//인덱스=행, 데이타=열
		
		calc(0);

		System.out.println(ans);
		
	}

}
