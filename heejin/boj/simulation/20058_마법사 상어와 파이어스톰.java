import java.io.*;
import java.util.*;

// BOJ / 마법사 상어와 파이어스톰 / G4 / 2시간50분
// https://www.acmicpc.net/problem/20058
public class Main_20058 {
	static int N, Q, M;
	static int map[][];
	static int L[];
	static boolean visited[][];
	static int iceCnt; // 덩어리가 차지하는 칸 개수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken()); // 파이어스톰 시전 회수
		M = (int) Math.pow(2, N); // M=2^N

		map = new int[M][M];
		visited = new boolean[M][M];
		L = new int[Q];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++)
			L[i] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < Q; i++) { // Q번의 파이어스톰 실행
			FireStorm(L[i]);
		}

		// 1.남아있는 얼음 양
		int sum = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				sum += map[i][j];
			}
		}
		// 2. 가장 큰 덩어리가 차지하는 개수
		int res = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] != 0) {
					iceCnt = 1;
					dfs(i, j);
					res = Math.max(res, iceCnt);
				}
			}
		}
		System.out.println(sum);
		System.out.println(res);
	}

	private static void dfs(int x, int y) { // 얼음 덩어리 개수
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= M || ny < 0 || ny >= M)
				continue;
			if (!visited[nx][ny] && map[nx][ny] > 0) {
				dfs(nx, ny);
				iceCnt++;

			}
		}
	}

	private static void FireStorm(int l) { // 파이어 스톰

		int K = (int) Math.pow(2, l); // K = 2^l

		int[][] copy = copy(map);
		for (int i = 0; i < M; i = i + K) { // KxK 크기의 격자 90도 회전
			for (int j = 0; j < M; j = j + K) {
				rotate(i, j, K, copy);
			}
		}
		decreaseIce(); // 회전 끝나면 얼음 줄이기 시작
	}

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	private static void decreaseIce() { // 얼음 줄이기
		List<int[]> list = new ArrayList<>();

		for (int x = 0; x < M; x++) {
			for (int y = 0; y < M; y++) {
				int cnt = 0; // 얼음이 있는 칸 수
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (nx < 0 || nx >= M || ny < 0 || ny >= M)
						continue;
					if (map[nx][ny] > 0)
						cnt++;
				}
				if (cnt < 3) // 얼음있는 칸이 3칸 미만이면
					list.add(new int[] { x, y });
			}

		}
		for (int i = 0; i < list.size(); i++) {
			int[] cur = list.get(i);
			if (map[cur[0]][cur[1]] > 0) {
				map[cur[0]][cur[1]] -= 1;
			}
		}
	}

	private static void rotate(int x, int y, int k, int[][] copy) { // 시계방향 90도 회전

		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				map[x + j][y + k - 1 - i] = copy[x + i][y + j];
			}
		}
	}

	private static int[][] copy(int[][] map) {
		int data[][] = new int[M][M];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++)
				data[i][j] = map[i][j];
		}
		return data;
	}
}
