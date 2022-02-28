import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16236_2 {

	static int map[][], n, rTo, cTo, timeSpend;
	static int[][] delta = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
	static boolean[][] visit;

	static class BabyShark {
		int size, indexR, indexC, time, eaten;

		BabyShark(int indexR, int indexC) {
			this(2, indexR, indexC);
		}

		BabyShark(int size, int indexR, int indexC) {
			this.size = size;
			this.indexR = indexR;
			this.indexC = indexC;
			this.time = 0;
			this.eaten = 0;
		}

		void eatFish(int r, int c) {
			map[r][c] = 0;
			++eaten;
			if (eaten == size) {
				eaten = 0;
				++size;
			}
		}

		void moveTo(int r, int c) {
			time += timeSpend;
			map[indexR][indexC] = 0;
			indexR = r;
			indexC = c;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		StringTokenizer st;
		BabyShark bs = null;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int tmp = st.nextToken().charAt(0) - '0';
				if (tmp == 9) {
					bs = new BabyShark(i, j);
				}
				map[i][j] = tmp;
			}
		}
		while (checkAvailable(bs, bs.indexR, bs.indexC)) {
			bs.eatFish(rTo, cTo);
			bs.moveTo(rTo, cTo);
		}
		System.out.println(bs.time);
	}

	private static boolean checkAvailable(BabyShark bs, int r, int c) {
		visit = new boolean[n][n];
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> {
			return a[2] == b[2] ?
			// level이 같을 때
			// 위에 있는 것 우선, 그 다음 좌우 기준
			(a[0] == b[0] ? a[1] - b[1] : a[0] - b[0])
					// level로 정렬이 우선
					: a[2] - b[2];
		});
		int level = 0;
		pq.offer(new int[] { r, c, level });
		visit[r][c] = true;

		while (!pq.isEmpty()) {
			// 현재 들어있던 pq 길이만큼 다 빠지고나면 한 level 끝난 것
			int size = pq.size();
			// queue에 넣어주는 level은 다음 level이기에 증가시켜놓기
			++level;
			while (--size >= 0) {
				int[] tmp = pq.poll();
				int R = tmp[0];
				int C = tmp[1];
				int time = tmp[2];
				// 먹을 수 있는 생선이 나타난다면
				if (map[R][C] != 0 && bs.size > map[R][C]) {
					rTo = R;
					cTo = C;
					timeSpend = time;
					return true;
				}
				// 현재 level에서 먹을 수 있는 생선이 없다면 사방 탐색 시도
				for (int i = 0; i < 4; i++) {
					int nR = R + delta[i][0];
					int nC = C + delta[i][1];

					// 기본 사방탐색 범위 체크
					if (nR >= 0 && nR < n && nC >= 0 && nC < n
					// 방문하지 않았거나, 해당 자리 물고기보다 상어가 같거나 크다면
							&& !visit[nR][nC] && bs.size >= map[nR][nC]) {
						// 방문 처리
						visit[nR][nC] = true;
						// pq에 넣어주기
						pq.offer(new int[] { nR, nC, level });
					}
				}
			}
		}
		return false;
	}
}