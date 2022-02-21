package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//BOJ / 집합 / S5 / 9분
// https://www.acmicpc.net/problem/11723
public class Main_11723 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int M = Integer.parseInt(br.readLine());
		boolean[] bit = new boolean[21]; // 집합 S에 원소 있는지 여부 확인(Index와 숫자값 일치)
		
		StringTokenizer st;
		int idx=0;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();

			switch(cmd) {
			case "add":
				idx = Integer.parseInt(st.nextToken());
				bit[idx] = true;
				break;
			case "remove":
				idx = Integer.parseInt(st.nextToken());
				bit[idx] = false;
				break;
			case "check":
				idx = Integer.parseInt(st.nextToken());
				if(bit[idx])
					sb.append("1").append("\n");
				else
					sb.append("0").append("\n");
				break;
			case "toggle":
				idx = Integer.parseInt(st.nextToken());
				bit[idx]= !bit[idx];
				break;
			case "all":
				for(int j=1;j<21;j++) {
					bit[j]=true;
				}
				break;
			case "empty":
				for(int j=1;j<21;j++) {
					bit[j]=false;
				}
				break;
			}
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}
