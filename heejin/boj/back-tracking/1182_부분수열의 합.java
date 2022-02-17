package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ / 부분수열의 합 / S2 / 30분
//https://www.acmicpc.net/problem/1182
public class Main_1182 {
	
	static int N,S;
	static int nums[];
	static int cnt; // 합이 S가 되는 부분 수열의 개수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}		
		dfs(0,0,0);
		System.out.println(cnt);

	}
	
	public static void dfs(int depth, int sum, int size) {
		if(depth==N) {
			if(size>0 && sum==S) { // 합이 0일 때, 아무것도 선택되지 않은 경우 예외처리
				cnt++;
			}
			return;
		}

		dfs(depth+1,sum+nums[depth],size+1); //원소 선택O
		dfs(depth+1,sum,size); //원소 선택X
	}

}
