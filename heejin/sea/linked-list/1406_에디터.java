package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

//[BOJ] ������ / s3 / 38�� / https://www.acmicpc.net/problem/1406
//1��(��ũ�帮��Ʈ) - �ð��ʰ�
public class Main_1406_2 {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>(); //Ŀ�� ���� ���� ����
		Stack<Character> stack2 = new Stack<>(); //Ŀ�� ������ ���� ����
		
		String input = br.readLine();
		char[] arr=  input.toCharArray();
		for(int i=0;i<input.length();i++) {
			stack.push(arr[i]);
		}

		int M = Integer.parseInt(br.readLine());
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			if(cmd.equals("P")) {
				char ch = st.nextToken().charAt(0);
				stack.push(ch); //Ŀ�� ���ʿ� ����
			}
			else if(cmd.equals("L")) {
				if(!stack.isEmpty())
					stack2.push(stack.pop()); //Ŀ�� �������� �ű�Ƿ�, ���� ������ top ���� stack2(������)�� push

			}
			else if(cmd.equals("D")) {
				if(!stack2.isEmpty())
					stack.push(stack2.pop()); //Ŀ�� ���������� �ű�Ƿ�, ������ ������ top ���� stack(����)�� push
			}
			else if(cmd.equals("B")) {
				if(!stack.isEmpty()) 
					stack.pop(); //Ŀ�� ���� ����
			}
		}
		while(!stack.isEmpty())
			sb.append(stack.pop());
		
		StringBuilder sb2 = new StringBuilder();
		while(!stack2.isEmpty())
			sb2.append(stack2.pop());
		
		bw.write(sb.reverse().toString()+sb2); //Ŀ�� ���� ���� ��� stack�� �������� ����Ǿ� �ֱ⿡ reverse() ó������.
		bw.close();
		br.close();
		
		
		
	}

}
