package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// BOJ / 회의실 배정 / S2 / 8분
// https://www.acmicpc.net/problem/1931
public class Main_1931 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		List<int[]> list = new ArrayList<int[]>();
		
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list.add(new int[] {start, end});
		}
		
		// list를 end 순으로 정렬, end 같으면 start 순으로 정렬
		Collections.sort(list, new Comparator<int[]>(){

			@Override
			public int compare(int[] o1, int[] o2) {
				return (o1[1]-o2[1])!=0?o1[1]-o2[1]:o1[0]-o2[0];
			}
		});
		
		// greedy logic start
		int cnt=1; //회의실 수
		int end = list.get(0)[1];
		for(int i=1;i<N;i++) {
			int s = list.get(i)[0]; // start
			int e = list.get(i)[1]; // end
			if(s<end) {
				continue;
			}
			else {
				end = e;
				cnt++;
			}
		}
		System.out.println(cnt);
	}

}
