package com;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		long left = 1;
		long right = k;
		while(left < right) {
			long mid = (left+right) / 2; //모든행에서 mid이하 수의 개수 찾기
			int cnt = 0;
			
			for (int i = 1; i <= n; i++) { //i행에서 mid이하 개수 : mid/i
				//4*4에서 6이하 수,,
				//1일때 6나오니 min
				cnt += Math.min(mid/i, n);
			}
			
			if(k <= cnt) right = mid;
			else left = mid+1;
		}
		System.out.println(left);
	}
}