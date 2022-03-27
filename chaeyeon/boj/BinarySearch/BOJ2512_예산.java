package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2512_¿¹»ê {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int N = Integer.parseInt(br.readLine());
		
		int[] list = new int[N];
		int right = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right,  list[i]);
		}
		int total = Integer.parseInt(br.readLine());
		
		int left = 0;
		
		while(left <= right) {
			int mid = (left+right)/2;
			int sum = 0;
			for(int i=0; i < N; i++) {
				if(list[i] < mid)
					sum += list[i];
				else
					sum += mid;
			}
			if(sum > total)
				right = mid-1;
			else
				left = mid+1;
		}
		
		System.out.println(right);
	}

}
