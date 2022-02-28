package com.ssafy.study;

import java.io.*;
import java.util.*;

// BOJ / 연산자 끼워넣기 / S1
// https://www.acmicpc.net/problem/14888
public class Main_14888_2 {
	static int N;
	static int[] nums; //입력 숫자값
	static int max = Integer.MIN_VALUE, min=Integer.MAX_VALUE; //최댓값, 최솟값
	static int add, sub, mul, div;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N =Integer.parseInt(br.readLine());
		nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N개의 숫자 저장
		for(int i=0;i<N;i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine()); //4칙 연산 횟수 입력
		add = Integer.parseInt(st.nextToken());
		sub = Integer.parseInt(st.nextToken());
		mul = Integer.parseInt(st.nextToken());
		div = Integer.parseInt(st.nextToken());
	
		dfs(1,nums[0],add,sub,mul,div);
		
		
		System.out.println(max);
		System.out.println(min);
	}

	private static void dfs(int idx, int res, int add, int sub, int mul, int div) {
		if(idx==N) {
			max = Math.max(max, res);
			min = Math.min(min, res);
			return;
		}		
		if(add!=0) // 덧셈
			dfs(idx+1, res + nums[idx], add-1, sub, mul, div);
		if(sub!=0) // 뺄셈
			dfs(idx+1, res - nums[idx], add, sub-1, mul, div);
		if(mul!=0) //곱셈
			dfs(idx+1, res * nums[idx], add, sub, mul-1, div);
		if(div!=0) //나눗셈
			dfs(idx+1,(int)(res/nums[idx]),add,sub,mul,div-1);
	
		
	}

		
	

}
