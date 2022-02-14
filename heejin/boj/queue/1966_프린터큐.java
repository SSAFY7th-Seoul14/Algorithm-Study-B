package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ 1966 ������ť Silver3
public class BOJ_1966 {
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		Queue<int[]> datas = new LinkedList<int[]>();
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); //�߿䵵 ����(��������)
		StringTokenizer st;
		int num, curMax, cnt; //num: �Է� ��, curMax: queue ���� �ְ�, cnt: �� Ƚ��
		int [] cur; //cur: queue �� ��
		
		for(int t=1;t<=T;t++) {
			cnt=0; //TC���� �ʱ�ȭ
			pq.clear();
			datas.clear();
			
			st = new StringTokenizer(br.readLine()," ");
			N= Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			//���� �Է¹ޱ�
			st = new StringTokenizer(br.readLine()," ");
			for(int i=0;i<N;i++) {
				num = Integer.parseInt(st.nextToken());
				datas.offer(new int[] {i,num});	
				pq.add(num);
			}
			curMax = pq.poll(); //���� �ְ�
			
			//ó��
			while(true) {
				if(!datas.isEmpty()) {
					cur=datas.poll();
					
					if(cur[1]==curMax) { //���� ť ���� �� �� ���� �ִ밪�� ���
						cnt++; //���� ��� �۾� ����
						if(cur[0]==M) //���� �ִ밪�� ����  M��° ������ ���
							break;
						else {
							curMax = pq.poll(); //M��° ���� �ƴ� ���, �ִ밪 ����
						}
					}
					else
						datas.offer(cur); //ť �ȿ� �ٸ� �ִ� ���� ������, ���� �� ť �� �ڷ� �̵�
				}
			}
			System.out.println(cnt);
		}
			
	}
}
