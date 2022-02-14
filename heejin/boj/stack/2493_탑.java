package com.ssafy.pcs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_test {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<int[]> stack = new Stack<int[]>();
		int[] input = new int[N]; // ž ���� ����
		
		StringTokenizer st= new StringTokenizer(br.readLine()," ");
		
		for(int i=0;i<N;i++) { // ž ���� �Է¹���
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<N;i++) {
			int curTop = input[i]; 
			
			while(!stack.isEmpty()) {
				if(stack.peek()[1] < curTop) { //stack�� top�� ���� ž ���̺��� ���� ���, ������ ��ȣ �� ��ġ�Ƿ� ���ÿ��� ����
					stack.pop();
				}
				else { // stack�� top�� ���� ž ���̺��� ������ ���
					System.out.print(stack.peek()[0]+" "); 
					break;
				}
					
					
			}
			if(stack.isEmpty()) //���� ��ȣ�� ������ ž�� ��� ������ ���� pop�Ǿ��� ���
				System.out.print(0+" ");
			stack.push(new int[] {i+1,input[i]});	//�о�� �Է°�(���� ž) ���ÿ� push
				
		}
		
	}

}
