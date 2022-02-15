package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// BOJ / 쿼드트리 / S1 / 43분
//https://www.acmicpc.net/problem/1992
public class Main_1992 {
	
	static int[][]map;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		// map 입력받기
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = input.charAt(j)-'0';
			}
		}

		//압축 시작
		zip(N,0,0);
		
		br.close();
	}

	private static void zip(int n, int r, int c) { //r: row, c: column
		if(n==1) { // n=1이면 자기 자신 출력
			System.out.print(map[r][c]);
			return;
		}
		int num = map[r][c]; //n x n에서 모든 수가 같은지 판단하는 기준 값
		boolean flag = true; // 압축 가능 여부

		out: for(int i=r;i<r+n;i++) {
			for(int j=c;j<c+n;j++) {
				if(map[i][j]!=num) { //하나라도 다르면 압축 불가능
					flag=false;
					break out;
				}
			}
		}
		
		if(!flag) { //압축 불가능 4방향으로 나뉘어짐
			System.out.print("(");
			zip(n/2,r,c); //왼쪽 위
			zip(n/2,r,c+n/2); //오른쪽 위
			zip(n/2,r+n/2,c); //왼쪽 아래
			zip(n/2,r+n/2,c+n/2); //오른쪽 아래
			System.out.print(")");
		}
		else { //압축 가능하면 기준 값 출력(어차피 모든 수가 기준 값과 같기 때문)
			System.out.print(num);
		}
	}
}
