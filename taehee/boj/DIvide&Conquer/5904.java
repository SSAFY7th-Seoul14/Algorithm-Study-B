package com;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(3);
		int k = 0;
		//n이 k번째 수열에 있다.
		while(n > arr.get(k)) {
			arr.add(arr.get(k)*2 + k+4);
			k++;
		}
		while(true) {
			if(k==0) {
				System.out.println(n==1 ? "m" : "o");
				break;
			}
			if(n <= arr.get(k-1)) { //s(k-1)에서  n번째
				k--;
			}else if(n <= arr.get(k-1) + k+3) { //mo...o
				if(n-arr.get(k-1) == 1) {
					System.out.println("m");
					break;
				}else {
					System.out.println("o");
					break;
				}
			}else { //s(k-1)에서 n - (arr.get(k-1) + k+3)번째
				n -= arr.get(k-1) + k+3;
				k--;
			}
		}
	}

}