import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15683_감시 {

	static int[][] map, delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }, cctvLoc;
	static int n, m, cctvCnt, min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 세로 1< = n <= 8
		n = Integer.parseInt(st.nextToken());
		// 가로 1 <= m <= 8
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];

		// cctv의 위치 저장해놓을 배열 8개를 넘지 않는다
		cctvLoc = new int[8][];

		// 이하 코드 주석들은 말도 안되는 로직 설계의 처절한 흔적...
//		cctvLoc = new ArrayList[6];
//		for (int i = 0; i <= 5; i++) {
//			cctvLoc[i] = new ArrayList<int[]>();
//		}

		min = Integer.MAX_VALUE;

		cctvCnt = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int temp = Integer.parseInt(st.nextToken());
				switch (temp) {
				case 0:
				case 6:
					break;
				default:
//					cctvLoc[temp].add(new int[] { i, j });
					// 각 cctv가 위치한 location과 몇번 cctv인지 저장. r, c, cctvNo
					cctvLoc[cctvCnt++] = new int[] { i, j, temp };
					break;
				}
				map[i][j] = temp;
			}
		}
		br.close();

		// cctv5 일단 세팅해놓으려 했던 흔적...
//		for (int i = 0; i < cctvLoc[5].size(); i++) {
//			cctv((int[]) cctvLoc[5].get(i), 0, 5);
//		}

		// cctv의 중요도는 숫자가 커질 수록 높다. 따라서 큰 번호가 더 나중에 비추도록 함으로 영역 확보 안정성 가져가기
		Arrays.sort(cctvLoc, 0, cctvCnt, (a, b) -> {
			return a[2] - b[2];
		});

		// dfs를 이용해 완전 탐색 수행
		for (int i = 0; i < 4; i++) {
			dfs(0, i);
		}

		System.out.println(min);

	}

	private static void dfs(int nthCctv, int mode) {
		if (nthCctv == cctvCnt) {
			countZero();
			return;
		}

		// 여기가 문제였음. 2번재 설계때 같은 번호는 반드시 같은 mode로 동작하도록 잘못 짠 코드...
//		for (int i = 0; i < cctvLoc[cctvNo].size(); i++) {
//			cctv((int[]) cctvLoc[cctvNo].get(i), mode, cctvNo);
//		}
		
		int nthIlluminating = nthCctv;
		cctv(cctvLoc[nthCctv], mode, nthIlluminating);
		for (int i = 0; i < 4; i++) {
			dfs(nthCctv + 1, i);
		}
		reset(nthIlluminating);
	}

	private static void countZero() {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0)
					++cnt;
			}
		}
		if (cnt < min) {
			min = cnt;
		}
	}

	private static void reset(int nthIlluminating) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == nthIlluminating) {
					map[i][j] = 0;
				}
			}
		}
	}

	private static void cctv(int[] locInfo, int mode, int nThIlluminating) {
		int cctvLocR = locInfo[0];
		int cctvLocC = locInfo[1];
		int cctvNo = locInfo[2];
		for (int i = 0; i < 4; i++) {
			if (modeNo(cctvNo, mode, i))
				continue;
			int nR = cctvLocR + delta[i][0];
			int nC = cctvLocC + delta[i][1];

			while (rangeCheck(nR, nC)) {
				if (map[nR][nC] == 0) {
					map[nR][nC] = nThIlluminating;
				}
				nR += delta[i][0];
				nC += delta[i][1];
			}
		}
	}

	private static boolean modeNo(int cctvNo, int i, int j) {
		switch (cctvNo) {
		case 1:
			if (j != i)
				return true;
			break;
		case 2:
			if (j == i || j == (i + 2) % 4)
				return true;
			break;
		case 3:
			if (j == i || j == (i + 1) % 4)
				return true;
			break;
		case 4:
			if (j == i)
				return true;
			break;
		case 5:
			return false;
		}
		return false;
	}

	private static boolean rangeCheck(int r, int c) {
		if (0 <= r && r < n && 0 <= c && c < m && map[r][c] != 6)
			return true;
		return false;
	}

	// 처음에는 이렇게 각 cctv 별로 많이 비출수 있는 놈부터 최대로 많이 비추는 영역을 체크한 후에 cctv 비추는 영역 표시를 하려고
	// 했습니다만.. 잘못된 설계
//	private static void cctv(int[] loc, int cctvNo) {
//		int mode = cctvCheck(loc, cctvNo);
//		cctv(loc, mode, cctvNo);
//	}
//
//	private static int cctvCheck(int[] loc, int cctvNo) {
//		int max = Integer.MIN_VALUE;
//		int mode = 0;
//		for (int i = 0; i < 4; i++) {
//			int cnt = 0;
//			for (int j = 0; j < 4; j++) {
//				if (modeNo(cctvNo, i, j))
//					continue;
//				int nR = loc[0] + delta[j][0];
//				int nC = loc[1] + delta[j][1];
//
//				while (rangeCheck(nR, nC)) {
//					if (map[nR][nC] == 0)
//						++cnt;
//					nR += delta[j][0];
//					nC += delta[j][1];
//				}
//			}
//			if (cnt > max) {
//				max = cnt;
//				mode = i;
//			}
//		}
//		return mode;
//	}

}