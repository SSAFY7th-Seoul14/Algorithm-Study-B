package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

//BOJ / ���� ���� / S3 / 20��
// https://www.acmicpc.net/problem/1874
public class Main_1874 {

	public static void main(String[] args) throws Exception{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		int cur = 1; // ���ÿ� push�� ���� ��
		boolean flag = true; //������ ���� �� �ִ��� ����
		
		out: for(int i=0;i<N;i++) {
			int input = Integer.parseInt(br.readLine());
			while(true) {
				if(stack.isEmpty()) { // ������ ������� ��� 1 ����
					stack.push(cur);
					cur++;
					sb.append("+\n");
				}
				else if(stack.peek()<input) { 
					stack.push(cur);
					sb.append("+\n");
					cur++;
					
				}
				else if(stack.peek()==input) {
					stack.pop();
					sb.append("-\n");
					break;
				}	
				else {				//�������� ����X�̹Ƿ� NO ���
					System.out.println("NO");
					flag = false;
					break out;
				}
			}
		}
		if(flag)
			bw.write(sb.toString());
			
		bw.close();
		br.close();
	}
}
