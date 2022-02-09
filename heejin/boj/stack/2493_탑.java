package com.ssafy.pcs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_test {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Stack<int[]> stack = new Stack<int[]>();
		int[] input = new int[N]; // 탑 높이 저장
		
		StringTokenizer st= new StringTokenizer(br.readLine()," ");
		
		for(int i=0;i<N;i++) { // 탑 높이 입력받음
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<N;i++) { // 탑 수만큼 비교
			int curTop = input[i]; 
			
			//1. 이전 탑이 있는 경우: 스택에 값이 있음. 
			while(!stack.isEmpty()) {
				if(stack.peek()[1] < curTop) { //stack의 top이 현재 탑 높이보다 낮을 경우, 어차피 신호 못 미치므로 스택에서 제거
					stack.pop(); // 어차피 앞으로도 신호를 못 받음
				}
				else { // stack의 top이 현재 탑 높이보다 높으면 출력
					sb.append(stack.peek()[0]+" "); 
					break;
				}			
			}
			//2. 이전 탑이 없는 경우
			if(stack.isEmpty()) //만약 신호를 수신할 탑이 없어서 스택이 전부 pop되었을 경우
				sb.append(0+" ");
			//3. 이전 탑이 있거나 없거나 모두 현재 탑 정보는 스택에 저장해놔야 함
			stack.push(new int[] {i+1,input[i]});	//읽어온 입력값(현재 탑) 스택에 push
		}
		//출력
		bw.write(sb.toString());
		br.close();
		bw.close();
		
	}

}
