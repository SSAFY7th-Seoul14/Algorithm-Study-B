import java.util.*;
import java.io.*;

public class BOJ15684_사다리조작 {

	static int answer = -1;
	static int[][] ladders;
	static int width, height;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		width = Integer.parseInt(st.nextToken());
		int cnt = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		ladders = new int[height + 1][width + 1];
		for (int i = 0; i < cnt; ++i) {
			st = new StringTokenizer(br.readLine());
			int hNth = Integer.parseInt(st.nextToken());
			int wNth = Integer.parseInt(st.nextToken());
			// 오른쪽으로 1
			ladders[hNth][wNth] = 1;
			// 왼쪽으로 -1
			ladders[hNth][wNth + 1] = -1;
		}
		// 0부터 체크
		solve(0);
		System.out.println(answer);
		br.close();
	}

	private static void solve(int cntAdd) {
		if (cntAdd > 3) {
			return;
		}
		// cntAdd만큼 사다리 설치하고
		select(cntAdd, cntAdd, 1, 1);
		if (answer < 0)
			solve(cntAdd + 1);
	}

	private static void select(int cntAdd, int cnt, int r, int c) {
		if (cntAdd == 0) {
			if (answer < 0 && !checkDest()) {
				return;
			}
			answer = cnt;
			return;
		}
		for (int i = 1; i <= height; ++i) {
			for (int j = 1; j < width; ++j) {
				if (isAvailable(i, j)) {
					ladders[i][j] = 1;
					ladders[i][j + 1] = -1;
					select(cntAdd - 1, cnt, i, j);
					// 되돌리기
					ladders[i][j] = 0;
					ladders[i][j + 1] = 0;
				}
			}
		}
	}

	private static boolean checkDest() {
		// 세어보기
		for (int j = 1; j <= width; ++j) {
			int pointer = j;
			for (int i = 1; i <= height; ++i) {
				pointer += ladders[i][pointer];
			}
			if (pointer != j) {
				return false;
			}
		}
		return true;
	}

	private static boolean isAvailable(int i, int j) {
		if (ladders[i][j] == 0 && ladders[i][j + 1] == 0)
			return true;
		return false;
	}

}
