package com.ssafy.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//[BOJ] �似Ǫ�� ���� / S5 / 12��
//https://www.acmicpc.net/problem/1158
public class Main_1158 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Integer> q = new LinkedList<>();
		sb.append("<");
		
		//N, K �Է�
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//���� 1~N���� Queue�� �Է�
		for(int i=1;i<=N;i++) {
			q.offer(i);
		}
	
		//���� ť�� �־ k�� �ݺ���Ű��
		while(!q.isEmpty()) {
			for(int i=1;i<=K;i++) {
				if(i==K && !q.isEmpty()) { //K��° �����̸� ����
					sb.append(q.poll()+", ");
				}
				else { //K��° ���� �ƴϸ� Queue �� �ڷ� �̵�
					if(!q.isEmpty())
						q.offer(q.poll());
				}
			}
		}
		sb.setLength(sb.length()-2);
		sb.append(">");
		bw.write(sb.toString());
		bw.close();
		br.close();
		
	}
}
