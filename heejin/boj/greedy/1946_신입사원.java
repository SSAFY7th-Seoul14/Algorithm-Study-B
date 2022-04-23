import java.io.*;
import java.util.*;

public class Main_1946 {
	static class Jiwonja implements Comparable<Jiwonja> {
		int rankA; // 서류 점수 순위
		int rankB; // 면접 점수 순위

		public Jiwonja(int rankA, int rankB) {
			this.rankA = rankA;
			this.rankB = rankB;
		}

		@Override
		public int compareTo(Jiwonja o) { // 서류 순위 오름차순
			return this.rankA - o.rankA;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine()); // 지원자 수
			List<Jiwonja> list = new ArrayList<>(); // 지원자 리스트
			int res = 0; // 선발할 수 있는 신입사원 수 (정답)

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list.add(new Jiwonja(a, b));
			}
			Collections.sort(list);

			int minA = Integer.MAX_VALUE; // 서류 제일 높은 순위
			int minB = Integer.MAX_VALUE; // 면접 제일 높은 순위

			for (int i = 0; i < N; i++) {
				Jiwonja cur = list.get(i);
				if (i == 0) { // 서류 제일 높은 지원자 기준으로 초기화
					minA = cur.rankA;
					minB = cur.rankB;
					res++;
				}
				if (cur.rankB < minB) { 
					minB = cur.rankB;
					res++;
				}
			}
			System.out.println(res);
		}
	}

}
