import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2573_빙산 {

	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }, map, toMelt;
	static int n, m, cntIceberg;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 3 <= <=300
		n = Integer.parseInt(st.nextToken());
		// 3 <= <=300
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		int year = 0;
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; ++j) {
				// 0 <= <= 10 10,000개 이하
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cntIceberg = 1;
		boolean onlyOne = false;
		while (cntIceberg > 0) {
			reset();
			// 덩어리 개수 세기
			countIcebergs();
			// 2개면 끝 년 수 세기
			if (cntIceberg > 1) {
				onlyOne = false;
				break;
			}
			// 녹일 개수만큼 녹이기
			melting();
			++year;
			onlyOne = true;
		}
		if (onlyOne) {
			System.out.println("0");
		} else {
			// 입력할 때부터 두 덩이 이상이거나 끝까지 한덩이이면 0
			System.out.println(year);
		}
	}

	private static void reset() {
		toMelt = new int[n][m];
		visited = new boolean[n][m];
		cntIceberg = 0;
	}

	private static void melting() {
		// for문 돌면서
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				// map에서 toMelt만큼 빼주기
				map[i][j] -= toMelt[i][j];
				// 0보다 작으면 그냥 바닷물 0
				if (map[i][j] < 0)
					map[i][j] = 0;
			}
		}
	}

	private static void countIcebergs() {
		// for문 돌다가
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				// 0이 아니면서 방문하지 않은 칸 만나면 == 방문하지 않은 얼음이면
				if (isNotVisitedIce(i, j)) {
					// dfs 시작 (dfs하면서 얼음 칸마다 녹일 개수 세기)
					dfs(i, j);
					++cntIceberg;
				}
				// 남은 곳에서 방문하지 않으면서 0이 아닌곳이 있다면
				if (isNotVisitedIce(i, j)) {
					// count 증가, return
					++cntIceberg;
					return;
				}
			}
		}
	}

	private static void dfs(int i, int j) {
		// 방문 처리
		visited[i][j] = true;
		// 사방탐색하면서
		for (int k = 0; k < 4; ++k) {
			int nR = i + delta[k][0];
			int nC = j + delta[k][1];
			if (rangeCheck(nR, nC)) {
				if (isNotVisitedIce(nR, nC)) {
					dfs(nR, nC);
				}
				// 사방 탐색에서 0이 있다면 i, j 녹을 포인트 증가
				if (map[nR][nC] == 0) {
					++toMelt[i][j];
				}
			}
		}
	}

	private static boolean isNotVisitedIce(int i, int j) {
		if (map[i][j] > 0 && !visited[i][j])
			return true;
		return false;
	}

	private static boolean rangeCheck(int r, int c) {
		if (0 <= r && r < n && 0 <= c && c < m)
			return true;
		return false;
	}
}