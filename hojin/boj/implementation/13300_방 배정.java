import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13300 {
	public static void main(String[] args) throws Exception {
		// 입력처리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		// counter
		int[][] counter = new int[6][2];
		// 0 1 1 1 | 0 1
		// 0 2 1 2 | 2 3
		// 0 3 1 3 | 4 5
		// 0 4 1 4 | 6 7
		// 0 5 1 5 | 8 9
		// 0 6 1 6 | 10 11

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken()) - 1;
			// 학년 1~6
			for (int j = 0; j < 6; j++) {
				// 성별 0, 1
				if (j == y) {
					counter[j][s]++;
				}
			}
		}
		br.close();
		int answer = 0;
		for (int[] row : counter) {
			for (int cnt : row) {
				answer += Math.ceil((double)cnt / k);
			}
		}
		System.out.println(answer);
	}

}