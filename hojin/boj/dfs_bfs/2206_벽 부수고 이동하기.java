// 80분
import java.io.*;
import java.util.*;

public class BOJ2206_벽부수고이동하기 {

	static int n, m, min = Integer.MAX_VALUE, ans;
	static char[][] map;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		ans = Integer.MAX_VALUE;
		// 1, 1부터 n, m로 가면서 최소 거리 구하기
		bfs(0, 0);
		resetVisited();
		// n, m부터 0, 0로 가면서 최소 거리 구하기
		bfs(n - 1, m - 1);
		// 한번도 최소거리가 나오지 못했다면
		if (ans == Integer.MAX_VALUE)
			ans = -1;
		System.out.println(ans);
	}

	private static void bfs(int r, int c) {
		LinkedList<int[]> q = new LinkedList<int[]>();
		// 각 bfs마다 도달점 정하기
		int targetR = 0;
		int targetC = 0;
		if (r == 0 && c == 0) {
			targetR = n - 1;
			targetC = m - 1;
		}
		// 출발점의 r, c와 벽을 부술 수 있는 횟수 cntCanBreak, 지나간 거리 pathCnt q에 넣어주기
		q.offer(new int[] { r, c, 1, 1 });
		visited[r][c] = true;
		while (!q.isEmpty()) {
			// 같은 이동횟수를 넣어주기 위해 같은 level 구분 작업
			int level = q.size();
			while (level > 0) {
				// q에서 꺼낸 element에 대해 탐색 수행
				int[] cur = q.poll();
				int curR = cur[0];
				int curC = cur[1];
				int cntCanBreak = cur[2];
				int pathCnt = cur[3];
				// target location에 도착시 정답 갱신, return으로 탈출
				if (curR == targetR && curC == targetC) {
					ans = Math.min(pathCnt, ans);
					return;
				}
				// 4방 탐색
				for (int i = 0; i < 4; i++) {
					int nR = cur[0] + delta[i][0];
					int nC = cur[1] + delta[i][1];
					if (rangeCheck(nR, nC) && !visited[nR][nC]) {
						char next = map[nR][nC];
						// 현재 벽을 깰 수 있는 횟수가 없고 탐색 요소가 벽이면
						if (next == '1' && cntCanBreak == 0) {
							continue;
						}
						// 이동 가능한 곳은 true 표시
						visited[nR][nC] = true;
						if (next == '1') {
							// 벽을 깨고 이동, 해당 location 넣기
							q.offer(new int[] { nR, nC, cntCanBreak - 1, pathCnt + 1 });
						}
						// 벽이 아니면 깰 수 있는 횟수는 유지
						else if (next == '0') {
							q.offer(new int[] { nR, nC, cntCanBreak, pathCnt + 1 });
						}
					}
				}
				--level;
			}
		}
	}

	private static void resetVisited() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				visited[i][j] = false;
			}
		}
	}

	private static boolean rangeCheck(int nR, int nC) {
		if (0 <= nR && nR < n && 0 <= nC && nC < m)
			return true;
		return false;
	}

}