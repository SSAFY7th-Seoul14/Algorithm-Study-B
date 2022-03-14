package com;

import java.util.*;
import java.io.*;

public class Main {

	static int n;
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		long b = sc.nextLong();
		
		int[][] A = new int[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				A[i][j] = sc.nextInt();
			}
		}
		
		int[][] res = DC(A,b);
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(res[i][j]%1000 + " ");
			}
			System.out.println();
		}
	}

	private static int[][] DC(int[][] arr, long num) {
		if(num==1L) {
			return arr;
		}
		
		int[][] result = DC(arr, num/2);
		result = multi(result,result); //제곱해주기
		if(num % 2 == 1L) result = multi(result,arr); //홀수엿으면 한번더 곱해주기   ex) 7 = 3*2 + 1
		return result;
		
	}
	
	private static int[][] multi(int[][] a, int[][] b) {
		int[][] result = new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				for(int k=0; k<n; k++) {
					result[i][j] += a[i][k] * b[k][j];
					result[i][j] = result[i][j] % 1000;
				}
			}
		}
		return result;
	}
	
}