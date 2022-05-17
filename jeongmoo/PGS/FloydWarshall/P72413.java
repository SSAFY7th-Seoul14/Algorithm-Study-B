// 프로그래머스 2021 KAKAO BLIND RECRUITMENT - 합승 택시 요금
public class P72413 {
	public static void main(String[] args) throws Exception {
		// 입력
		int[][] fares = { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 }, { 5, 1, 24 }, { 4, 6, 50 },
				{ 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } };

		int result = solution(6, 4, 6, 2, fares);
		System.out.println(result);
	}

	public static int solution(int n, int s, int a, int b, int[][] fares) {
		int[][] adj = new int[n + 1][n + 1];
		final int INF = 20000000;
		
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (i == j)
					adj[i][j] = 0;
				else
					adj[i][j] = INF;
			}
		}

		for (int i = 0; i < fares.length; i++) {
			int[] fare = fares[i];
			int from = fare[0];
			int to = fare[1];
			int cost = fare[2];
			adj[from][to] = adj[to][from] = cost;
		}
		
		// 플로이드와셜
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (adj[i][j] > adj[i][k] + adj[k][j]) {
						adj[i][j] = adj[i][k] + adj[k][j];
					}
				}
			}
		}
		
		int answer = INF;
		for (int i = 1; i <= n; i++) {
			answer = Math.min(answer, adj[s][i] + adj[i][a] + adj[i][b]);
		}

		return answer;
	}
}
