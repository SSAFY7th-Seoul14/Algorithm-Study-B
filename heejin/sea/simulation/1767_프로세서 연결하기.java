import java.io.*;
import java.util.*;

// BOJ / 프로세서 연결하기 / SW 역량 샘플 / 2시간 +
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4suNtaXFEDFAUf
class Core {
	int x;
	int y;

	public Core(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Solution_1767 {
	static int N, maxCore, minWire; // maxCore: 최대 코어 수, minWire: 최소 전선 수
	static int map[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static List<Core> list; // 코어 위치 담을 리스트
	static int res; //전선 수(정답)
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<Core>();
			minWire = Integer.MAX_VALUE;
			maxCore = Integer.MIN_VALUE;
			res = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1 && i != 0 && i != N - 1 && j != 0 && j != N - 1) { // 벽에 안 붙은 코어
						list.add(new Core(i, j));
					}
				}
			}

			dfs(0, 0, 0); // idx: 현재 탐색하고 있는 코어, coreCnt: 연결된 코어 수, len: 전선 수
			System.out.println("#" + t + " " + res);

		}
	}

	private static void dfs(int idx, int coreCnt, int len) {
		if (idx == list.size()) { // 종료조건: 모든 코어 다 탐색 시
			if (maxCore < coreCnt) { // 더 많은 코어 연결 시
				maxCore = coreCnt;
				res = len;
			} else if (maxCore == coreCnt) { // 코어 수 같으면 전선 수 더 적은거로
				res = Math.min(res, len);
			}
			return;
		}

		for (int i = 0; i < 4; i++) { // 4방 탐색
			if (isConnect(list.get(idx), i)) { // 해당 방향으로 이동 가능한지 확인
				fill(list.get(idx), i, -1);
				dfs(idx + 1, coreCnt + 1, len + minWire);
				fill(list.get(idx), i, 0);
			}
		}
		dfs(idx + 1, coreCnt, len); // 코어 선택 안 함

	}

	private static void fill(Core core, int dir, int num) {
		minWire = 0; // 전선 수

		int nx = core.x;
		int ny = core.y;
		while (true) {
			nx += dx[dir];
			ny += dy[dir];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				break;
			map[nx][ny] = num;
			minWire++;
		}

	}

	private static boolean isConnect(Core core, int dir) { // 연결 가능한지 여부
		int nx = core.x;
		int ny = core.y;
		while (true) {
			nx += dx[dir];
			ny += dy[dir];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) // 벽 밖으로 벗어나면 연결 가능
				return true;
			if (map[nx][ny] != 0) // 중간에 전선이나 코어 만날 경우
				return false;
		}
	}

}
