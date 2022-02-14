package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int m = Integer.parseInt(br.readLine());
		//비트마스킹을 위한 int형 변수 선언
		int bit = 0;
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int x;
			if(cmd.equals("add")) {
				x = Integer.parseInt(st.nextToken());
				//OR연산 : 있으면 무시 없으면 1로 바꿀수있음
				bit |= (1 << (x-1));
				
			}else if(cmd.equals("remove")) {
				x = Integer.parseInt(st.nextToken());
				bit = bit & ~(1 <<(x-1));
				
			}else if(cmd.equals("check")) {
				x = Integer.parseInt(st.nextToken());
				sb.append((bit & (1 << (x - 1))) != 0 ? "1\n" : "0\n");
				
			}else if(cmd.equals("toggle")) {
				x = Integer.parseInt(st.nextToken());
				bit ^= (1 << (x-1));
				
			}else if(cmd.equals("all")) {
				bit |= (~0);
				
			}else if(cmd.equals("empty")) {
				bit &= 0;
			}
		}
		System.out.println(sb);
	}
	
}
