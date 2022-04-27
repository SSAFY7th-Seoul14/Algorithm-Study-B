package com;

import java.io.*;
import java.util.*;

public class Main {
	
	static boolean[] select = new boolean[26];
	static String[] str;
	static int n,k,ans=0;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		str = new String[n];
		for (int i = 0; i < n; i++) {
			str[i] = br.readLine();
		}
		select['a'-'a'] = true;
		select['c'-'a'] = true;
		select['i'-'a'] = true;
		select['n'-'a'] = true;
		select['t'-'a'] = true;
		comb(0,0);
		System.out.println(ans);
	}

	private static void comb(int start, int cnt) {
		if(cnt == k-5) {
			int count = 0;
			int tmp = 0;
			for(int i=0; i<26; i++) {
				if(select[i]) tmp |= 1<<i;
			}
			for(String s : str) {
				if(check(s,tmp)) count++;
			}
			ans = Math.max(count, ans);
			return;
		}
		for (int i = start; i < 26; i++) {
			if(select[i]) continue;
			select[i] = true;
			comb(i+1,cnt+1);
			select[i] = false;
		}
	}

	private static boolean check(String s, int tmp) {
		for(int i=0; i<s.length(); i++) {
			int order = s.charAt(i) - 'a';
			int res = tmp & (1<<order);
			if(res==0) return false; //안배운 문자가 있다
		}
		return true;
	}

}
