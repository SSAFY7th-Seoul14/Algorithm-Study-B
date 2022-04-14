import java.util.*;
import java.io.*;

public class BOJ16197_두동전 {
	static class Point {
		int r, c, time;

		public Point(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}

	}

	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
	static int h, w;
	static char[][] map;
	static LinkedList<Point> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new char[h][w];
		for (int i = 0; i < h; ++i) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < w; ++j) {
				if (map[i][j] == 'o') {
					q.offer(new Point(i, j, 0));
				}
			}
		}
		// bfs해서 둘다 떨어지는 경우는 지나가고
		System.out.println(bfs());
		// 하나만 떨어질 때 출력
		br.close();
	}

	private static int bfs() {
		while (!q.isEmpty()) {
			Point cur1 = q.poll();
			int curR1 = cur1.r;
			int curC1 = cur1.c;
			Point cur2 = q.poll();
			int curR2 = cur2.r;
			int curC2 = cur2.c;
			int nextTime = cur1.time + 1;
			if (nextTime > 10)
				return -1;
			for (int i = 0; i < 4; ++i) {
				int nR1 = curR1 + dr[i];
				int nC1 = curC1 + dc[i];
				int nR2 = curR2 + dr[i];
				int nC2 = curC2 + dc[i];
				boolean check1 = rangeCheck(nR1, nC1);
				boolean check2 = rangeCheck(nR2, nC2);
				// 둘 중 하나만 범위 밖이면 정답
				if (check1 != check2)
					return nextTime;
				// 둘이 동시에 밖이면 제끼고
				else if (!check1 && !check2)
					continue;
				// 돌다 범위 안이면 무조건 q에 넣기
				else {
					char next1 = map[nR1][nC1];
					char next2 = map[nR2][nC2];
					switch (next1) {
					case '#':
						q.offer(new Point(curR1, curC1, nextTime));
						break;
					default:
						q.offer(new Point(nR1, nC1, nextTime));
						break;
					}
					switch (next2) {
					case '#':
						q.offer(new Point(curR2, curC2, nextTime));
						break;
					default:
						q.offer(new Point(nR2, nC2, nextTime));
						break;
					}
				}
			}
		}
		return -1;
	}

	private static boolean rangeCheck(int r, int c) {
		if (0 <= r && r < h && 0 <= c && c < w)
			return true;
		return false;
	}

}
