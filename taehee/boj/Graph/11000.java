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
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			arr[i][0] = s;
			arr[i][1] = e;
		}
		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) return o1[1] - o2[1];
				return o1[0] - o2[0];
			}
			
		});
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(arr[0][1]); //끝나는시간 넣기,,가장빨리 끝나는 시간이 맨앞에 있다
		for (int i = 1; i < n; i++) {
			if(arr[i][0] >= pq.peek()) pq.poll(); //끝난 강의 빼기
			pq.offer(arr[i][1]); //지금강의 넣어주기
		}
		System.out.println(pq.size());
	}

}
