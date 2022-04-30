package com;

import java.io.*;
import java.util.*;

public class Main {
	
	static String s;
	static String b;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();
		b = br.readLine();
		
		int i = 0;
		while(i < s.length()) {
			if(bomb(i)) {
				s = s.substring(0, i) + s.substring(i+b.length());
				i -= b.length()-1;
				if(i<0) i = 0;
			}
			else {
				i++;
			}
		}
		System.out.println(s.length()!=0 ? s : "FRULA");
	}

	private static boolean bomb(int i) {
		//중간에 하나라도 다르면 false, 다같으면 true
		for (int j = i; j < i + b.length(); j++) {
			if(s.charAt(j) != b.charAt(j-i))
				return false;
		} 
		return true;
	}

}
