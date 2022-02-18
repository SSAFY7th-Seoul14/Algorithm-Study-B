package com.hw;

import java.util.Arrays;
import java.util.Scanner;

public class sea9229 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
			}
			Arrays.sort(arr);
			int front = 0;
			int rear = n-1;
			int max = 0;
			
			while(front < rear) {
				int sum = arr[front] + arr[rear];
				
                if(sum <= m) {
                    if (max < sum) {
                       max = sum;
                    }
                     front++;
                 }else {
                     rear--;
                     front = 0;
                 }

			}
			System.out.println("#"+t+" "+((max)!=0 ? max : -1));
		}
	}

}
