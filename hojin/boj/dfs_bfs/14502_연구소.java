// 1시간
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ14502 {

	static char[][] map;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int n, m, cnt, max = 0;
	static LinkedList<int[]> virusInfo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		virusInfo = new LinkedList<int[]>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				char tmp = st.nextToken().charAt(0);
				if (tmp == '2')
					virusInfo.add(new int[] { i, j });
				map[i][j] = tmp;
			}
		}
		br.close();
		// 8*8 지도에서 64C3해도 약 40000밖에 안되니까 충분히 돌릴만하다
		buildWall(0);
		// 입력 완료
		System.out.println(max);
	}

	// 2 입장에서 2 채워나가는 함수 짜기 call stack을 이용했기 때문에 dfs
	private static void spreadVirus(int r, int c) {
		// 기저조건
		if (map[r][c] == '1' || map[r][c] == '3' || map[r][c] == '4') {
			return;
		}
		// 0 이면 3으로 바꾸고
		// 2면 4로 바꾼다 restore 과정 때 기존의 것과 비교해줘야 함
		if (map[r][c] == '0') {
			map[r][c] = '3';
		}
		if (map[r][c] == '2') {
			map[r][c] = '4';
		}
		// 사방 탐색
		for (int i = 0; i < 4; i++) {
			int nR = r + delta[i][0];
			int nC = c + delta[i][1];
			if (nR >= 0 && nR < n && nC >= 0 && nC < m) {
				spreadVirus(nR, nC);
			}
		}
	}

	// 다음으로 넘어가야 하므로 다시 0으로 바꿔주기
	private static void restore() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == '3') {
					map[i][j] = '0';
				}
				if (map[i][j] == '4') {
					map[i][j] = '2';
				}
			}
		}
	}

	private static void buildWall(int builtCnt) {
		// 0중 3개를 1로 만들고서
		if (builtCnt == 3) {
			// 2가 퍼져나가는 형태일 때
			for (int[] vIndex : virusInfo) {
//				spreadVirus(vIndex[0], vIndex[1]);
				spreadVBFS(vIndex[0], vIndex[1]);
			}
			// 바이러스 다 퍼지고서 0개수 세어주기
			cnt = 0;
			// 쭉 돌면서 0count
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == '0') {
						cnt++;
					}
				}
			}
			// max와 비교해가며 갱신
			if (cnt > max)
				max = cnt;
			restore();
			return;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == '0') {
					map[i][j] = '1';
					buildWall(builtCnt + 1);
					map[i][j] = '0';
				}
			}
		}
	}

	// 다시 queue를 이용해서 구현해보기
	private static void spreadVBFS(int r, int c) {
		LinkedList<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { r, c });

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nR = cur[0] + delta[i][0];
				int nC = cur[1] + delta[i][1];
				if (nR >= 0 && nR < n && nC >= 0 && nC < m) {
					if (map[nR][nC] == '0') {
						q.offer(new int[] { nR, nC });
						map[nR][nC] = '3';
					} else if (map[nR][nC] == '2') {
						map[nR][nC] = '4';
					}
				}
			}
		}
	}

}