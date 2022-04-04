package com;

import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int h = Integer.parseInt(st.nextToken());
    	int w = Integer.parseInt(st.nextToken());
    	int[] arr = new int[w];
    	st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < w; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
    	int ans = 0;
    	int left = 0; //왼쪽 기둥 인덱스
    	
    	while(left < w-1) {
    		int right = left+1; // 오른쪽 기둥 인덱스
    		// 왼쪽기둥보다 높으면 오른쪽기둥이된다.
    		while(right < w-1 && arr[right] < arr[left]) {
    			right++;
    		}
    		//왼쪽기둥,오른쪽기둥중에 작은값 기준으로 물이찬다
    		int line = Math.min(arr[left], arr[right]);
    		//왼쪽기둥 ~ 오른쪽기둥,, 물채우기  (4 3 2 1 같은 경우 대비해서 line보다 작을때만 더해주기)
    		for (int i = left+1; i < right; i++) {
    			if(arr[i] < line) ans += line - arr[i];
    		}
    		//다채웠으면 다음단계 가기 위해 left 업데이트
    		left = right;
    	}
    	
    	System.out.println(ans);
    }
    
}