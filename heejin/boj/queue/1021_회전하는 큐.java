package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//BOJ / ȸ���ϴ� ť / S4 / 
// https://www.acmicpc.net/problem/1021
public class Main_1021 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Integer> dq = new ArrayList<>();
		int cnt=0;//�� ���� Ƚ��
		
		int N = Integer.parseInt(st.nextToken()); // ť ũ��
		int M = Integer.parseInt(st.nextToken()); // �̴� �� ����
		
		for(int i=1;i<=N;i++) //N���� ���� ���� �� ����
			dq.add(i);
		
		int popIdx[] = new int[M]; //�̾ƾ� �ϴ� ���ҵ�
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++)
			popIdx[i] = Integer.parseInt(st.nextToken());
		
		
		for(int i=0;i<M;i++) {
				if(dq.get(0)==popIdx[i]) { //1������
					dq.remove(0);
				}
				else {
					int idx = dq.indexOf(popIdx[i]);
					if(idx<=dq.size()/2) {
						//����
						while(!(dq.get(0)==popIdx[i])) {
							int front = dq.get(0);
							
							dq.add(front);
							dq.remove(0);
							cnt++;
						}
						dq.remove(0);
					}
					else {
						//������
						while(!(dq.get(0)==popIdx[i])) {
							int rear = dq.get(dq.size()-1);
							
							dq.add(0,rear);
							dq.remove(dq.size()-1);
							cnt++;
						}
						dq.remove(0);
					}
				}
		}
		System.out.println(cnt);
		br.close();
		
	}

}
