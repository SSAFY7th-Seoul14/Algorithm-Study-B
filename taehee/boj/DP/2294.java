package com;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int[] dp = new int[k+1];
		Arrays.fill(dp, 100001);
		dp[0] = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = arr[i]; j <= k; j++) {
				dp[j] = Math.min(dp[j], dp[j-arr[i]]+1);
			}
		}
		System.out.println(dp[k]==100001 ? -1 : dp[k]);
	}

}