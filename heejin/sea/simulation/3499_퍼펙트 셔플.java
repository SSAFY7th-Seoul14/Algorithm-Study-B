package com.ssafy.pcs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;
import java.util.StringTokenizer;

//SWEA 퍼펙트 셔플 D3 - 22분
public class Solution_3499 {
	static List<String> cards = new ArrayList<String>(); //input
	static List<String> first = new ArrayList<String>();
	static List<String> second = new ArrayList<String>();
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int t=1;t<=T;t++) {
			//초기화
			cards.clear(); 
			first.clear();
			second.clear();
			
			//N, 카드 입력
			N= Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine()," ");
			for(int i=0;i<N;i++) {
				cards.add(st.nextToken());
			}
			
			//처리  
			int mid = (int) Math.ceil(N/2.0); //짝수면 N/2, 홀수면 N/2+1
			
			for(int i=0;i<mid;i++) {
				first.add(cards.get(i));
			}
			for(int i=mid;i<N;i++) {
				second.add(cards.get(i));
			}

			//출력
			sb.append("#"+t+" ");
			
			for(int i=0;i<N/2;i++) {
				sb.append(first.get(i)+" ");
				sb.append(second.get(i)+" ");
			}
			if(N%2==1)
				sb.append(first.get(N/2));	
			sb.append('\n');
		}
		System.out.print(sb);
	
	}

}
