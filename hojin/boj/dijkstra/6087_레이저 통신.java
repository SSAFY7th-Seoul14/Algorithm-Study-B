//90분

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ6087_레이저통신 {

	static class Point {
		int r, c;
		int cntMirror;

		public Point(int r, int c, int cntMirror) {
			this.r = r;
			this.c = c;
			this.cntMirror = cntMirror;
		}

	}

	static int w, h, answer;
	static char[][] map;
	static Point[] cPoint;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new char[h][w];
		cPoint = new Point[2];
		int cIndex = 0;
		for (int i = 0; i < h; ++i) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < w; ++j) {
				if (map[i][j] == 'C') {
					// bfs 처리 쉽게 해주기 위해 C자리 기억만 해두고 빈칸으로 바꿔두기
					map[i][j] = '.';
					cPoint[cIndex++] = new Point(i, j, -1);
				}
			}
		}
		bfs();
		br.close();
	}

	private static void bfs() {
		LinkedList<Point> q = new LinkedList<>();
		int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
		// 처음에는 못떠올렸지만 dijkstra라는 힌트를 보고 생각해본 방법. minCost를 사용한 거울 횟수로 생각해주면 된다!
		int[][] mirrorCnt = new int[h][w];
		for (int i = 0; i < h; ++i) {
			Arrays.fill(mirrorCnt[i], Integer.MAX_VALUE);
		}
		// 출발점 표시
		mirrorCnt[cPoint[0].r][cPoint[0].c] = cPoint[0].cntMirror;
		q.offer(cPoint[0]);
		// 목표 지점 index
		int targetR = cPoint[1].r;
		int targetC = cPoint[1].c;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			int curR = cur.r;
			int curC = cur.c;
			// 다음 Point에 넣을 추가된 거울 개수
			int nextCnt = cur.cntMirror + 1;
			// 목표점에 Point가 반환 됐다면 최소 개수로 도달 한것
			// q이기 때문에 mirrorCnt가 작은 값부터 들어가는 게 보장된다.
			if (curR == targetR && curC == targetC) {
				// 현재 Point가 들고 있던 거울 개수 출력
				System.out.println(cur.cntMirror);
				// bfs 탈출
				return;
			}
			for (int i = 0; i < 4; ++i) {
				int nR = curR + dr[i];
				int nC = curC + dc[i];
				// 해당 방향으로 레이저 쏘는 행위
				while (rangeCheck(nR, nC) && map[nR][nC] == '.' &&
						// 배열에 표시된 거울 개수가 현재 Point에서 갱신할 값보다 크면 갱신해주기
						mirrorCnt[nR][nC] >= nextCnt) {
					// mirrorCnt 갱신
					mirrorCnt[nR][nC] = nextCnt;
					// 다음 bfs 탐색 위해 넣기. q head쪽일 수록 mirrorCnt가 작은건 보장된다.
					q.offer(new Point(nR, nC, nextCnt));
					nR += dr[i];
					nC += dc[i];
				}
			}
		}
	}

	private static boolean rangeCheck(int nR, int nC) {
		if (0 <= nR && nR < h && 0 <= nC && nC < w)
			return true;
		return false;
	}

}