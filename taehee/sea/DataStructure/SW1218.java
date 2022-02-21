package com;

import java.util.Scanner;
import java.util.Stack;

public class SW1218 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		for(int tc = 1; tc<=10; tc++) {
			Stack<Character> stk = new Stack<>();
			int n = sc.nextInt();
			String str = sc.next();
			
			for (int i = 0; i < n; i++) {
				char c = str.charAt(i);
				
				if(c ==')' && stk.peek()=='(') stk.pop();
				else if(c =='}' && stk.peek()=='{') stk.pop();
				else if(c ==']' && stk.peek()=='[') stk.pop();
				else if(c =='>' && stk.peek()=='<') stk.pop();
				else stk.push(c);
			}
			
			System.out.println("#"+tc + " " + (stk.isEmpty() ? "1" : "0"));
		}
	}

}
