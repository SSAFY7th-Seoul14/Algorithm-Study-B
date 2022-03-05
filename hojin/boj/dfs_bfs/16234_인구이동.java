// 2시간
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16234_인구이동 {

	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }, map, unionMap;
	static int n, L, R, day, tot, cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 1 <= 각나라 인구수 n <= 50, 1 <= L <= R <= 100 국가간 인구 차이가 L명 이상 R명 이하일 때 국경 열기
		n = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		// 이미 union된 것 체크하기위해 방문체크할 때 union No로 체크해놓기
		// 방문 여부와 연합 번호 2가지 역할을 할 변수
		unionMap = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		day = 0;
		while (true) {
			// 연합 여부 체크하기 위한 range
			int unionFail = 0;
			// 각 union마다 부여될 연합 번호
			int unionNo = 1;
			// map의 모든 부분을 돌기
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// 당일 dfs를 돌아서 visited 된 곳 있으면 이미 연합에 속했으므로 continue;
					if (unionMap[i][j] != 0) {
						continue;
					}
					cnt = 1;
					tot = map[i][j];
					// 각 점마다 4방탐색으로 dfs 국경선 체크하기
					if (!dfs(i, j, unionNo)) {
						// dfs가 실패 즉, union 실패했다면 union에 속하지 못하므로 0으로 되돌리기
						unionMap[i][j] = 0;
						// union 실패 여부 확인하기 위해 증가
						++unionFail;
						continue;
					}
					// 조정 인구 구하기
					int after = (int) Math.floor(tot / cnt);
					// 국경 공유 인구 조정, 다음 연합 번호를 위해 증가시키기
					adjust(after, unionNo++);
				}
			}
			// dfs를 n * n번 모두 실패하면
			if (unionFail == n * n)
				break;
//			for (int[] is : map) {
//				System.out.println(Arrays.toString(is));
//			}
//			System.out.println();
			// 매일 초기화 진행
			reset();
			// 날짜 증가
			++day;
		}
		System.out.println(day);
	}

	private static void reset() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				unionMap[i][j] = 0;
			}
		}
	}

	private static void adjust(int population, int unionNo) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 연합 번호에 맞는 번호들의 인구를 조절하기
				if (unionMap[i][j] == unionNo) {
					map[i][j] = population;
				}
			}
		}
	}

	private static boolean dfs(int r, int c, int unionNo) {
		// unionNo 시작
		unionMap[r][c] = unionNo;
		// dfs 시작했을 때 한번이라도 연합을 했는지 못했는지 여부 확인하기 위한 union flag
		boolean union = false;
		// 4방 탐색
		for (int i = 0; i < 4; i++) {
			int nR = r + delta[i][0];
			int nC = c + delta[i][1];
			// 범위 체크
			if (rangeCheck(nR, nC)) {
				// 사방으로의 차이값
				int diff = Math.abs(map[r][c] - map[nR][nC]);
				// 아직 방문하지 않았고
				if (unionMap[nR][nC] == 0
						// 차이값이 범위 안에 들었다면
						&& L <= diff && diff <= R) {
					// 연합 성공, 같은 연합끼리 같은 번호 부여
					unionMap[nR][nC] = unionNo;
					// 해당 범위 값 추가
					tot += map[nR][nC];
					// 몇개이지 증가
					++cnt;
					// 연합을 한번이라도 들어왔으면 true 반환
					union = true;
					dfs(nR, nC, unionNo);
				}
			}
		}
		return union;
	}

	private static boolean rangeCheck(int nR, int nC) {
		if (0 <= nR && nR < n && 0 <= nC && nC < n)
			return true;
		return false;
	}

}