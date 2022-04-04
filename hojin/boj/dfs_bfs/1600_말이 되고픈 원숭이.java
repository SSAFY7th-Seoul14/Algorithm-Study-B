import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Point {
	int r, c, jumpCnt;

	public Point(int r, int c, int jumpCnt) {
		this.r = r;
		this.c = c;
		this.jumpCnt = jumpCnt;
	}

}

public class BOJ1600_말이되고픈원숭이 {

	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } },
			deltaJump = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } };
	static int k, w, h, cnt;
	static boolean[][][] visitedByWalk, visitedByJump;
	static char[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new char[h][w];
		visitedByWalk = new boolean[h][w][k + 1];
		visitedByJump = new boolean[h][w][k + 1];
		for (int i = 0; i < h; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; ++j) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		cnt = 0;
		// bfs로 돌리기
		if (bfs()) {
			System.out.println(cnt);
		} else {
			System.out.println("-1");
		}
	}

	private static boolean bfs() {
		LinkedList<Point> q = new LinkedList<>();
		// 사방 탐색 넣고, jump 넣고
		q.offer(new Point(0, 0, k));
		visitedByWalk[0][0][k] = true;
		visitedByJump[0][0][k] = true;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Point cur = q.poll();
				int curR = cur.r;
				int curC = cur.c;
				int curJumpCnt = cur.jumpCnt;
				if (curR == h - 1 && curC == w - 1) {
					return true;
				}
				// 4방 탐색 경우
				for (int i = 0; i < 4; ++i) {
					int nR = curR + delta[i][0];
					int nC = curC + delta[i][1];
					if (rangeCheck(nR, nC) && map[nR][nC] == '0' && !visitedByWalk[nR][nC][curJumpCnt]) {
						visitedByWalk[nR][nC][curJumpCnt] = true;
						q.offer(new Point(nR, nC, cur.jumpCnt));
					}
				}
				// jump 경우
				if (curJumpCnt > 0) {
					for (int i = 0; i < 8; ++i) {
						int nR = curR + deltaJump[i][0];
						int nC = curC + deltaJump[i][1];
						if (rangeCheck(nR, nC) && map[nR][nC] == '0' && !visitedByJump[nR][nC][curJumpCnt]) {
							visitedByJump[nR][nC][curJumpCnt] = true;
							q.offer(new Point(nR, nC, curJumpCnt - 1));
						}
					}
				}
			}
			++cnt;
		}
		return false;
	}

	private static boolean rangeCheck(int r, int c) {
		if (0 <= r && r < h && 0 <= c && c < w)
			return true;
		return false;
	}

}