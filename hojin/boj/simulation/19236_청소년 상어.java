import java.util.*;
import java.io.*;

public class BOJ19236_청소년상어 {
	static int[][] delta = { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 } };
	static int max = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] map = new int[4][4];
		int[][] fishes = new int[18][3];
		for (int i = 0; i < 4; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; ++j) {
				int no = stoi(st.nextToken());
				int dir = stoi(st.nextToken()) - 1;
				fishes[no] = new int[] { i, j, dir };
				map[i][j] = no;
			}
		}
		sharkEat(map, fishes, 0, 0, 0);
		System.out.println(max);
		br.close();
	}

	private static void sharkMove(int[][] map, int[][] fishes, int score) {
		int curR = fishes[17][0];
		int curC = fishes[17][1];
		int dir = fishes[17][2];
		for (int i = 1; i <= 3; ++i) {
			int[][] copiedMap = copyMap(map);
			int[][] copiedFishes = copyFishes(fishes);
			int nR = curR + delta[dir][0] * i;
			int nC = curC + delta[dir][1] * i;
			if (rangeCheck(nR, nC) && copiedMap[nR][nC] > 0 && copiedMap[nR][nC] < 17) {
				sharkEat(copiedMap, copiedFishes, nR, nC, score);
			}
		}
	}

	private static void sharkEat(int[][] map, int[][] fishes, int nR, int nC, int score) {
		int target = map[nR][nC];
		int dir = fishes[target][2];
		int curR = fishes[17][0];
		int curC = fishes[17][1];
		map[curR][curC] = 0;
		score += target;
		max = Math.max(max, score);
		fishes[target] = null;
		map[nR][nC] = 17;
		fishes[17][0] = nR;
		fishes[17][1] = nC;
		fishes[17][2] = dir;
		moveFishes(map, fishes, score);
	}

	private static void moveFishes(int[][] map, int[][] fishes, int score) {
		for (int i = 1; i <= 16; ++i) {
			if (fishes[i] != null) {
				moveFish(map, fishes, i);
			}
		}
		sharkMove(map, fishes, score);
	}

	private static void moveFish(int[][] map, int[][] fishes, int i) {
		int r = fishes[i][0];
		int c = fishes[i][1];
		int dir = fishes[i][2];
		int canMove = 8;
		while (canMove-- > 0) {
			int nR = r + delta[dir][0];
			int nC = c + delta[dir][1];
			if (rangeCheck(nR, nC) && possible(map, nR, nC)) {
				fishes[i][2] = dir;
				swap(r, c, nR, nC, map, fishes);
				return;
			} else {
				++dir;
				dir %= 8;
			}
		}
	}

	private static void swap(int r, int c, int nR, int nC, int[][] map, int[][] fishes) {
		int to = map[nR][nC];
		int from = map[r][c];
		map[r][c] = map[nR][nC];
		map[nR][nC] = from;
		if (to > 0) {
			for (int i = 0; i < 2; ++i) {
				int tmp = fishes[from][i];
				fishes[from][i] = fishes[to][i];
				fishes[to][i] = tmp;
			}
		} else {
			fishes[from][0] = nR;
			fishes[from][1] = nC;
		}
	}

	private static boolean possible(int[][] map, int nR, int nC) {
		int target = map[nR][nC];
		if (target == 17) {
			return false;
		} else {
			return true;
		}
	}

	private static boolean rangeCheck(int r, int c) {
		if (0 <= r && r < 4 && 0 <= c && c < 4)
			return true;
		return false;
	}

	private static int[][] copyMap(int[][] map) {
		int[][] retMap = new int[4][4];
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 4; ++j) {
				retMap[i][j] = map[i][j];
			}
		}
		return retMap;
	}

	private static int[][] copyFishes(int[][] fishes) {
		int[][] retFishes = new int[18][3];
		for (int i = 1; i <= 17; ++i) {
			if (fishes[i] != null) {
				for (int j = 0; j < 3; ++j) {
					retFishes[i][j] = fishes[i][j];
				}
			} else {
				retFishes[i] = null;
			}
		}
		return retFishes;
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
