package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// BOJ / 암호 만들기 / G5 / 40분
//https://www.acmicpc.net/problem/1759
public class Main_1759 {

	static int L,C;
	static List<Character> list;
	static boolean[] visited;
	static boolean flag1; //모음 ,자음 여부 
	static int flag2; //자음 개수
	static char[] code;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken()); //암호 길이
		C = Integer.parseInt(st.nextToken());
		list = new ArrayList<Character>(C);
		visited = new boolean[C+1]; //알파벳 방문 여부
		code = new char[L]; //가능한 암호(정답)
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<C;i++) {
			list.add(st.nextToken().charAt(0));
		}
		
		Collections.sort(list);
		dfs(0,0);
	}
	private static void dfs(int x, int idx) {
		if(idx==L) {
			boolean flag1=false; //모음
			int flag2=0; //자음 2개 이상
			for(int i=0;i<L;i++) {
				if(code[i]=='a' || code[i]=='e' || code[i]=='i' || code[i]=='o'|| code[i]=='u')
					flag1=true;
				else
					flag2++;
			}
			if(flag1 && flag2>=2) //자음 2개 이상, 모음 1개 이상일 경우
				System.out.println(code);
			return;
		}
		
		for(int i=x;i<C;i++) { //C개에서 L개 뽑는 조합 진행
			if(!visited[i]) {
				code[idx]=list.get(i);
				visited[i]=true;
				dfs(i,idx+1);
				visited[i]=false;
			}
		}
	}
}
