import java.io.*;
import java.util.*;

// BOJ / G3 / 마법사 상어와 토네이도
public class Main_20057 {
	static int N;
	static int map[][];
	static int dir[] = { 0, 1, 2, 3 }; // 0: 상, 1: 하, 2: 좌, 3: 우
	static int nextDir[] = { 2, 3, 1, 0 }; // 현재 방향에서 다음방향
	static int dx[] = { -1, 1, 0, 0 }; // 상하좌우
	static int dy[] = { 0, 0, -1, 1 };
	static int res; // 격자 밖으로 나간 모래 양

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		simulation();
		System.out.println(res);
	}

	private static void simulation() {

		int cx = N / 2, cy = N / 2; // 시작점
		int curDir = 2; // 시작 방향은 좌측(2)
		int nx = 0, ny = 0; // 다음 칸
		int d = 1; // 이동해야 하는 칸 수
		int cnt = 0; // 이동 횟수
		int check = 0; // 이동해야 하는 칸만큼 이동을 2번 했는지

		while (true) {
			if (cx == 0 && cy == 0) { // (1,1) 도착하면 소멸됨
				break;
			}
			nx = cx + dx[curDir];
			ny = cy + dy[curDir];
			cnt++;
			move(cx, cy, nx, ny, curDir);

			if (d == cnt) {
				cnt = 0;
				curDir = nextDir[curDir];
				check++;
			}
			if (check == 2) {
				check = 0;
				d++; // 이동해야 하는 칸만큼 2번 이동 했으면 이동해야 하는 칸 수 늘리기
			}
			cx = nx;
			cy = ny;
		}
	}

	static int dsx[][] = { { 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 },
			{ -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 } }; // 모래 퍼지는 x방향
	static int dsy[][] = { { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 },
			{ 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 } }; // 모래 퍼지는 y방향
	static int rate[] = { 1, 1, 2, 2, 5, 7, 7, 10, 10 };

	private static void move(int cx, int cy, int nx, int ny, int curDir) {
		map[nx][ny] += map[cx][cy];
		map[cx][cy] = 0; // x 자리는 이동했으므로 비우기
		int sand = map[nx][ny]; // 모래 질량
		int a = sand; // a 칸에 들어갈 질량
		int sx = 0, sy = 0; // 모래 흩날리는 좌표
		for (int i = 0; i < 9; i++) { // 비율이 적혀있는 9칸에 배치
			sx = nx + dsx[curDir][i];
			sy = ny + dsy[curDir][i];
			int amount = (int) (sand * (rate[i] * 0.01));

			check(sx, sy, amount);
			a -= amount;
		}
		int ax = nx + dsx[curDir][9]; // a칸에 배치
		int ay = ny + dsy[curDir][9];
		check(ax, ay, a);
		map[nx][ny] = 0; // y 자리 모래 비우기

	}

	private static void check(int sx, int sy, int amount) {
		if (sx < 0 || sx >= N || sy < 0 || sy >= N) // 범위 벗어날 경우 res에 더하기
			res += amount;
		else { // 범위 안 벗어나면 map에 쌓기
			map[sx][sy] += amount;
		}
	}

}
