package com.ssafy.webX;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class SW1218_괄호짝짓기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 0; t < 10; t++) {
			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();
			
			Stack<Character> stack = new Stack<>();
			
			int result = 1;
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if(c == '(' || c == '{' || c == '[') {
					stack.push(c);
				}
				else if(c == ')') {
					if(stack.isEmpty() || stack.pop() != '(') {
						result = 0;
						break;
					}
				}
				else if(c == '}') {
					if(stack.isEmpty() || stack.pop() != '{') {
						result = 0;
						break;
					}
				}
				else if(c == ']') {
					if(stack.isEmpty() || stack.pop() != '[') {
						result = 0;
						break;
					}
				}

			}
			if(!stack.isEmpty()) result = 0;
			
			System.out.println("#" + (t+1) + " " + result);
		}
	}

}
