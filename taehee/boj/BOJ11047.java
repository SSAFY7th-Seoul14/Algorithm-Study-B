package com.ssafy;

import java.util.Scanner;
import java.util.Stack;

public class BOJ11047 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		Stack<Integer> stk = new Stack<>();
		for (int i = 0; i < n; i++) {
			stk.push(sc.nextInt());
		}
		
		int cnt = 0;
		while(k>0) {
			if(stk.peek() <= k) {
				int x = k / stk.peek();
				k -= x * stk.peek();
				cnt += x;
			}else {
				stk.pop();
			}
		}
		System.out.println(cnt);
	}

}
