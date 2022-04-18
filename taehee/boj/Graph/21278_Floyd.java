package com;

import java.io.*;
import java.util.*;

public class Main {
	static int[][] arr;
	static int[] chicken = new int[2];
	static int[] minIdx = {101,101};
	static int min = Integer.MAX_VALUE;
	static int n,m;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n+1][n+1];
		for (int i = 1; i < n+1; i++) {
			Arrays.fill(arr[i], 101);
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
		for (int k = 1; k < n+1; k++) {
			for (int i = 1; i < n+1; i++) {
				for (int j = 1; j < n+1; j++) {
					if(i==j) continue;
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
		comb(1,0);
		System.out.println(minIdx[0]+" "+minIdx[1]+" "+min);
	}

	private static void comb(int start, int cnt) {
		if(cnt == 2) {
			int sum = 0;
			for(int i=1; i<n+1; i++) {
				if(i == chicken[0] || i == chicken[1]) continue;
				int tmp = 101;
				for(int j=0;j<2;j++) {
					tmp = Math.min(arr[i][chicken[j]], tmp);
				}
				sum += 2*tmp;
			}
			if(sum == min) {
				minIdx[0] = Math.min(minIdx[0], chicken[0]);
				minIdx[1] = Math.min(minIdx[1], chicken[1]);
				
			}else if(sum < min) {
				min = sum;
				minIdx[0] = chicken[0];
				minIdx[1] = chicken[1];
			}
			return;
		}
		for(int i=start; i<=n; i++) {
			chicken[cnt] = i;
			comb(i+1, cnt+1);
		}
	}

}