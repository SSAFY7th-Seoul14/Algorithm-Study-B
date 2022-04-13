import java.util.*;
import java.io.*;

public class BOJ17143_낚시왕 {
	static class Shark implements Comparable<Shark> {
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			map[r][c].add(this);
		}

		public void move() {
			int nR = this.r;
			int nC = this.c;
			for (int i = 0; i < this.s; ++i) {
				nR += delta[this.d][0];
				nC += delta[this.d][1];
				if (nR < 0 || nR == h) {
					this.d = this.d % 2 == 0 ? this.d + 1 : this.d - 1;
					nR = (2 * (h - 1) - nR) % Math.max((h - 1), 2);
				}
				if (nC < 0 || nC == w) {
					this.d = this.d % 2 == 0 ? this.d + 1 : this.d - 1;
					nC = (2 * (w - 1) - nC) % Math.max((w - 1), 2);
				}
			}
			this.r = nR;
			this.c = nC;
			map[nR][nC].add(this);
		}

		@Override
		public int compareTo(Shark o) {
			return this.z - o.z;
		}

	}

	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static LinkedList<Shark> list = new LinkedList<>();
	static PriorityQueue<Shark>[][] map;
	static int h, w;
	static int huntingSize;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new PriorityQueue[h][w];
		for (int i = 0; i < h; ++i) {
			for (int j = 0; j < w; ++j) {
				map[i][j] = new PriorityQueue<Shark>();
			}
		}
		int m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			new Shark(r, c, s, d, z);
		}
		huntingSize = 0;
		for (int i = 0; i < w; ++i) {
			hunting(i);
			moveShark();
		}
		System.out.println(huntingSize);
		br.close();
	}

	private static void moveShark() {
		for (int i = 0; i < h; ++i) {
			for (int j = 0; j < w; ++j) {
				if (!map[i][j].isEmpty()) {
					map[i][j].poll().move();
				}
			}
		}
		while (!list.isEmpty()) {
			list.poll().move();
		}
		for (int i = 0; i < h; ++i) {
			for (int j = 0; j < w; ++j) {
				if (!map[i][j].isEmpty()) {
					while (map[i][j].size() > 1) {
						map[i][j].poll();
					}
				}
			}
		}
	}

	private static void hunting(int col) {
		for (int i = 0; i < h; ++i) {
			if (map[i][col].size() > 0) {
				Shark toHunt = map[i][col].poll();
				huntingSize += toHunt.z;
				return;
			}
		}
	}
}