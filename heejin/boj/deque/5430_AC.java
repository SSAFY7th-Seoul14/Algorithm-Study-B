package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// AC / ��� 5 / 90��
// https://www.acmicpc.net/problem/5430
//1�� : Collections.sort �ð��ʰ�/ ��� ������ ���� �ɸ�..
public class Main_5430 {

	public static void main(String[] args) throws Exception{
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		 out: for(int t=1;t<=T;t++) {
			//��ɾ� �Է¹ޱ�
			String cmd = br.readLine();
			Deque<Integer> list = new ArrayDeque<>();
			int len = Integer.parseInt(br.readLine());
			String input = br.readLine();

			st = new StringTokenizer(input,",[]"); //,[]�� token
			
			while(st.hasMoreTokens()) { // ���� �Է¹ޱ�
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			boolean dir = true; //true: ����, false: ����
			// cmd ����
			inner: for(int i=0;i<cmd.length();i++) {
				if(cmd.charAt(i)=='R') { 
					dir= !dir;
				}
				else if(cmd.charAt(i)=='D') {
					if(!list.isEmpty()) {
						if(dir) {
							list.removeFirst(); //�տ������� ����
						}
						else {
							list.removeLast();	//�ڿ������� ����
						}
					}
					else {
						sb.append("error\n");
						continue out;
					}
				}
			}
			//���
			sb.append("[");
			if(dir) {
				while(list.size() > 1) {
					sb.append(list.removeFirst()).append(",");
				}
			}
			else {
				while(list.size() > 1) {
					sb.append(list.removeLast()).append(",");
				}
			}
			if(!list.isEmpty()) sb.append(list.remove());
			sb.append("]\n");
			
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}
