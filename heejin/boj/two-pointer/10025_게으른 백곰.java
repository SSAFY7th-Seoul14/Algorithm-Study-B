package com.boj;

import java.io.*;
import java.util.*;

// BOJ / 게으른 백곰 / S4 / 25분
// https://www.acmicpc.net/problem/10025
public class Main_10025 {
	static int N, K;
	static int map[];
	static int res; // 얼음들의 합의 최댓값

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 얼음 양동이 개수
		K = Integer.parseInt(st.nextToken()); // 이동 가능 거리

		map = new int[1000001];
		int max_x = 0; // 최대 x 좌표

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken()); // 얼음의 양
			int x = Integer.parseInt(st.nextToken()); // 양동이의 좌표
			map[x] = g;
			max_x = Math.max(max_x, x);
		}

		// 슬라이딩 윈도우 -> 2*K+1 크기
		if(K>=max_x/2+1) {	//이동 방향이 전체를 커버할 경우
			for(int i=0;i<=max_x;i++)
				res +=map[i];
		}else {
			int sum = 0;
			for (int i = 0; i < 2 * K + 1; i++) { // 초기 세팅
				sum += map[i];
			}
			res = sum;
			for (int i = 0; i < max_x - 2 * K; i++) {
				// 앞에값 빼기
				sum -= map[i];
				// 뒤에 값 더하기
				sum += map[i + 2 * K + 1];
				res = Math.max(res, sum);
			}
		}
		
		System.out.println(res);

	}
}
