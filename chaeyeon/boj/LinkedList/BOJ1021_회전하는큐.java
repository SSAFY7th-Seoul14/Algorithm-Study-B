package queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//2022-02-10
//1�ð�
public class BOJ1021_ȸ���ϴ�ť {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		LinkedList<Integer> q = new LinkedList<>();
		
		//����Ʈ�� ó�� ���� ������ ��Ÿ�� �� �ְ� 1���� ����
		for(int i = 1; i <= N; i++) {
			q.addLast(i);
		}
		
		int count = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			int idx = q.indexOf(num);//�ش� ������ ���� ��� ��ġ�� �ֳ�
			if(idx <= q.size()/2) {//�ε����� ����Ʈ�� ������ġ�� ���Ͽ� ������ �������� �̵�
				for(int j = 0; j < idx; j++) {
					q.add(q.remove());
					count++;
				}
				q.poll();
			}
			else {
				for(int j = idx; j < q.size(); j++) {
					q.addFirst(q.removeLast());
					count++;
				}
				q.poll();
			}
			
		}
		System.out.println(count);


	}

}
