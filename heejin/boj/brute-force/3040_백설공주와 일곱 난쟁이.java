package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//BOJ / 백설공주와 일곱 난쟁이 / B2 / 12분
// https://www.acmicpc.net/problem/1260
public class Main_3040 {
	static int[] input = new int[9];
	static int[] nums = new int[7];
	static int[] result = new int[7];
	static int R = 7;
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<9;i++) { // 9마리 난쟁이 입력 받기
			input[i] = Integer.parseInt(br.readLine());
		}
		combination(0,0); //9 난쟁이에서 7난쟁이 고르는 조합 시작
		//출력
		for(int i=0;i<result.length;i++) {
			sb.append(result[i]).append("\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
	
	public static void combination(int idx, int start) {
		if(idx==R) { // 난쟁이 7마리 되면
			int sum=0;
			for(int i=0;i<nums.length;i++) {
				sum+=nums[i];
			}
			if(sum==100) { //합이 100일 경우 정답 변수에 저장
				for(int i=0;i<nums.length;i++) {
					result[i]=nums[i];
				}
			}
			return;
				
		}
		for(int i=start;i<9;i++) {
			nums[idx] = input[i];
			combination(idx+1, i+1);
		}
	}

}
