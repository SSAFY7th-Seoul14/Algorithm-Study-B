package com;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	
    	int[] dp = new int[31];
    	dp[2] = 3;
    	for (int i = 4; i < n+1; i++) {
			if(i%2==0) {
				dp[i] = 3 * dp[i-2] + 2; //이전거 곱하기 3 + 각경우마다 2가지추가(경계깨면서 놓는경우)
				for (int j = 2; j <= i-4; j = j+2) { //[8] = [2]*[6] + [4]*[4]
					dp[i] += dp[j]*2;
				}
			}
		}
    	System.out.println(dp[n]);
    }
    
}