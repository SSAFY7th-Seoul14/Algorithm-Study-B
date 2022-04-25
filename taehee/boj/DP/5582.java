package com;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		String b = br.readLine();
		int la = a.length();
		int lb = b.length();
		int max = 0;
		int[][] dp = new int[la+1][lb+1]; //a가 세로 b가 가로
		for (int i = 1; i < la+1; i++) {
			for (int j = 1; j < lb+1; j++) {
				if(a.charAt(i-1) == b.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		System.out.println(max);
	}

}