package com.ssafy.datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// SWEA 1225 ��ȣ ������
public class SWEA_1225 {
	
	static int N = 8;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = new LinkedList<Integer>();;
		int t; //tc Ƚ��
		int num;
		
		String input= null;
		while((input = br.readLine()) != null) {
			q.clear();  //tc���� �ʱ�ȭ
			
			t=Integer.parseInt(input);			
			// ���� �Է�
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int i=0;i<N;i++) {	
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			boolean isDone = false; //�۾� �Ϸ� ����
			while(!isDone) {
				for(int i=1;i<=5;i++) {
					num = q.poll()-i;
					if(num<=0) {
						q.offer(0);
						isDone = true;
						break;
					}
					else {
						q.offer(num);
					}
				}
				
			}
			//���
			sb.append("#" + t + " ");
            while(!q.isEmpty())
                sb.append(q.poll() + " ");
            sb.append('\n');
		}
		System.out.print(sb);
		
	}

}
