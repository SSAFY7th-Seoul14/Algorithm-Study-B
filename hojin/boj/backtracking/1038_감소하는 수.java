import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ1038_감소하는수 {
	static boolean[] visited = new boolean[10];
	static PriorityQueue<Long> pq;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int targetNo = sc.nextInt();
		sc.close();
		pq = new PriorityQueue<>();
		for (int i = 1; i <= 10; i++) {
			makeDecreasingNo(0, i, new int[i]);
		}
		int pqLen = pq.size();
		if (targetNo >= pqLen) {
			System.out.println(-1);
		} else {
			for (int i = 0; i < targetNo; i++) {
				pq.poll();
			}
			System.out.println(pq.poll());
		}
	}

	private static void makeDecreasingNo(int cnt, int len, int[] numbers) {
		if (cnt == len) {
			long curNo = 0;
			for (int i = 0; i < len; i++) {
				curNo += numbers[i] * Math.pow(10, i);
			}
			pq.add(curNo);
			return;
		}
		for (int i = 0; i < 10; i++) {
			numbers[cnt] = i;
			// 이게 중요한 prunning 조건. 이전 자리 수보다 현재 넣은 자리 수가 클 경우만 넘겨준다.
			if (cnt > 0 && numbers[cnt - 1] >= numbers[cnt]) {
				continue;
			}
			makeDecreasingNo(cnt + 1, len, numbers);
		}
	}

}