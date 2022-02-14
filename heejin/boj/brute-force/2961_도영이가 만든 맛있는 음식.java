package com.ssafy.exam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// BOJ / 도영이가 만든 맛있는 음식 / S1 / 18분
// https://www.acmicpc.net/problem/2961
public class Main_2961 {
	
	static List<int[]> list = new ArrayList<>();
	static boolean[] isSelected;
	static int N;
	static long result=Long.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		isSelected = new boolean[N];
		
		for(int i=0;i<N;i++) { //S, B 입력
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			list.add(new int[] {S,B});
		}
		
		subset(0); // 부분집합 생성
		System.out.println(result);
	}

	private static void subset(int idx) {
		if(idx==N) {
			long totalS=1, totalB =0; // 총 S합, B합 값 초기화
			int use=0; //사용 재료 개수
			long diff=0;		// S합과 B합의 차이 값 초기화
			for(int i=0;i<N;i++) {
				if(isSelected[i]) {
					totalS*=list.get(i)[0]; // S끼린 곱하기
					totalB+=list.get(i)[1]; // B끼린 더하기
					use++; //사용 재료 개수 +1 하기
				}
			}
			if(use!=0) { //재료 최소 1개 사용해야 함
				diff = Math.abs(totalS-totalB); // 차이 구하기
				result = Math.min(result, diff); // 최소 차이 구하기
			}
			return;
		}
		
		isSelected[idx]=true;
		subset(idx+1);
		isSelected[idx]=false;
		subset(idx+1);
	}

}
