package com.ssafy.pcs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_test {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Stack<int[]> stack = new Stack<int[]>();
		int[] input = new int[N]; // ž ���� ����
		
		StringTokenizer st= new StringTokenizer(br.readLine()," ");
		
		for(int i=0;i<N;i++) { // ž ���� �Է¹���
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<N;i++) { // ž ����ŭ ��
			int curTop = input[i]; 
			
			//1. ���� ž�� �ִ� ���: ���ÿ� ���� ����. 
			while(!stack.isEmpty()) {
				if(stack.peek()[1] < curTop) { //stack�� top�� ���� ž ���̺��� ���� ���, ������ ��ȣ �� ��ġ�Ƿ� ���ÿ��� ����
					stack.pop(); // ������ �����ε� ��ȣ�� �� ����
				}
				else { // stack�� top�� ���� ž ���̺��� ������ ���
					sb.append(stack.peek()[0]+" "); 
					break;
				}			
			}
			//2. ���� ž�� ���� ���
			if(stack.isEmpty()) //���� ��ȣ�� ������ ž�� ��� ������ ���� pop�Ǿ��� ���
				sb.append(0+" ");
			//3. ���� ž�� �ְų� ���ų� ��� ���� ž ������ ���ÿ� �����س��� ��
			stack.push(new int[] {i+1,input[i]});	//�о�� �Է°�(���� ž) ���ÿ� push
		}
		//���
		bw.write(sb.toString());
		br.close();
		bw.close();
		
	}

}
