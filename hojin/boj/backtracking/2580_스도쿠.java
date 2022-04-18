import java.util.*;
import java.io.*;

public class BOJ2580_스도쿠 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static int cntZero;
	static boolean isFirst = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] sudoku = new int[9][9];
		ArrayList<Point> zeroList = new ArrayList<>();
		for (int i = 0; i < 9; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; ++j) {
				int inp = Integer.parseInt(st.nextToken());
				if (inp == 0)
					zeroList.add(new Point(i, j));
				sudoku[i][j] = inp;
			}
		}
		cntZero = zeroList.size();
		BT(0, zeroList, sudoku);
		StringBuilder sb = new StringBuilder();
		for (int[] is : sudoku) {
			for (int i : is) {
				sb.append(i).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
		br.close();
	}

	private static void BT(int cnt, ArrayList<Point> zeroList, int[][] sudoku) {
		if (cnt == cntZero) {
			isFirst = true;
			return;
		}
		Point cur = zeroList.get(cnt);
		int curR = cur.r;
		int curC = cur.c;
		for (int i = 1; i <= 9; ++i) {
			if (check(curR, curC, sudoku, i)) {
				sudoku[curR][curC] = i;
				BT(cnt + 1, zeroList, sudoku);
			}
			if (isFirst)
				return;
			sudoku[curR][curC] = 0;
		}
	}

	private static boolean check(int curR, int curC, int[][] sudoku, int curNo) {
		for (int i = 0; i < 9; ++i) {
			if (sudoku[curR][i] == curNo)
				return false;
			if (sudoku[i][curC] == curNo)
				return false;
		}
		int compareR = curR / 3 * 3;
		int compareC = curC / 3 * 3;
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (sudoku[compareR + i][compareC + j] == curNo)
					return false;
			}
		}
		return true;
	}

}
