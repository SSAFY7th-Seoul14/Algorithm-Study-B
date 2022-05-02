package com;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int[][][][] dp = new int[w+1][h+1][2][2];
		
		for (int i = 2; i <= w; i++) dp[i][1][0][0] = 1;
		for (int i = 2; i <= h; i++) dp[1][i][1][0] = 1;
		
		for (int i = 2; i <= w; i++) {
			for (int j = 2; j <= h; j++) {
				dp[i][j][0][0] = (dp[i-1][j][0][0] + dp[i-1][j][0][1]) % 100000;
				dp[i][j][0][1] = dp[i-1][j][1][0];
				dp[i][j][1][0] = (dp[i][j-1][1][0] + dp[i][j-1][1][1]) % 100000;
				dp[i][j][1][1] = dp[i][j-1][0][0];
			}
		}
		int sum = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				sum += dp[w][h][i][j];
			}
		}
		System.out.println(sum % 100000);
	}

}
