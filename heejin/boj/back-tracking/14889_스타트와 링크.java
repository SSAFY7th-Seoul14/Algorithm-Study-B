package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


// BOJ / 스타트와 링크 / S2 / 15분
// https://www.acmicpc.net/problem/14889
public class Main_14889 {
	
	static int N;
	static int[][] S; // i와 j 같은 팀 되면 나타나는 효과
	static boolean[] isSelected; // 조합에서 선택되었는지 아닌지 여부
	
	static int[] start, link; // 스타트팀과 링크팀
	static int diff;  //능력 차이
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		start = new int[N/2];
		link = new int[N/2];
		isSelected = new boolean[N];
		
		diff = Integer.MAX_VALUE;
		
		//N을 N/2로 나누기
		comb(0,0);
		System.out.println(diff);
	}

	private static void comb(int idx, int starts) {
		if(idx==N/2) {
			solve();
			
			return;
		}
		for(int i=starts;i<N;i++) {
			isSelected[i]=true;
			comb(idx+1,i+1);
			isSelected[i]=false;
		}
		
	}

	private static void solve() {
		int sumStart=0, sumLink=0;
		List<Integer> start = new ArrayList<>();
		List<Integer> link = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			if(isSelected[i])
				start.add(i);
			else
				link.add(i);
		}
		
		for(int i=0;i<N/2;i++) {
			for(int j=i+1;j<N/2;j++) {
				sumStart += S[start.get(i)][start.get(j)];
				sumStart += S[start.get(j)][start.get(i)];
				sumLink += S[link.get(i)][link.get(j)];
				sumLink += S[link.get(j)][link.get(i)];
			}
		}
		diff = Math.min(diff, Math.abs(sumStart-sumLink));
		
	}

}
