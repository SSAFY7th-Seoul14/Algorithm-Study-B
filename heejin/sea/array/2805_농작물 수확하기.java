package com.compare.comparator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//SWEA / 농작물 수확하기 / D3 / 30분
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GLXqKAWYDFAXB&categoryId=AV7GLXqKAWYDFAXB
public class SWEA_2805 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			int sum=0; //수확 총합
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			
			// N X N 농장 입력받기
			for(int i=0;i<N;i++) {
				String input = br.readLine();
				for(int j=0;j<N;j++)
					map[i][j] = input.charAt(j)-'0';
			}
			
			//SUM 구하기(위쪽 삼각형)
			int start = N/2;
			for(int i=0;i<N/2;i++) {
				for(int j=start;j<start + 2*i+1;j++) {
					
					sum += map[i][j];
				}
				start--;
			}

			// SUM 구하기 2-1 (아래쪽 삼각형)
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
