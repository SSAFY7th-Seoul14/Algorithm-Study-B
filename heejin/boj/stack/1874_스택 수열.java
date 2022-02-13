package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

//BOJ / 스택 수열 / S3 / 20분
// https://www.acmicpc.net/problem/1874
public class Main_1874 {

	public static void main(String[] args) throws Exception{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		int cur = 1; // 스택에 push할 현재 수
		boolean flag = true; //수열을 만들 수 있는지 여부
		
		out: for(int i=0;i<N;i++) {
			int input = Integer.parseInt(br.readLine());
			while(true) {
				if(stack.isEmpty()) { // 스택이 비어있을 경우 1 삽입
					stack.push(cur);
					cur++;
					sb.append("+\n");
				}
				else if(stack.peek()<input) { 
					stack.push(cur);
					sb.append("+\n");
					cur++;
					
				}
				else if(stack.peek()==input) {
					stack.pop();
					sb.append("-\n");
					break;
				}	
				else {				//오름차순 만족X이므로 NO 출력
					System.out.println("NO");
					flag = false;
					break out;
				}
			}
		}
		if(flag)
			bw.write(sb.toString());
			
		bw.close();
		br.close();
	}
}
