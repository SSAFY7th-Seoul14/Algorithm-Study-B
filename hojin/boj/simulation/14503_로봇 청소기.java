import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 반시계 방향으로 회전
public class BOJ14503 {

	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }, map;
	static int n, m;

	static class Vacuum {
		int originR, originC, direction, cnt;

		public Vacuum(int originR, int originC, int direction) {
			super();
			this.originR = originR;
			this.originC = originC;
			this.direction = direction;
			this.cnt = 0;
		}

		public void run() {
			clean();
			checkLeftDirection();
		}

		// 2.
		private void checkLeftDirection() {
			for (int i = 1; i <= 4; i++) {
				int nDir = (direction + 4 - i) % 4;
				int nR = originR + delta[nDir][0];
				int nC = originC + delta[nDir][1];
				// a
				if (nR >= 0 && nR < n && nC >= 0 && nC < m && map[nR][nC] == 0) {
					direction = nDir;
					moveTo(nR, nC);
					run();
					return;
				}
				// b
			}
			// c
			moveBackward();
			return;
			// d.
		}

		private void moveTo(int nR, int nC) {
			originR = nR;
			originC = nC;
		}

		private void moveBackward() {
			int backDir = (direction + 2) % 4;
			int bR = originR + delta[backDir][0];
			int bC = originC + delta[backDir][1];
			if (bR >= 0 && bR < n && bC >= 0 && bC < m && map[bR][bC] != 1) {
				moveTo(bR, bC);
				checkLeftDirection();
			}
		}

		// 1.
		private void clean() {
			map[originR][originC] = 2;
			++cnt;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 세로n, 가로m 3<= <=50
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		// 지도 초기화
		map = new int[n][m];

		st = new StringTokenizer(br.readLine());
		int originR = Integer.parseInt(st.nextToken());
		int originC = Integer.parseInt(st.nextToken());
		// 0 북 1 동 2 남 3 서
		int direction = Integer.parseInt(st.nextToken());

		Vacuum cleaner = new Vacuum(originR, originC, direction);

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cleaner.run();
		System.out.println(cleaner.cnt);
	}

}
