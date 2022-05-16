package com;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int left = 0;
		int right = n-1;
		int max = Integer.MAX_VALUE;
		int l = -1;
		int r = -1;
		while(left < right-1) {
			int sum = arr[left] + arr[right];
			
			if(Math.abs(sum) < max) {
				l = left;
				r = right;
				max = Math.abs(sum);
			}
			if(sum > 0) right--;
			else if(sum < 0) left++;
			else break;
		}
		System.out.println(arr[l]+" "+arr[r]);
	}

}
