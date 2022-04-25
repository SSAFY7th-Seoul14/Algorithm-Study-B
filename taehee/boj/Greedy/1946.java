package com;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st;
			int[] arr = new int[n+1];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				arr[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
			}
			int cnt = 0;
			int max = arr[1];
			for (int i = 2; i < n+1; i++) {
				if(max < arr[i]) cnt++; // 둘다커서 안되는애들
				else max = arr[i]; //그다음 작은값
			}
			System.out.println(n-cnt);
		}

	}

}