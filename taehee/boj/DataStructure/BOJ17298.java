package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n]; //수열
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> stk = new Stack<>();
		
        for (int i = 0; i < n; i++) {
        	//스택이 비어잇지 않으면서 
        	//top원소가 가리키는 원소보다 현재값이 큰 경우
        	//계속 pop하면서 arr값 update
        	while(!stk.isEmpty() && arr[stk.peek()] < arr[i]) {
        		arr[stk.pop()] = arr[i];
        	}
        	stk.push(i);
        }
        //스택에 들어있는 값들은 -1인 경우이므로 update
        while(!stk.isEmpty()) {
        	arr[stk.pop()] = -1;
        }
        StringBuilder sb = new StringBuilder();
        for(int i : arr) {
        	sb.append(i).append(" ");
        }
        System.out.println(sb);
	}

}
