package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//BOJ / ��ȭ���� ��  / S5 / 15��
//https://www.acmicpc.net/problem/1436
public class Main_1436 {
	
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int cnt=0; // 666 ���� Ƚ��
		int num=666; //���� ����
		while(true) {
			String numStr = Integer.toString(num);
			for(int i=0;i<numStr.length()-2;i++) {
				int first = numStr.charAt(i)-'0';
				int second = numStr.charAt(i+1)-'0';
				int third = numStr.charAt(i+2)-'0';
				if(first==6 && second==6 && third==6) { // ���� 3�ڸ��� 6�� ���
					cnt++;
					break;
				}
			}
			
			if(cnt==N) { //N��° ������ ���
				sb.append(num);
				break;
			}
			num++;
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
