package com;

import java.io.*;
import java.util.*;

public class Main {
	
	static String s;
	static String b;
	static Stack<Character> stk = new Stack<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();
		b = br.readLine();
		
		for(int i = 0; i<s.length(); i++) {
			stk.push(s.charAt(i));
			if(stk.size() >= b.length() && bomb(i)) {
				for (int j = 0; j < b.length(); j++) {
					stk.pop();
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		while(!stk.isEmpty()) {
			sb.append(stk.pop());
		}
		sb.reverse();
		System.out.println(sb.length()==0 ? "FRULA" : sb);
	}

	private static boolean bomb(int i) {
		//중간에 하나라도 다르면 false, 다같으면 true
		for (int j = 0; j < b.length(); j++) {
			if(stk.get(stk.size()- b.length() + j) != b.charAt(j))
				return false;
		} 
		return true;
	}

}
