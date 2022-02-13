package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// AC / 골드 5 / 90분
// https://www.acmicpc.net/problem/5430
//1차 : Collections.sort 시간초과/ 출력 때문에 오래 걸림..
public class Main_5430 {

	public static void main(String[] args) throws Exception{
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		 out: for(int t=1;t<=T;t++) {
			//명령어 입력받기
			String cmd = br.readLine();
			Deque<Integer> list = new ArrayDeque<>();
			int len = Integer.parseInt(br.readLine());
			String input = br.readLine();

			st = new StringTokenizer(input,",[]"); //,[]로 token
			
			while(st.hasMoreTokens()) { // 숫자 입력받기
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			boolean dir = true; //true: 정순, false: 역순
			// cmd 시작
			inner: for(int i=0;i<cmd.length();i++) {
				if(cmd.charAt(i)=='R') { 
					dir= !dir;
				}
				else if(cmd.charAt(i)=='D') {
					if(!list.isEmpty()) {
						if(dir) {
							list.removeFirst(); //앞에서부터 삭제
						}
						else {
							list.removeLast();	//뒤에서부터 삭제
						}
					}
					else {
						sb.append("error\n");
						continue out;
					}
				}
			}
			//출력
			sb.append("[");
			if(dir) {
				while(list.size() > 1) {
					sb.append(list.removeFirst()).append(",");
				}
			}
			else {
				while(list.size() > 1) {
					sb.append(list.removeLast()).append(",");
				}
			}
			if(!list.isEmpty()) sb.append(list.remove());
			sb.append("]\n");
			
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}
