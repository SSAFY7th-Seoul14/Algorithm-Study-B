package com;

import java.io.*;
import java.util.*;

public class Solution {
	static int n,d,k,c, res, cnt;
	static int[] dishes,ate;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); //접시수
		d = Integer.parseInt(st.nextToken()); //초밥 가짓수
		k = Integer.parseInt(st.nextToken()); //연속해서 먹는 수
		c = Integer.parseInt(st.nextToken()); //쿠폰번호
		
		dishes = new int[n];
		ate = new int[d+1];
		for (int i = 0; i < n; i++) {
			dishes[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < k; i++) { //일단 처음에 연속으로 k개 먹기
			if(ate[dishes[i]] == 0) res++; //먹은적 없는경우만 + 1
			ate[dishes[i]]++; // i번 해당하는 음식번호 +1
		}
		cnt = res; //처음 연속k개 먹은 가짓수
		res = (ate[c] == 0) ? cnt+1 : cnt; // 쿠폰번호 안먹었으면 + 1
		
		slidingWindow();
		
		System.out.println(res);
	}

	private static void slidingWindow() {
		int start = k;
		
		while(true) {
			ate[dishes[(start-k)%n]]--; //이전에 먹은거 되돌려놓기
			if(ate[dishes[(start-k)%n]] == 0) cnt--; //중복아닌거 out, -1
			
			if(ate[dishes[start%n]] == 0) cnt++; //먹은적 없는거 먹으면 +1
			ate[dishes[start%n]]++;//먹은거 표시
			
			res = Math.max(ate[c]==0 ? cnt+1 : cnt, res);
			
			if(start++ == (n-1)+k) break;
		}
	}
}
