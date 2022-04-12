package com;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		int[][] dp = new int[n][2]; //값, 어디서이어지는지 인덱스
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if(arr[i] > arr[j] && dp[j][0] > dp[i][0]) {
					dp[i][0] = dp[j][0];
					dp[i][1] = j; //j에서 이어진
				}
			}
			dp[i][0]++;
		}
		//최대값,인덱스 찾기
		int max = 0;
		int maxIdx = 0;
		for (int i = 0; i < n; i++) {
			if(dp[i][0] > max) {
				max = dp[i][0];
				maxIdx = i;
			}
		}
		//LIS담을 배열 
		int[] maxArr = new int[max];
		int i = maxIdx; //끝에서부터 넣어준다
		int j = max-1;
		while(true) {
			maxArr[j] = arr[i];
			i = dp[i][1];
			if(j-- == 0) break;
		}
		System.out.println(max);
		for (int k = 0; k < max; k++) {
			System.out.print(maxArr[k]+" ");
		}
	}

}