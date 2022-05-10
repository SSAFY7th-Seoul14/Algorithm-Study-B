import java.util.*;
import java.io.*;

public class BOJ1895_필터 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 3 ≤ R ≤ 40, 3 ≤ C ≤ 40
		int r = stoi(st.nextToken());
		int c = stoi(st.nextToken());
		int[][] I = new int[r][c];
		for (int i = 0; i < r; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; ++j) {
				// 0 ≤ V ≤ 255
				I[i][j] = stoi(st.nextToken());
			}
		}
		int T = stoi(br.readLine());
		int ans = 0;
		for (int i = 1; i < r - 1; ++i) {
			for (int j = 1; j < c - 1; ++j) {
				if (check(I, i, j) >= T)
					++ans;
			}
		}
		System.out.println(ans);
		br.close();
	}

	private static int check(int[][] arr, int r, int c) {
		int[] sort = new int[9];
		int index = 0;
		for (int i = r - 1; i <= r + 1; ++i) {
			for (int j = c - 1; j <= c + 1; ++j) {
				sort[index++] = arr[i][j];
			}
		}
		Arrays.sort(sort);
		return sort[4];
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}