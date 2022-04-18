import java.util.*;
import java.io.*;

public class BOJ20058_마법사상어와파이어스톰 {
	static int[][] A;
	static int sqr2N, sum = 0, maxCnt = 0;
	static boolean[][] visited;
	static LinkedList<int[]> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		sqr2N = (int) Math.pow(2, n);
		A = new int[sqr2N][sqr2N];
		for (int i = 0; i < sqr2N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < sqr2N; ++j) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < q; ++i) {
			fireStorm(Integer.parseInt(st.nextToken()));
		}
		visited = new boolean[sqr2N][sqr2N];
		solve();
		bw.write(String.valueOf(sum));
		bw.newLine();
		bw.write(String.valueOf(maxCnt));
		bw.flush();
		bw.close();
		br.close();
	}

	private static void solve() {
		for (int i = 0; i < sqr2N; ++i) {
			for (int j = 0; j < sqr2N; ++j) {
				if (!visited[i][j] && A[i][j] > 0) {
					iceberg(i, j);
				}
			}
		}
	}

	private static void iceberg(int r, int c) {
		q = new LinkedList<>();
		q.offer(new int[] { r, c });
		int cnt = 1;
		visited[r][c] = true;
		sum += A[r][c];
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			for (int i = 0; i < 4; ++i) {
				int nR = curR + dr[i];
				int nC = curC + dc[i];
				if (rangeCheck(nR, nC) && !visited[nR][nC]) {
					int iceAmount = A[nR][nC];
					visited[nR][nC] = true;
					if (iceAmount > 0) {
						sum += iceAmount;
						++cnt;
						q.offer(new int[] { nR, nC });
					}
				}
			}
		}
		maxCnt = Math.max(maxCnt, cnt);
	}

	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	private static void fireStorm(int lv) {
		int lvSqr = (int) Math.pow(2, lv);
		for (int i = 0; i < sqr2N; i += lvSqr) {
			for (int j = 0; j < sqr2N; j += lvSqr) {
				rotate(i, j, lvSqr);
			}
		}
		boolean[][] willMelt = new boolean[sqr2N][sqr2N];
		for (int i = 0; i < sqr2N; ++i) {
			for (int j = 0; j < sqr2N; ++j) {
				willMelt[i][j] = toMelt(i, j);
			}
		}
		for (int i = 0; i < sqr2N; ++i) {
			for (int j = 0; j < sqr2N; ++j) {
				if (willMelt[i][j])
					--A[i][j];
			}
		}
	}

	private static boolean toMelt(int r, int c) {
		if (A[r][c] == 0)
			return false;
		int cnt = 0;
		for (int i = 0; i < 4; ++i) {
			int nR = r + dr[i];
			int nC = c + dc[i];
			if (rangeCheck(nR, nC)) {
				if (A[nR][nC] > 0)
					++cnt;
			}
		}
		if (cnt >= 3)
			return false;
		return true;
	}

	private static void rotate(int r, int c, int lvSqr) {
		if (lvSqr < 2)
			return;
		int[] tmp = new int[lvSqr];
		int range = lvSqr - 1;
		for (int i = 0; i < range; ++i)
			tmp[i] = A[r + i][c];
		for (int i = 0; i < range; ++i)
			A[r + i][c] = A[r + range][c + i];
		for (int i = 0; i < range; ++i)
			A[r + range][c + i] = A[r + range - i][c + range];
		for (int i = range; i > 0; --i)
			A[r + i][c + range] = A[r][c + i];
		for (int i = 0; i < range; ++i)
			A[r][c + range - i] = tmp[i];
		rotate(r + 1, c + 1, lvSqr - 2);
	}

	private static boolean rangeCheck(int r, int c) {
		if (0 <= r && r < sqr2N && 0 <= c && c < sqr2N)
			return true;
		return false;
	}

}
