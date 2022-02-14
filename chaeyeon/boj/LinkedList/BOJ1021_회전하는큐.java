package queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//2022-02-10
//1시간
public class BOJ1021_회전하는큐 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		LinkedList<Integer> q = new LinkedList<>();
		
		//리스트에 처음 원소 순서를 나타낼 수 있게 1부터 저장
		for(int i = 1; i <= N; i++) {
			q.addLast(i);
		}
		
		int count = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			int idx = q.indexOf(num);//해당 순서가 지금 어느 위치에 있나
			if(idx <= q.size()/2) {//인덱스가 리스트의 절반위치의 이하에 있으면 왼쪽으로 이동
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
