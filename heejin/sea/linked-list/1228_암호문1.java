package com.ssafy.datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

//SWEA 1228 ��ȣ��1 [D3]
public class SWEA_1228 {
	
	static int N, M;
	static String[] codes;
	static List<Integer> list = new LinkedList<Integer>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int t=1;t<=10;t++) { //10�� �ݺ�
			list.clear(); //TC ���� �ʱ�ȭ
			
			//���� ��ȣ�� �Է�
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine()," ");
			for(int i=0;i<N;i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			//��ɾ� �Է�
			M = Integer.parseInt(br.readLine());
			codes = new String[M];
			st = new StringTokenizer(br.readLine()," ");
			//�۾� ����
			int x, y;
			for(int i=0;i<M;i++) {
				if(st.nextToken().equals("I")) {					
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					for(int j=0;j<y;j++) {
						list.add(x+j,Integer.parseInt(st.nextToken()));
					}
				}
			}
			//���
			sb.append("#"+t+" ");
			for(int i=0;i<10;i++) {
				sb.append(list.get(i)+" ");
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}

}
