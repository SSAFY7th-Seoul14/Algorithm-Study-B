import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JOL1681 {

	static int[][] costs;
	static int n, min;
	static boolean[] selected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());

		costs = new int[n][n];
		selected = new boolean[n];

		StringTokenizer st = null;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				costs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		// 0에서 출발
		int start = 0;
		min = Integer.MAX_VALUE;
		BT(start, 1, 0, 0);
		// 1 <= 부터는 0으로 못감
		// 모두 돌았을 때 0으로
		System.out.println(min == Integer.MAX_VALUE ? 0 : min);
	}

	private static void BT(int start, int cnt, int sum, int flag) {
		if (sum >= min)
			return;

		if (cnt == n) {
			if (costs[start][0] == 0)
				return;
			// 범위 다 돌았으면
			sum += costs[start][0];
			if (min > sum)
				min = sum;
			return;
		}
		for (int i = 1; i < n; i++) {
			if ((flag & 1 << i) != 0 || costs[start][i] == 0)
				continue;
			BT(i, cnt + 1, sum + costs[start][i], flag | 1 << i);
		}
	}

}