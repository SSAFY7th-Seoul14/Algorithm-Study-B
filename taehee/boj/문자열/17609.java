package com;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int t = Integer.parseInt(br.readLine());
    	for (int i = 0; i < t; i++) {
    		String s = br.readLine();
    		System.out.println(palind(s));
		}
    }

	private static int palind(String s) {
		int ans = 0; //같지 않아서 지우는 갯수
		int l = 0;
		int r = s.length()-1;
		
		while(l < r) {
			if(s.charAt(l) == s.charAt(r)) {
				l++;
				r--;
			}else {
				ans++;
				//l 지워보기
				boolean leftFlag = true;
				int left = l+1;
				int right = r;
				while(left < right) {
					if(s.charAt(left)==s.charAt(right)) {
						left++;
						right--;
					}else {
						leftFlag = false;
						break;
					}
				}
				//r 지워보기
				boolean rightFlag = true;
				left = l;
				right = r-1;
				while(left < right) {
					if(s.charAt(left)==s.charAt(right)) {
						left++;
						right--;
					}else {
						rightFlag = false;
						break;						
					}
				}
				if(leftFlag || rightFlag) return 1; //하나라도 되면 유사회문
				else return 2;
			}
		}
		return ans;
	}
    
}