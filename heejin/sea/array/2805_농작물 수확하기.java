package com.compare.comparator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//SWEA / ���۹� ��Ȯ�ϱ� / D3 / 30��
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GLXqKAWYDFAXB&categoryId=AV7GLXqKAWYDFAXB
public class SWEA_2805 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			int sum=0; //��Ȯ ����
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			
			// N X N ���� �Է¹ޱ�
			for(int i=0;i<N;i++) {
				String input = br.readLine();
				for(int j=0;j<N;j++)
					map[i][j] = input.charAt(j)-'0';
			}
			
			//SUM ���ϱ�(���� �ﰢ��)
			int start = N/2;
			for(int i=0;i<N/2;i++) {
				for(int j=start;j<start + 2*i+1;j++) {
					
					sum += map[i][j];
				}
				start--;
			}

			// SUM ���ϱ� 2-1 (�Ʒ��� �ﰢ��)
			int d = 0;
			for(int i=N/2;i<N;i++) {
				for(int j=d;j<N-d;j++)
					sum += map[i][j];
				d++;
			}
			sb.append("#"+t+" "+sum);
			sb.append('\n');
		}
		bw.write(sb.toString());
		bw.close();
		br.close();	
	}
}
