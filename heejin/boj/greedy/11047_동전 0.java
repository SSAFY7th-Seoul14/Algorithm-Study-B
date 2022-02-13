package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//BOJ / S3 / 동전 0 / 20분
//https://www.acmicpc.net/problem/11047
//1차 - 시간초과
public class Main_11047 {
	
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int [] moneys = new int[N];
		for(int i=0;i<N;i++) {
			moneys[i] = Integer.parseInt(br.readLine());
		}
		int total_cnt = 0; // 총 동전 갯수
		int last = N-1; // 가장 마지막 화폐 단위
		while(K!=0) {
			for(int i=last;i>=0;i--) { //큰 화폐 -> 작은 화폐 순으로 탐색
				if(K/moneys[i]!=0) { 
					int cnt = K/moneys[i]; //화폐 사용 개수
					K -= cnt*moneys[i]; // 화폐 값 갱신
					total_cnt +=cnt;
					last = i-1;  // 최고 단위 화폐 갱신(다음 반복문 때 그것보다 큰 것들은 탐색 X)
					break;
				}
			}
			
		}
		sb.append(total_cnt);
		bw.write(sb.toString());
		bw.close();
		br.close();	
	}
}
