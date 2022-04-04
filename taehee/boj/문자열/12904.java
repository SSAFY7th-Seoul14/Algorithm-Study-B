package com;

import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String s = br.readLine();
    	String t = br.readLine();
    	
    	int ans = 0;
    	while(s.length() < t.length()) {
    		if(t.charAt(t.length()-1) == 'A') {
    			t = one(t);
    		}else {
    			t = two(t);
    		}
    	}
    	if(s.equals(t)) System.out.println(1);
    	else System.out.println(0);
    }
    private static String one(String str) {
    	return str.substring(0, str.length()-1);
    }
    private static String two(String str) {
    	StringBuilder sb = new StringBuilder();
    	for (int i = str.length()-2; i >= 0; i--) {
			sb.append(str.charAt(i));
		}
    	return sb.toString();
    }
    
}