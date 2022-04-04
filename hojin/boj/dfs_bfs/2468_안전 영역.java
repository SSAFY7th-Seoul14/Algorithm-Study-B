import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2468_안전영역 {
	// 0높이 일 때는 안전영역 어차피 1
	static int safeArea = 1, maxH = 1, n;
	static int[][] map, delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 2<= <=100
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; ++j) {
				// 1 <= <= 100
				int inp = Integer.parseInt(st.nextToken());
				if (inp > maxH)
					maxH = inp;
				map[i][j] = inp;
			}
		}
		// maxH보다 높으면 안정영역 0
		for (int i = 1; i < maxH; ++i) {
			visited = new boolean[n][n];
			checkSafe(i);
		}
		System.out.println(safeArea);
	}

	private static void checkSafe(int rainH) {
		int cnt = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (map[i][j] > rainH && !visited[i][j]) {
					visited[i][j] = true;
					dfs(i, j, rainH);
					++cnt;
				}
			}
		}
		if (cnt > safeArea)
			safeArea = cnt;
	}

	private static void dfs(int r, int c, int h) {
		for (int i = 0; i < 4; ++i) {
			int nR = r + delta[i][0];
			int nC = c + delta[i][1];
			if (rangeCheck(nR, nC) && map[nR][nC] > h && !visited[nR][nC]) {
				visited[nR][nC] = true;
				dfs(nR, nC, h);
			}
		}
	}

	private static boolean rangeCheck(int r, int c) {
		if (0 <= r && r < n && 0 <= c && c < n)
			return true;
		return false;
	}

}