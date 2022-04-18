import java.io.*;
import java.util.*;

public class BOJ21278_호석이두마리치킨 {
	static int[][] adjMat;
	static int MAX_VALUE = 10000;
	static int min = MAX_VALUE, n;
	static int[] minArr = new int[2];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		adjMat = new int[n + 1][n + 1];
		for (int i = 1; i <= n; ++i)
			Arrays.fill(adjMat[i], MAX_VALUE);
		for (int i = 1; i <= n; ++i)
			adjMat[i][i] = 0;
		int m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			adjMat[A][B] = 1;
			adjMat[B][A] = 1;
		}
		for (int k = 1; k <= n; ++k) {
			for (int j = 1; j <= n; ++j) {
				for (int i = 1; i <= n; ++i) {
					adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
					adjMat[j][i] = adjMat[i][j];
				}
			}
		}
		combi(0, 1);
		System.out.printf("%d %d %d", minArr[0], minArr[1], min);
		br.close();
	}

	static int[] selected = new int[2];

	private static void combi(int cnt, int start) {
		if (cnt == 2) {
			checkMin();
			return;
		}
		for (int i = start; i <= n; ++i) {
			selected[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}

	static boolean[] visited;

	private static void checkMin() {
		int sum = 0;
		for (int i = 1; i <= n; ++i) {
			sum += 2 * Math.min(adjMat[selected[0]][i], adjMat[selected[1]][i]);
		}
		if (sum < min) {
			minArr[0] = selected[0];
			minArr[1] = selected[1];
			min = sum;
		}
	}

}
