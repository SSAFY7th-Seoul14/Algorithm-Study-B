import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17070_파이프옮기기1 {
	static int cnt, n;
	static int[][] grid;
	static int[][] delta = {
			// 가로
			{ 0, 1 },
			// 대각선
			{ 1, 1 },
			// 세로
			{ 1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		grid = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cnt = 0;
		dfs(0, 1, 0);
		System.out.println(cnt);
	}

	private static void dfs(int r, int c, int dir) {
		if (r == n - 1 && c == n - 1) {
			++cnt;
			return;
		}
		// 가로
		if (dir == 0) {
			// 가로일 때 끝점이 이미 n-1 도달하면 실패
			if (c == n - 1) {
				return;
			}
			check(r, c, 2);
		}
		// 세로
		else if (dir == 2) {
			// 세로일 때 끝점이 이미 n-1 도달하면 실패
			if (r == n - 1) {
				return;
			}
			check(r, c, 0);
		}
		// 대각선
		else {
			check(r, c, 3);
		}
	}

	private static void check(int r, int c, int skipNo) {
		for (int i = 0; i < 3; i++) {
			if (i == skipNo)
				continue;
			int nR = r + delta[i][0];
			int nC = c + delta[i][1];
			switch (i) {
			case 1:
				if (nR > 0 && nR < n && nC > 0 && nC < n) {
					if (grid[nR][nC] == 0 && grid[nR - 1][nC] == 0 && grid[nR][nC - 1] == 0) {
						dfs(nR, nC, 1);
					}
				}
				break;
			default:
				if (nR >= 0 && nR < n && nC >= 0 && nC < n) {
					if (grid[nR][nC] == 0) {
						dfs(nR, nC, i);
					}
				}
				break;
			}
		}
	}

}