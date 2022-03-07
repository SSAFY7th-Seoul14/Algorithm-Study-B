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
					// 각 cctv가 위치한 location과 몇번 cctv인지 저장. r, c, cctvNo
					cctvLoc[cctvCnt++] = new int[] { i, j, temp };
					break;
				}
				map[i][j] = temp;
			}
		}
		br.close();

		// cctv의 중요도는 숫자가 커질 수록 높다. 따라서 큰 번호가 더 나중에 비추도록 함으로 영역 확보 안정성 가져가기
		// 5가 먼저 비추고 2가 비춘다면 map을 reset 할 때 2가 비췄던 자리 먼저 갱신하면서 5의 흔적이 남지 않고 0으로 바뀌어버려서 오류가 발생한다.
		Arrays.sort(cctvLoc, 0, cctvCnt, (a, b) -> {
			return a[2] - b[2];
		});

		// dfs를 이용해 완전 탐색 수행
		for (int i = 0; i < 4; i++) {
			// 0번째 카메라부터 dfs 수행
			dfs(0, i);
		}

		System.out.println(min);

	}

	private static void dfs(int nthCctv, int mode) {
		if (nthCctv == cctvCnt) {
			countZero();
			return;
		}

		// n번째 카메라가 비추는 영역을 구분해주기 위해 7을 더한다.
		int nthCctvBeam = nthCctv + 7;
		// n번째 카메라 작동시키기
		cctvOperation(cctvLoc[nthCctv], mode, nthCctvBeam);
		for (int i = 0; i < 4; i++) {
			dfs(nthCctv + 1, i);
		}
		reset(nthCctvBeam);
	}

	private static void countZero() {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0)
					++cnt;
			}
		}
		min = Math.min(min, cnt);
	}

	private static void reset(int nthCctvBeam) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == nthCctvBeam) {
					map[i][j] = 0;
				}
			}
		}
	}

	private static void cctvOperation(int[] locInfo, int mode, int nthCctvBeam) {
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
					map[nR][nC] = nthCctvBeam;
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

}