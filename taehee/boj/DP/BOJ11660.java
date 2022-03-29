package com;

import java.io.*;
import java.util.*;

public class Main {
			
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	
    	int[][] arr = new int[n+1][n+1];
    	for (int i = 1; i <= n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j = 1; j <= n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				arr[i][j] += arr[i][j-1];
			}
		}
    	
    	for (int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int x1 = Integer.parseInt(st.nextToken());
    		int y1 = Integer.parseInt(st.nextToken());
    		int x2 = Integer.parseInt(st.nextToken());
    		int y2 = Integer.parseInt(st.nextToken());
    		
    		int total = 0;
    		for (int x = x1; x <= x2; x++) {
				total += (arr[x][y2] - arr[x][y1-1]);
			}
    		System.out.println(total);
		}
    }
    
}