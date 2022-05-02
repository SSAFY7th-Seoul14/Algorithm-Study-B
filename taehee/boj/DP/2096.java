package com;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] maxdp = new int[3];
		int[] mindp = new int[3];
		
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(i==0) {
				maxdp[0] = a;
				mindp[0] = a;
				maxdp[1] = b;
				mindp[1] = b;
				maxdp[2] = c;
				mindp[2] = c;
			}else {
				int t1 = maxdp[0];
				int t2 = maxdp[1];
				int t3 = maxdp[2];
				maxdp[0] = a + Math.max(t1, t2);
				int m = Math.max(t1, t2);
				maxdp[1] = b + Math.max(m, t3);
				maxdp[2] = c + Math.max(t2, t3);
				
				t1 = mindp[0];
				t2 = mindp[1];
				t3 = mindp[2];
				mindp[0] = a + Math.min(t1,t2);
				m = Math.min(t1, t2);
				mindp[1] = b + Math.min(m, t3);
				mindp[2] = c + Math.min(t2,t3);
			}
		}
		int max = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			max = Math.max(max, maxdp[i]);
			min = Math.min(min, mindp[i]);
		}
		System.out.println(max + " " + min);
	}

}
