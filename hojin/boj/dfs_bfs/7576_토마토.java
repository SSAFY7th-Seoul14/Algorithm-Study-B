import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576_토마토 {

	static int m, n;
	static int[][] tray, delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static Queue<int[]> q = new LinkedList<int[]>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 상자 가로 2 <= m <= 1,000
		m = Integer.parseInt(st.nextToken());
		// 상자 세로 2 <= n <= 1,000
		n = Integer.parseInt(st.nextToken());
		tray = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				// 익은 토마토 1의 위치 기억하기
				if (tmp == 1)
					q.offer(new int[] { i, j });
				tray[i][j] = tmp;
			}
		}
		br.close();
		int day = -1;
		// bfs 수행
		while (!q.isEmpty()) {
			int size = q.size();
			while (size > 0) {
				int[] loc = q.poll();
				int curR = loc[0];
				int curC = loc[1];
				for (int i = 0; i < 4; i++) {
					int nR = curR + delta[i][0];
					int nC = curC + delta[i][1];

					if (0 <= nR && nR < n && 0 <= nC && nC < m) {
						if (tray[nR][nC] == 0) {
							tray[nR][nC] = 1;
							q.offer(new int[] { nR, nC });
						}
					}

				}
				--size;
			}
			++day;
		}
		boolean fail = false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (tray[i][j] == 0) {
					fail = true;
					break;
				}
			}
		}
		if (fail) {
			System.out.println("-1");
		} else {
			System.out.println(day);
		}
	}
}