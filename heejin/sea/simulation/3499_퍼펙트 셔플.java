package com.ssafy.pcs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;
import java.util.StringTokenizer;

//SWEA ����Ʈ ���� D3 - 22��
public class Solution_3499 {
	static List<String> cards = new ArrayList<String>(); //input
	static List<String> first = new ArrayList<String>();
	static List<String> second = new ArrayList<String>();
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int t=1;t<=T;t++) {
			//�ʱ�ȭ
			cards.clear(); 
			first.clear();
			second.clear();
			
			//N, ī�� �Է�
			N= Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine()," ");
			for(int i=0;i<N;i++) {
				cards.add(st.nextToken());
			}
			
			//ó��  
			int mid = (int) Math.ceil(N/2.0); //¦���� N/2, Ȧ���� N/2+1
			
			for(int i=0;i<mid;i++) {
				first.add(cards.get(i));
			}
			for(int i=mid;i<N;i++) {
				second.add(cards.get(i));
			}

			//���
			sb.append("#"+t+" ");
			
			for(int i=0;i<N/2;i++) {
				sb.append(first.get(i)+" ");
				sb.append(second.get(i)+" ");
			}
			if(N%2==1)
				sb.append(first.get(N/2));	
			sb.append('\n');
		}
		System.out.print(sb);
	
	}

}
