package com.ssafy.datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SWEA 9229 한빈이와 Spot Mart [D3] - 11분
public class SWEA_9229 {
	static int N,M;
	static int[] weights;
	static int max,sum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int res;
		for(int t=1;t<=T;t++) {
			res=0;
			max=Integer.MIN_VALUE;
			
			st = new StringTokenizer(br.readLine()," ");
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			weights = new int[N];
			//입력받기
			st =new StringTokenizer(br.readLine()," ");
			for(int i=0;i<N;i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}
			
			//2개 고르기
			for(int i=0;i<N;i++) {
				for(int j=i+1;j<N;j++) {
					sum = weights[i]+weights[j];
					if(sum <=M && max<sum)
						max = sum;
				}
			}
			if(max<0)
				res=-1;
			else
				res=max;
			System.out.println("#"+t+" "+res);
		}
	}
}
