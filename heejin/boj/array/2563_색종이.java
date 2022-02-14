package com.ssafy.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//BOJ ������ / B1 / 25��
//https://www.acmicpc.net/problem/2563
public class Main_2563 {
	
	static int MAX = 100;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int area=0;
		
		int N = Integer.parseInt(br.readLine()); //������ ����
		
		int[][] map = new int[MAX][MAX]; //100x100 ��ȭ��
		
		
		for(int t=0;t<N;t++) {
			st = new StringTokenizer(br.readLine());
			int x= Integer.parseInt(st.nextToken()); 
			int y = Integer.parseInt(st.nextToken());
		
			for(int i=0;i<10;i++) { //���簢�� ���ʾƷ� ������ 10x10 �ڸ��� 1����
				for(int j=0;j<10;j++) {
					if(x+i<=100 && y+j<=100)
						map[x+i][y+j]=1; //1: ������, 0: ���
				}
			}
		}
		
		//���� ���ϱ�
		for(int i=0;i<MAX;i++) {
			for(int j=0;j<MAX;j++) {
				if(map[i][j]==1)
					area++;
				
			}
		}
		sb.append(area);
		bw.write(sb.toString()); //���
		bw.flush();
		bw.close();
		br.close();
		
	}
	

}
