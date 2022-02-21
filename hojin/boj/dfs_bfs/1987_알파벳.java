import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static char[][] grid;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int r, c, cnt, visited, max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		cnt = 0;
		grid = new char[r][];
		for (int i = 0; i < r; i++) {
			grid[i] = br.readLine().toCharArray();
		}
		visited = 0;
		runHorse(0, 0);
		System.out.println(max);
	}

	private static void runHorse(int row, int col) {
		char curVal = grid[row][col];
		visited |= 1 << curVal - 'A';
		cnt++;
		for (int i = 0; i < 4; i++) {
			int nRow = row + delta[i][0];
			int nCol = col + delta[i][1];
			if (nRow >= 0 && nRow < r && nCol >= 0 && nCol < c) {
				if ((visited & 1 << (grid[nRow][nCol] - 'A')) == 0) {
					runHorse(nRow, nCol);
				}
			}

		}
		max = Math.max(max, cnt--);
		visited -= 1 << curVal - 'A';
	}

}