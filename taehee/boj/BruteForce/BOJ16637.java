package com;

import java.util.*;
import java.io.*;

public class Main {
	
	static boolean[] visit;
	static char[] arr;
	static List<Integer> nums = new ArrayList<>();
	static List<Character> ops = new ArrayList<>();
	static int n, max = Integer.MIN_VALUE;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = br.readLine().toCharArray();
		
		for (int i = 0; i < n; i++) {
			char c = arr[i];
			if(!Character.isDigit(c)) ops.add(c);
			else nums.add(c-'0');
		}
		
		dfs(0, nums.get(0));
		System.out.println(max);
	}
	
	private static void dfs(int cnt, int sum) {
		if(cnt >= ops.size()) {
			max = Math.max(max, sum);
			return;
		}
		
		//괄호 안치고
		int val = cal(sum, nums.get(cnt+1), ops.get(cnt));
		dfs(cnt + 1, val);
		
		//괄호 치고
		if(cnt + 1 < ops.size()) {
			val = cal(nums.get(cnt+1), nums.get(cnt+2), ops.get(cnt+1));
			dfs(cnt+2, cal(sum,val, ops.get(cnt)));
		}
		return;
	}

	private static int cal(int a, int b, char c) {
		if(c == '+') return a+b;
		else if(c == '-') return a-b;
		else return a*b;
	}
} 