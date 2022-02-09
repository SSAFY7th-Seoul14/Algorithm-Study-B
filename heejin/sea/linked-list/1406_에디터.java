package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

//[BOJ] 에디터 / s3 / 38분 / https://www.acmicpc.net/problem/1406
//1차(링크드리스트) - 시간초과
public class Main_1406_2 {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>(); //커서 왼쪽 문자 저장
		Stack<Character> stack2 = new Stack<>(); //커서 오른쪽 문자 저장
		
		String input = br.readLine();
		char[] arr=  input.toCharArray();
		for(int i=0;i<input.length();i++) {
			stack.push(arr[i]);
		}

		int M = Integer.parseInt(br.readLine());
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			if(cmd.equals("P")) {
				char ch = st.nextToken().charAt(0);
				stack.push(ch); //커서 왼쪽에 삽입
			}
			else if(cmd.equals("L")) {
				if(!stack.isEmpty())
					stack2.push(stack.pop()); //커서 왼쪽으로 옮기므로, 왼쪽 스택의 top 값을 stack2(오른쪽)에 push

			}
			else if(cmd.equals("D")) {
				if(!stack2.isEmpty())
					stack.push(stack2.pop()); //커서 오른쪽으로 옮기므로, 오른쪽 스택의 top 값을 stack(왼쪽)에 push
			}
			else if(cmd.equals("B")) {
				if(!stack.isEmpty()) 
					stack.pop(); //커서 왼쪽 삭제
			}
		}
		while(!stack.isEmpty())
			sb.append(stack.pop());
		
		StringBuilder sb2 = new StringBuilder();
		while(!stack2.isEmpty())
			sb2.append(stack2.pop());
		
		bw.write(sb.reverse().toString()+sb2); //커서 왼쪽 값의 경우 stack에 역순으로 저장되어 있기에 reverse() 처리해줌.
		bw.close();
		br.close();
		
		
		
	}

}
