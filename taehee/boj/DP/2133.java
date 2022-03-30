package com;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	
    	int[] dp = new int[31];
    	dp[2] = 3;
    	for (int i = 4; i < n+1; i++) {
			if(i%2==0) {
				dp[i] = 3 * dp[i-2] + 2;
				for (int j = 2; j <= i-4; j = j+2) {
					dp[i] += dp[j]*2;
				}
			}
		}
    	System.out.println(dp[n]);
    }
    
}