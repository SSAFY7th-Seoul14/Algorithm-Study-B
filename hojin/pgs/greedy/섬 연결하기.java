import java.util.Arrays;

class Solution {
    public int solution(int n, int[][] costs) {
		int answer = 0;
		Arrays.sort(costs, (a, b) -> {
			return a[2] - b[2];
		});
		makeSet(n);

		int cnt = 0;

		for (int[] row : costs) {
			if (union(row[0], row[1])) {
				answer += row[2];
				if (++cnt == n - 1)
					break;
			}
		}
		return answer;
	}

	static int[] parents;

	private static void makeSet(int n) {
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
	}

	private static int find(int i) {
		if (parents[i] == i)
			return i;

		return parents[i] = find(parents[i]);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}
}