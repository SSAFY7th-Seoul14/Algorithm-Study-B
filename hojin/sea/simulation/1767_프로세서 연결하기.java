import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Point {
	int r, c;

	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class SWEA1767_프로세서연결하기 {

	static char[][] processor;
	static boolean[][] isConnected;
	static int n, min, coreCnt, maxConnectedCoreCnt, length;
	static int[][] delta = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
	static Point[] cores;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; ++tc) {
			n = Integer.parseInt(br.readLine().trim());
			processor = new char[n][n];
			isConnected = new boolean[n][n];
			// core의 개수 coreCnt
			coreCnt = 0;
			// core들의 index가 담길 cores
			cores = new Point[12];
			for (int i = 0; i < n; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; ++j) {
					char inp = st.nextToken().charAt(0);
					// core 담기
					if (inp == '1') {
						cores[coreCnt++] = new Point(i, j);
						// 가장 자리에 있는 core들은 미리 연결 처리
						if (i == 0 || i == n - 1 || j == 0 || j == n - 1) {
							isConnected[i][j] = true;
						}
					}
					processor[i][j] = inp;
				}
			}
			// 모든 core들을 처리했을 때 연결되어 있는 최대 개수를 담아줄 maxConnectedCoreCnt
			maxConnectedCoreCnt = 0;
			// 전선 길이 담을 length
			length = 0;
			dfs(0, 0);
			bw.write(String.format("#%d %d%n", tc, min));
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int nth, int connectedCoreCnt) {
		// 여기가 prunning의 핵심
		// 현재 연결된 core수 + 남은 core수(core전체개수 - 현재 사용된 n개 core)가 현재 core 최대개수보다 작은 경우는
		// 따져보지도 않는다. 여기가 BT.
		if (connectedCoreCnt + (coreCnt - nth) < maxConnectedCoreCnt)
			return;
		// coreCnt개만큼 depth가 들어왔을 경우
		if (nth == coreCnt) {
			// 최대 연결 개수 갱신
			if (connectedCoreCnt > maxConnectedCoreCnt) {
				maxConnectedCoreCnt = connectedCoreCnt;
				// 이 경우 min값도 갱신해줘야 한다. 최대 연결 개수가 갱신 됨에 따라 이전 개수에서의 min값은 무시
				min = Integer.MAX_VALUE;
			}
			// 최대 연결 개수와 현재 연결 개수가 같을 경우는 min값을 따져줘봐야 한다.
			if (connectedCoreCnt == maxConnectedCoreCnt)
				min = Math.min(length, min);
			return;
		}
		int r = cores[nth].r;
		int c = cores[nth].c;
		// 이미 연결되어 있는 core라면 벽에 붙어있는 core이므로 바로 다음 depth로 이동
		if (isConnected[r][c]) {
			dfs(nth + 1, connectedCoreCnt + 1);
			return;
		}
		isConnected[r][c] = true;
		// 4방향에 대해서
		for (int i = 0; i < 4; ++i) {
			// 연결이 가능한지 확인
			if (checkConnect(r, c, i)) {
				// 연결 작업 진행
				connect(r, c, i);
				// 다음 depth로 이동
				dfs(nth + 1, connectedCoreCnt + 1);
				// 재귀 빠져나오고 연결 해제
				disconnect(r, c, i);
			}
		}
		// 4방향 모두 탐색하지 않고 depth 넘어가는 경우도 포함해야 함
		dfs(nth + 1, connectedCoreCnt);
		isConnected[r][c] = false;
	}

	private static boolean checkConnect(int r, int c, int dir) {
		int nR = r + delta[dir][0];
		int nC = c + delta[dir][1];
		while (rangeCheck(nR, nC)) {
			// 범위안에서 core가 있거나 전선이 깔려있다면
			if (processor[nR][nC] == '1' || isConnected[nR][nC]) {
				return false;
			}
			nR += delta[dir][0];
			nC += delta[dir][1];
		}
		return true;
	}

	private static void connect(int r, int c, int dir) {
		int nR = r + delta[dir][0];
		int nC = c + delta[dir][1];
		while (rangeCheck(nR, nC)) {
			// 연결처리해주면서(전선을 깔면서)
			isConnected[nR][nC] = true;
			// 전선 길이 증가
			++length;
			nR += delta[dir][0];
			nC += delta[dir][1];
		}
	}

	private static void disconnect(int r, int c, int dir) {
		int nR = r + delta[dir][0];
		int nC = c + delta[dir][1];
		while (rangeCheck(nR, nC)) {
			// connect와 반대 작업
			isConnected[nR][nC] = false;
			--length;
			nR += delta[dir][0];
			nC += delta[dir][1];
		}
	}

	private static boolean rangeCheck(int nR, int nC) {
		if (0 <= nR && nR < n && 0 <= nC && nC < n)
			return true;
		return false;
	}

}
