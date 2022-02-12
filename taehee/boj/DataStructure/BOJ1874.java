package com.ssafy;

import java.util.Scanner;
import java.util.Stack;

public class BOJ1874 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		//수열 순서 담긴 배열
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		Stack<Integer> stk = new Stack<>();
		Stack<Integer> res = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		int idx = 0; //수열순서
		for(int i=1; i<=n; i++){ //1~n까지 오름차순
			while(!stk.isEmpty() && stk.peek()==arr[idx]) {					
				res.push(stk.pop());
				idx++;
				sb.append("-\n");
			}
			stk.push(i);
			sb.append("+\n");
		}
		while(!stk.isEmpty()) {
			res.push(stk.pop());
			sb.append("-\n");
		}
		boolean isSame = true;
		for(int i=0;i<n; i++) {
			if(arr[i] != res.get(i)) {
				isSame=false;
				break;
			}
		}
		System.out.println(isSame ? sb : "NO");
	}

}
