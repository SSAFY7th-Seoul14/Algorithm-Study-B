package com.boj;

import java.io.*;
import java.util.*;
// BOJ / 동전2 / S1 / 25분
// https://www.acmicpc.net/problem/2294
public class Main_2294 {
	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 동전 종류
		K = Integer.parseInt(st.nextToken()); // 동전 가치 합

		int coin[] = new int[N]; // 동전 종류
		for (int i = 0; i < N; i++)
			coin[i] = Integer.parseInt(br.readLine());
		Arrays.sort(coin); //동전 종류를 오름차순으로 정렬

		int dp[] = new int[K + 1]; // i원 만들기 위한 동전 최소 개수
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 1; i < K + 1; i++) { // i원 만들기 위한 것
			for(int j=0;j<N;j++) {
				if(i-coin[j]>=0) {
					if(dp[i-coin[j]]!=Integer.MAX_VALUE)
						dp[i] = Math.min(dp[i-coin[j]]+1, dp[i]); //현재 동전 사용했을 때와 사용 안 했을 때 중 최소값 선택
				}
				else	//현재 동전 종류가 만들고자 하는 값보다 크면 탈출
					break;
			}
		}
		System.out.println(dp[K]==Integer.MAX_VALUE?-1:dp[K]);
	}
}
