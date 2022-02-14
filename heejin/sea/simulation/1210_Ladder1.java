package com.ssafy.pcs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//[SWEA] Ladder1 / D4
public class Solution_1210 {

	static int N = 100;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int t=1;t<=10;t++) {
			int T = Integer.parseInt(br.readLine());
			map = new int[N][N]; //map ����
			int res= -1;
			
			//100x100 �Է¹ޱ�
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++)
					map[i][j]= Integer.parseInt(st.nextToken());
			}
			
			// X�� ��ġ ã��
			int x=N-1, y = 0;
			for(int i=0;i<N;i++) {
				if(map[N-1][i]==2) {
					y = i;
					break;
				}
			} 
			//X���� �Ųٷ� Ÿ�� �ö󰡱� - ��ٸ� Ÿ�� ����	
			while(true) {
				if(x==0) {  // 0�� �����ϸ� ��ٸ� Ÿ�� ���� �� ���� ��ٸ� �� �� ��ȯ
					res=y;
					break;
				}
					
				if(y-1 >=0 && map[x][y-1]==1) { //������ �ִ��� üũ
					while(x>=0 && x<N && y-1>=0 && y<N) { //���� ���°� ���� ������ �ݺ�  + ���� üũ
						y= y-1;						
						if(x-1>=0 && map[x-1][y]==1) { //���� ���� ���� �� ���� ���, �� ĭ ���� �����ְ� break
							x= x-1;
							break;
						}
					}
				}
				else if(y+1 <N && map[x][y+1]==1) { //������ �ִ��� üũ
					while(x>=0 && x<N && y>=0 && y<N-1) { //���� ���°� ���� ������ �ݺ� + ���� üũ
						y=y+1;
						if(x-1>=0 && map[x-1][y]==1) { //���� ���� ���� �� ���� ���, �� ĭ ���� �����ְ� break
							x=x-1;
							break;
						}
					}
				}
				else if(x-1>=0 && map[x-1][y]==1) { //���� �ִ��� üũ
					while(x-1>=0 && x<N && y>=0 && y<N) {
						x = x-1;
						if((y-1 >=0 && map[x][y-1]==1) || (y+1<N &&map[x][y+1]==1)) // ���� �����̳� ������ ��ٸ� ���� ��� break
							break;
					}	
				}
			}
			//���
			sb.append("#"+t+" "+res);
			sb.append('\n');
			
		}	
		bw.write(sb.toString());
		bw.close();
		br.close();
	}	
}

