import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ2239_스도쿠 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static ArrayList<Point> list = new ArrayList<>();
	static int[][] sudoku;
	static boolean isFirst;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		sudoku = new int[9][9];
		for (int i = 0; i < 9; ++i) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < 9; ++j) {
				int inp = line[j] - '0';
				if (inp == 0)
					list.add(new Point(i, j));
				sudoku[i][j] = inp;
			}
		}
		isFirst = false;
		dfs(0);
		for (int[] q : sudoku) {
			for (int i : q) {
				sb.append(i);
			}
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	private static void dfs(int cnt) {
		if (cnt == list.size()) {
			isFirst = true;
			return;
		}
		Point curP = list.get(cnt);
		int r = curP.r;
		int c = curP.c;
		for (int i = 1; i <= 9; ++i) {
			if (check(r, c, i)) {
				sudoku[r][c] = i;
				dfs(cnt + 1);
			}
			if (isFirst)
				return;
			sudoku[r][c] = 0;
		}
	}

	private static boolean check(int r, int c, int val) {
		for (int i = 0; i < 9; ++i) {
			if (sudoku[r][i] == val || sudoku[i][c] == val)
				return false;
		}
		int nR = r / 3 * 3;
		int nC = c / 3 * 3;
		for (int i = nR; i < nR + 3; ++i) {
			for (int j = nC; j < nC + 3; ++j) {
				if (sudoku[i][j] == val)
					return false;
			}
		}
		return true;
	}

}