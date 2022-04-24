import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1946번. 신입 사원
public class BOJ1946_NewRecruits {
	static class Pair implements Comparable<Pair>{
		int score1, score2;

		public Pair(int score1, int score2) {
			this.score1 = score1;
			this.score2 = score2;
		}

		@Override
		public int compareTo(Pair o) {
			return this.score1 - o.score1;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < tc; t++) {
			int n = Integer.parseInt(br.readLine());
			Pair[] scores = new Pair[n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int s1 = Integer.parseInt(st.nextToken());
				int s2 = Integer.parseInt(st.nextToken());
				scores[i] = new Pair(s1, s2);
			}
			Arrays.sort(scores);			

			// 정렬 후 서류심사 등수 기준으로 정렬 후 순회하면
			// 항상 이전 서류등수보다는 낮으므로 이전 면접 등수보다는 등수가 높아야함.
			// 면접 등수가 높은 경우만 카운트하고 갱신
			int result = 0;
			int max = n+1;
			for (Pair p : scores) {
				if (p.score2 < max) {
					max = p.score2;
					result++;
				}
			}
			
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
}
