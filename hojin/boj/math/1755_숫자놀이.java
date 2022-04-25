import java.util.*;

public class BOJ1755_숫자놀이 {
	static class Pairs {
		int num;
		String numStr;

		public Pairs(int num, String numStr) {
			super();
			this.num = num;
			this.numStr = numStr;
		}

	}

	// 0부터 9까지 String을 미리 입력해두기
	static String[] zTon = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();
		// 정렬을 대신 수행해주기 위해 Priority Queue 이용
		PriorityQueue<Pairs> pq = new PriorityQueue<Pairs>((a, b) -> {
			// Pairs의 field 값 중 묶인 String에 대해서 오름차순으로 정렬
			return a.numStr.compareTo(b.numStr);
		});
		// m 이상 n 이하에 대해서
		for (int i = m; i <= n; ++i) {
			// 숫자를 String으로 변환 후
			String tmp = (i / 10 == 0 ? "" : zTon[i / 10] + " ") + zTon[i % 10];
			// pq에 담아주기
			pq.add(new Pairs(i, tmp));
		}
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			// 10개씩 입력후
			for (int i = 0; i < 10 && !pq.isEmpty(); ++i) {
				sb.append(pq.poll().num).append(' ');
			}
			// 개행
			sb.append('\n');
		}
		// 출력
		System.out.println(sb);
		sc.close();
	}

}
