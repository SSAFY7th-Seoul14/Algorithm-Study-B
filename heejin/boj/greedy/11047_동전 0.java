package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//BOJ / S3 / ���� 0 / 20��
//https://www.acmicpc.net/problem/11047
//1�� - �ð��ʰ�
public class Main_11047 {
	
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int [] moneys = new int[N];
		for(int i=0;i<N;i++) {
			moneys[i] = Integer.parseInt(br.readLine());
		}
		int total_cnt = 0; // �� ���� ����
		int last = N-1; // ���� ������ ȭ�� ����
		while(K!=0) {
			for(int i=last;i>=0;i--) { //ū ȭ�� -> ���� ȭ�� ������ Ž��
				if(K/moneys[i]!=0) { 
					int cnt = K/moneys[i]; //ȭ�� ��� ����
					K -= cnt*moneys[i]; // ȭ�� �� ����
					total_cnt +=cnt;
					last = i-1;  // �ְ� ���� ȭ�� ����(���� �ݺ��� �� �װͺ��� ū �͵��� Ž�� X)
					break;
				}
			}
			
		}
		sb.append(total_cnt);
		bw.write(sb.toString());
		bw.close();
		br.close();	
	}
}
