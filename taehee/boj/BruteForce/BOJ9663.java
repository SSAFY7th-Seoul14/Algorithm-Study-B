package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	
	static int[] col;
	static int ans = 0;
	static int n;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		col = new int[n+1];
		
		setQueen(1);
		System.out.println(ans);
		
	}
	
	private static void setQueen(int rowNo) {
		//이전상황 안되는 경우  돌아감
		if(!isAvailable(rowNo-1)) {
			return;
		}
		//퀸 배치 다성공한경우
		if(rowNo > n) {
			ans++;
			return;
		}
		//퀸 배치할것
		for(int i=1; i<=n; i++) {
			col[rowNo] = i;
			setQueen(rowNo+1);
		}
	}

	private static boolean isAvailable(int rowNo) {
		for(int i=1; i<rowNo; i++) {
			if(col[rowNo] == col[i] || rowNo - i == Math.abs(col[rowNo]-col[i])) return false;
		}
		return true;
	}
}
