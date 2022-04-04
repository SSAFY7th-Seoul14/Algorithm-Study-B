package com;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	int[][] dp = new int[n][10];
    	Arrays.fill(dp[0], 1);
    	
    	for (int i = 1; i < n; i++) {
			for (int j = 0; j < 10; j++) {
				int sum = 0;
				for (int k = j; k < 10; k++) {
					sum += dp[i-1][k];
				}
				dp[i][j] = sum % 10007;
			}
		}
    	int ans = 0;
    	for (int i = 0; i < 10; i++) {
			ans += dp[n-1][i];
		}
    	System.out.println(ans%10007);
    }
    
}