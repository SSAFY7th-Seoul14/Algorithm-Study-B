package com;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long n = Long.parseLong(st.nextToken());
		long k = Long.parseLong(st.nextToken());
		
		long l = 0;
		long r = n/2 + 1;
		while(l<r) {
			long mid = (l+r) / 2;
			if(k == (mid+1)*(n+1 - mid)){
				System.out.println("YES");
				return;
			}
			if(k < (mid+1)*(n+1 - mid)) r = mid;
			else l = mid + 1;
		}
		System.out.println("NO");
	}

}
