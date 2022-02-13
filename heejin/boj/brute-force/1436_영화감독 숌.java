package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//BOJ / 영화감독 숌  / S5 / 15분
//https://www.acmicpc.net/problem/1436
public class Main_1436 {
	
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int cnt=0; // 666 나온 횟수
		int num=666; //시작 숫자
		while(true) {
			String numStr = Integer.toString(num);
			for(int i=0;i<numStr.length()-2;i++) {
				int first = numStr.charAt(i)-'0';
				int second = numStr.charAt(i+1)-'0';
				int third = numStr.charAt(i+2)-'0';
				if(first==6 && second==6 && third==6) { // 연속 3자리가 6일 경우
					cnt++;
					break;
				}
			}
			
			if(cnt==N) { //N번째 숫자인 경우
				sb.append(num);
				break;
			}
			num++;
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
