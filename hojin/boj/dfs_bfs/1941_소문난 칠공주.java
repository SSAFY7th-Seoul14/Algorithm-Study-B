import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class BOJ1941_소문난칠공주 {
	static boolean[][] selected = new boolean[5][5];
	static char[][] region;
	static int[][] delta = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		region = new char[5][];
		cnt = 0;
		for (int i = 0; i < 5; i++) {
			region[i] = br.readLine().toCharArray();
		}

		// 25C7해봤자 40만 정도
		combi(0, 0);
		bw.write(String.valueOf(cnt));
		bw.flush();
		bw.close();
		br.close();
	}

	private static void combi(int depth, int start) {
		if (depth == 7) {
			if (bfs())
				++cnt;
			return;
		}
		for (int i = start; i < 25; i++) {
			selected[i / 5][i % 5] = true;
			combi(depth + 1, i + 1);
			selected[i / 5][i % 5] = false;
		}
	}

	private static boolean bfs() {
		LinkedList<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[5][5];
		int len = 0;
		int isY = 3;
		// q 넣어주기
		outer: for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				if (selected[i][j]) {
					q.offer(new int[] { i, j });
					++len;
					visited[i][j] = true;
					if (region[i][j] == 'Y')
						--isY;
					break outer;
				}
			}
		}
		// bfs 수행
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			// 4방탐색
			for (int i = 0; i < 4; ++i) {
				int nR = cur[0] + delta[i][0];
				int nC = cur[1] + delta[i][1];
				if (rangeCheck(nR, nC) && !visited[nR][nC] &&
						// 조합에서 선택 된 학생일 경우만 넣어주기
						selected[nR][nC]) {
					q.offer(new int[] { nR, nC });
					++len;
					visited[nR][nC] = true;
					if (region[nR][nC] == 'Y')
						--isY;
				}
			}
			// Y가 3개보다 많으면
			if (isY < 0)
				return false;
			if (len == 7)
				return true;
		}
		return false;
	}

	private static boolean rangeCheck(int r, int c) {
		if (0 <= r && r < 5 && 0 <= c && c < 5)
			return true;
		return false;
	}
}