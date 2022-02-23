import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ4963 {

	static int[][] delta = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } },
			map;
	static int h, w;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0)
				break;
			map = new int[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int cnt = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1) {
						checkIsland(i, j);
						++cnt;
					}
				}
			}
			bw.write(String.format("%d%n", cnt));
		}
		br.close();
		bw.flush();
		bw.close();
	}

	private static void checkIsland(int r, int c) {
		if (map[r][c] == 0)
			return;
		map[r][c] = 0;
		for (int i = 0; i < 8; i++) {
			int nR = r + delta[i][0];
			int nC = c + delta[i][1];

			// 8방 탐색하면서
			if (nR >= 0 && nR < h && nC >= 0 && nC < w) {
				if (map[nR][nC] == 1) {
					checkIsland(nR, nC);
				}
			}
		}
	}

}