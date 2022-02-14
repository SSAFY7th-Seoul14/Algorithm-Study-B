import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준1966번. 프린터 큐
public class BOJ1966_PrinterQueue {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		Queue<int[]> q = new LinkedList<>(); // 데이터
		LinkedList<Integer> list = new LinkedList<>(); // 우선순위 저장 리스트
		
		for (int i = 0; i < t; i++) {
			q.clear();
			list.clear();
			
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			for (int j = 0; j < n; j++) {
				int num = sc.nextInt();
				q.offer(new int[]{num, j}); // 우선순위, 순서
				list.add(num);
			}
			Collections.sort(list, Collections.reverseOrder());
			
			// 처리
			int count = 0;
			while(true) {
				int[] data = q.poll();
				// 뽑은 데이터 우선순위 = 가장 높은 우선순위면
				if (data[0] == list.get(0)) {
					list.poll();
					count++;
					if (data[1] == m) {
						break;
					}
				} else {
					// 아니면 다시 넣자.
					q.offer(data);					
				}
			}
			
			// 출력
			System.out.println(count);
		}
	}
}
