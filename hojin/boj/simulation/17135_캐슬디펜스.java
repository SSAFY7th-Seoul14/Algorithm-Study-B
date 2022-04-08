import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17137_캐슬디펜스 {

	static class Point {
		int r, c, dist;

		public Point(int r, int c, int dist) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
		}

	}

	static int n, m, d;
	static int[][] map;
	static int max;
	// 밑은 탐색해줄 필요가 없다.
	static int[][] delta = { { 0, -1 }, { -1, 0 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 3 ≤ N, M ≤ 15
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		// 1 ≤ D ≤ 10
		d = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max = 0;
		combi(0, 0, 0);
		System.out.println(max);
		br.close();
	}

	private static void combi(int nth, int start, int selected) {
		// 경우의 수로 mC3 궁수 위치 골라 놓고
		if (nth == 3) {
			// 고른 상태에서 게임 시작
			playGame(nth, selected);
			return;
		}
		for (int i = start; i < m; ++i) {
			combi(nth + 1, i + 1, selected | 1 << i);
		}
	}

	private static void playGame(int cnt, int selected) {
		int[][] newMap = new int[n][m];
		copyMap(newMap);
		int killCnt = 0;
		while (true) {
			// 궁수 턴
			killCnt += archerTurn(selected, newMap);
			// 이동 턴
			enemyTurn(newMap);
			// 적 개수 세어주기
			if (countEnemy(newMap)) {
				// 전부 0이 되면 게임 종료
				break;
			}
		}
		max = Math.max(max, killCnt);
	}

	private static int archerTurn(int selected, int[][] newMap) {
		int cnt = 0;
		boolean[][] attacked = new boolean[n][m];
		for (int i = 0; i < m; ++i) {
			// 선택된 i에서 처리
			if ((selected & 1 << i) > 0) {
				// 궁수 바로 위부터 d 이하 bfs로 체크
				bfs(n - 1, i, newMap, attacked);
			}
		}
		cnt += attackCnt(newMap, attacked);
		return cnt;
	}

	private static int attackCnt(int[][] newMap, boolean[][] attacked) {
		int cnt = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (attacked[i][j]) {
					newMap[i][j] = 0;
					++cnt;
				}
			}
		}
		return cnt;
	}

	private static void bfs(int r, int c, int[][] newMap, boolean[][] attacked) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[n][m];
		// q에서 나온 값의 거리가 d이면 탈출 시킬 것이므로 0을 넣어주고 시작
		// 원래 여기가 1이고 d까지 갈 수 있는 것이므로
		// 0부터 d-1까지 이동으로 처리하겠다.
		q.add(new Point(r, c, 0));
		visited[r][c] = true;
		// bfs 왼쪽부터 체크
		while (!q.isEmpty()) {
			Point cur = q.poll();
			int curR = cur.r;
			int curC = cur.c;
			int curDist = cur.dist;
			if (curDist == d)
				break;
			if (newMap[curR][curC] == 1) {
				attacked[curR][curC] = true;
				return;
			}
			for (int i = 0; i < 3; ++i) {
				int nR = curR + delta[i][0];
				int nC = curC + delta[i][1];
				if (rangeCheck(nR, nC) && !visited[nR][nC]) {
					visited[nR][nC] = true;
					q.offer(new Point(nR, nC, curDist + 1));
				}
			}
		}
	}

	private static boolean rangeCheck(int nR, int nC) {
		if (0 <= nR && nR < n && 0 <= nC && nC < m)
			return true;
		return false;
	}

	private static void enemyTurn(int[][] newMap) {
		for (int i = n - 1; i > 0; --i) {
			for (int j = 0; j < m; ++j) {
				newMap[i][j] = newMap[i - 1][j];
			}
		}
		Arrays.fill(newMap[0], 0);
	}

	private static boolean countEnemy(int[][] newMap) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (newMap[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}

	private static void copyMap(int[][] newMap) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				newMap[i][j] = map[i][j];
			}
		}
	}

}