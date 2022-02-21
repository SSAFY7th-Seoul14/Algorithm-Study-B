// 20분
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1974 {

	static int[][] sudoku = new int[9][9];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < 9; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++) {
					sudoku[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append(check()).append("\n");
		}
		br.close();
		System.out.println(sb);
	}

	private static int check() {
		int ans = 1;
		// 1row씩 돌아가면서 check
		for (int i = 0; i < 9; i++) {
			int sum = 0;
			for (int j = 0; j < 9; j++) {
				sum += sudoku[i][j];
			}
			if (sum != 45)
				return 0;
		}
		// 1col씩 돌아가면서 check
		for (int i = 0; i < 9; i++) {
			int sum = 0;
			for (int j = 0; j < 9; j++) {
				sum += sudoku[j][i];
			}
			if (sum != 45)
				return 0;
		}
		// 9칸씩 돌아가면서 check
		for (int i = 0; i < 9; i += 3) {
			for (int j = 0; j < 9; j += 3) {
				ans = areaCheck(i, j);
			}
			if (ans == 0)
				return 0;
		}
		return 1;
	}

	private static int areaCheck(int r, int c) {
		int sum = 0;
		for (int i = r; i < r + 3; i++) {
			for (int j = c; j < c + 3; j++) {
				sum += sudoku[i][j];
			}
		}
		if (sum != 45)
			return 0;
		return 1;
	}

}
