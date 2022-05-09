package com;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int[] alp = new int[26]; //각 문자 몇개있는지..가운데들어가는거만 홀수개 가능
		for (int i = 0; i < s.length(); i++) {
			alp[s.charAt(i)-'A']++;
		}
		int odd = 0;
		for (int i = 0; i < 26; i++) {
			if(alp[i]%2 == 1) odd++;
		}
		String ans = "";
		if(odd>1) ans = "I'm Sorry Hansoo";
		else {
			char tmp = 0;
			for (int i = 25; i >= 0; i--) {
				if(alp[i]>0) {
					if(alp[i]%2 == 1) {
						tmp = (char) (i + 'A');
					}
					for (int j = 0; j < alp[i]/2; j++) {
						ans = ans + (char)(i+'A'); //뒤에 
						ans = (char)(i+'A') + ans; //앞에
					}
				}
			}
			if(tmp != 0) { //가운데 넣어야할때
				ans = ans.substring(0,ans.length()/2) + tmp + ans.substring(ans.length()/2, ans.length());
			}
		}
		System.out.println(ans);
	}

}
