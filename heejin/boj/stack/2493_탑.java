package com.ssafy.pcs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Stack;
import java.util.StringTokenizer;

//BOJ 탑 (골드 5) 1,2차 - 메모리 초과
public class Main_2493 {
	
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<int[]> tops = new Stack<int[]>();
		
		StringTokenizer st= new StringTokenizer(br.readLine()," ");
		for(int i=0;i<N;i++) {
			int num = Integer.parseInt(st.nextToken());
			while(!tops.isEmpty()) {
				if(tops.peek()[1]<num) //현재 top보다 높이가 낮다면 어차피 신호 못 옴.
					tops.pop();
				else{
					System.out.print(tops.peek()[0]+" ");
					break;
				}
				
			}
			if(tops.isEmpty())
				System.out.print("0 ");
			tops.push(new int[] {i+1,num});
		}
	}		
}
	
	


