import java.io.*;
import java.util.*;

public class BOJ11660_구간합구하기5 {

	static int[][] table;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 표의 크기
		int n = Integer.parseInt(st.nextToken());
		// 합을 구해야하는 횟수
		int m = Integer.parseInt(st.nextToken());

		table = new int[n + 1][n + 1];
		for (int i = 1; i <= n; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; ++j) {
				table[i][j] = Integer.parseInt(st.nextToken()) + table[i - 1][j] + table[i][j - 1]
						- table[i - 1][j - 1];
			}
		}
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken()), c1 = Integer.parseInt(st.nextToken()),
					r2 = Integer.parseInt(st.nextToken()), c2 = Integer.parseInt(st.nextToken());
			bw.write(String.valueOf(table[r2][c2] - table[r1 - 1][c2] - table[r2][c1 - 1] + table[r1 - 1][c1 - 1]));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}

}