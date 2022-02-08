package com.ssafy.pcs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class SW_1218 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int res=0;
		int len = 0;
		String str;
		Stack<Character> st;
		
		for(int t=1;t<=10;t++) {
			st = new Stack<Character>(); //TC마다 초기화
			res=1;
			len = Integer.parseInt(br.readLine());
			str = br.readLine();
			for(int i=0;i<len;i++) { //괄호들 stack에 삽입
				char ch = str.charAt(i);
				char top;
				switch(ch) {
				case '(':
				case'{':
				case'[':
				case'<':
					st.push(ch);
					break;
				case')':
					if(st.isEmpty()) {
						res=0;
						break;
					}
					top = st.peek();
					if(top!='(' || st.isEmpty()) {
						res=0;
						break;
					}	
					st.pop();
					break;
				case '}':
					if(st.isEmpty()) {
						res=0;
						break;
					}
					top = st.peek();
					if(top!='{'|| st.isEmpty()){
						res=0;
						break;
					}
					st.pop();	
					break;
				case ']':
					if(st.isEmpty()) {
						res=0;
						break;
					}
					top = st.peek();
					
					if(top!='['|| st.isEmpty()) {
						res=0;
						break;
					}
					st.pop();
					break;
				case '>':
					if(st.isEmpty()) {
						res=0;
						break;
					}
					top = st.peek();
					
					if(top!='<'|| st.isEmpty()) {
						res=0;
						break;
					}
					st.pop();
					break;
				default:
					break;
				}
			}
			System.out.println(st.size());
			System.out.printf("#%d %d%n",t,res);
				
			}
		}
	}


