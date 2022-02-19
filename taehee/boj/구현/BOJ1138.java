package com;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ1138 {
	
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] nums = new int[n];
		ArrayList<Integer> arr = new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			nums[i] = sc.nextInt();
		}
		
		for(int i=n-1;i>=0;i--) {
			arr.add(nums[i], i+1);
		}
		
		for(int i : arr) {
			System.out.print(i + " ");
		}
	}
	
}
