package com;

import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	int[] arr = new int[n];
    	int sum = 0;
    	
    	int max = 0;
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < n; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    		sum += arr[i];
    		max = Math.max(max, arr[i]);
		}
    	
    	int m = Integer.parseInt(br.readLine());
    	int min = 0;
    	int mid = 0; //최대 예산
    	
    	if(sum <= m) mid = max;
    	else {
    		while(min < max) {
    			mid = (max+min) / 2;
    			if(mid == min) break;
    			int total = 0;
    			for (int i = 0; i < n; i++) {
					if(arr[i] > mid) total += mid;
					else total += arr[i];
				}
    			
    			if(total < m) min = mid;
    			else if(total>m) max = mid;
    			else break;
    		}
    	}
    	System.out.println(mid);
    }

}