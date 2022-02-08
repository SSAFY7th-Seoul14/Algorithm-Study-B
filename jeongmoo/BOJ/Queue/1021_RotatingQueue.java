import java.util.LinkedList;
import java.util.Scanner;

// 백준1021번. 회전하는 큐.
public class BOJ1021_RotatingQueue {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		LinkedList<Integer> q = new LinkedList<>();	// 순서 데이터
		for (int i = 1; i <= n; i++) { 				// 최초값
			q.offer(i);
		}
		
		int count = 0;
		for (int i = 1; i <= m; i++) {
			int find = sc.nextInt(); // 찾아야 할 값
			
			// 해당 값이 몇번째인지 탐색
			int index = 0;
			for (int j = 0; j < q.size(); j++) {
				if (find == q.get(j)) {
					index = j;
					break;
				}
			}
			
			// index가 0이면 맨앞이므로 그냥 제거만 하면 됨
			if(index!=0) {
				// 아니면 앞, 뒤 중 어디가 더 가까운지 체크
				int back = q.size() - index;
				boolean isLeft = index<back;
				int min = isLeft? index: back;
				
				// 가까운만큼 작업한다.
				for (int j = 0; j < min; j++) {
					if (isLeft) {
						int num = q.poll();
						q.offer(num);
						count++;
					} else {
						int num = q.pollLast();
						q.offerFirst(num);
						count++;
					}
				}				
			}
			q.remove(0);
		}
		
		System.out.println(count);
	}
}
