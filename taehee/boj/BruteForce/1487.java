package com;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][2];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[i][0] = a;
			arr[i][1] = b;
		}
		int maxProfit = 0; //최대 이익
		int maxPrice = 0; //최대이익일때 가격
		for (int i=0; i<n; i++) {
			int profit = 0;
			int sell = arr[i][0]; //파는 가격
			
			for(int j=0; j<n; j++) {
				if(arr[j][0] >= sell && sell > arr[j][1]) { //살려는 가격이 파는가격 보다 크고 배달해도 남을때
					profit += sell - arr[j][1];
				}
			}
			if(profit > maxProfit) {
				maxPrice = sell;
				maxProfit = profit;
			}else if(profit == maxProfit) {
				maxPrice = Math.min(maxPrice, sell);
			}
		}
		System.out.println(maxPrice);
	}

}
