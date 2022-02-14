package queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13335_Ʈ�� {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Queue<Integer> drive = new LinkedList<>(); // �ٸ����� Ʈ��
		Queue<Integer> wait = new LinkedList<>(); // ���Ʈ��
		
		for(int i = 0; i < N; i++) {
			wait.add(Integer.parseInt(st.nextToken()));
		}
	
		for(int i = 0; i < W; i++) {
			drive.add(0); // �ٸ����̸�ŭ 0���� ä���� �ٸ����̸� �����ϴ� ť�迭�� ���� ����
		}
		
		int sum = 0;//�ٸ��� ������
		int count = 0;// �ð���
		
		
		while(!wait.isEmpty()) {// ���Ʈ���� ���� �� ����
			int truck = wait.peek();
			sum -= drive.poll(); // �ϴ� �� ���� Ʈ���� ��ĭ �����δ�.
			count++;
			if(sum + truck > L) { //�ٸ��� Ʈ�� ���Կ� �� Ʈ�������� ���� �ִ빫���� ���� ��
				drive.add(0); //���ο� Ʈ���� ������� �ʰ� 0�� �־��ش�.
			}
			else {//���ο� Ʈ���� �� �� ���� ��
				drive.add(wait.poll()); // ��⿭���� �̾� �ٸ��� Ʈ���迭�� �־��ش�.
				sum += truck;// �� Ʈ���� ���Ը� �����ش�.
			}
		}
		
		System.out.println(count+W);
		
	}

}
