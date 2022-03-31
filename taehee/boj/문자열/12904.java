package com;

import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String s = br.readLine();
    	String t = br.readLine();
    	
    	int sa = 0;
    	int sb = 0;
    	int ta = 0;
    	int tb = 0;
    	for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == 'A') sa++;
			else sb++;
		}
    	for (int i = 0; i < t.length(); i++) {
    		if(t.charAt(i) == 'A') ta++;
    		else tb++;
    	}
    	int ans = 0;
    	while(true) {
    		if(t.charAt(t.length()-1) == 'A') {
    			if(sa != ta) {
    				t = one(t);
    				ta--;
    			}
    			else {
    				t = two(t);
    				tb--;
    			}
    		}else {
    			if(sb != tb) {
    				t = two(t);
    				tb--;
    			}
    			else {
    				t = one(t);    			
    				ta--;
    			}
    		}
    		
    		if(s.equals(t)) {
    			ans = 1;
    			break;
    		}
    		if(ta < sa || tb < sb) {
    			ans = 0;
    			break;
    		}
    	}
    	
    	System.out.println(ans);
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