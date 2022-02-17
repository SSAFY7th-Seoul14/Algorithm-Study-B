package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ / 오큰수 / G4
// https://www.acmicpc.net/problem/17298
public class Main_17298 {
	
	static int N;
	static int[] nums;
	static int[] ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		ans = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//숫자 입력받기
		for(int i=0;i<N;i++) {
			nums[i]=Integer.parseInt(st.nextToken());
		}
		
		//오큰수 로직 시작
		Stack<Integer> stack = new Stack<>();
		stack.push(nums[N-1]); 
		ans[N-1]=-1;
		for(int i=N-2;i>=0;i--) {
			if(stack.isEmpty()) { // 스택이 비어있으면 -1 출력
				ans[i]=-1;
				stack.push(nums[i]);
				continue;
			}
			
			int top = stack.peek();
			
			if(top>nums[i]) { // 오른쪽에 큰 수가 있을 경우
				ans[i]=top;
				stack.push(nums[i]);
			}
			else { //오른쪽에 큰 수가 없을 경우
				while(!stack.isEmpty() && stack.peek()<=nums[i]) {
					stack.pop();
					
				}
				if(stack.isEmpty()) {
					ans[i]=-1;
					stack.push(nums[i]);
					continue;
				}
				ans[i]=stack.peek();
				stack.push(nums[i]);
			}
		}
		
		//정답 출력
		for(int i=0;i<N;i++) {
			sb.append(ans[i]).append(" ");
		}
		
		bw.write(sb.toString());
		bw.close();
		br.close();
		
		
		
	}

}
