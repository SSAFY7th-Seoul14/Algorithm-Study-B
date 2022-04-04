package com;

import java.io.*;
import java.util.*;

public class Main {
			
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int n = Integer.parseInt(st.nextToken());
    	int h = Integer.parseInt(st.nextToken());
    	
    	int[] top = new int[n/2]; //종유석
    	int[] down = new int[n/2]; //석순
    	
    	for (int i = 0; i < n/2; i++) {
			down[i] = Integer.parseInt(br.readLine());
			top[i] = Integer.parseInt(br.readLine());
		}
    	
    	Arrays.sort(down);
    	Arrays.sort(top);
    	
    	int min = Integer.MAX_VALUE; //부수는 갯수 최솟값
    	int cnt = 0; //구간 갯수
    	
    	for (int i = 1; i <= h; i++) { //h증가시키면서 탐색
    		
    		int destory = search(0, n/2, i, down) + search(0, n/2, h-i+1, top);
			
			if(min == destory) {
				cnt++;
				continue;
			}
			if(min > destory) {
				min = destory;
				cnt = 1;
			}
		}
    	System.out.println(min + " " + cnt);
    }

	private static int search(int l, int r, int h, int[] arr) {
		while(l<r) {
			int mid = (l+r) / 2;
			
			if(arr[mid] >= h) {
				r = mid;
			}else {
				l = mid + 1;
			}
		}
		return arr.length - r;
	}
    
}