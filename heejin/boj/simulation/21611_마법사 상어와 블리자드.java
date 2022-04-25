import java.io.*;
import java.util.*;

// BOJ / G1 / 마법사 상어와 블리자드 / 1시간 45분
public class Main_21611 {
	static int N, M;
	static int map[][];
	static int d[], s[]; // 방향, 속력
	static int dx[] = { 0, -1, 1, 0, 0 }; // 1~4 상하좌우
	static int dy[] = { 0, 0, 0, -1, 1 };
	static int res; // 구슬 폭발 합
	static int xy[][]; // 좌표

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 격자 크기
		M = Integer.parseInt(st.nextToken()); // 블리자드 시행 횟수

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		d = new int[M];
		s = new int[M];
		xy = new int[N * N][2];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			d[i] = Integer.parseInt(st.nextToken());
			s[i] = Integer.parseInt(st.nextToken());
		}
		setxy(); // x,y좌표에 따른 칸 번호 생성

		simulation();
		System.out.println(res);
	}

	private static void setxy() {
		int x = N / 2, y = N / 2;
		int nx = 0, ny = 0;
		int curDir = 3;
		int d = 1; // 이동해야하는 양
		int num = 1;

		while (true) {
			for (int k = 0; k < 2; k++) {
				for (int i = 0; i < d; i++) {
					if (x == 0 && y == 0)
						return;
					nx = x + dx[curDir];
					ny = y + dy[curDir];
					xy[num][0] = nx;
					xy[num][1] = ny;
					num++;

					x = nx;
					y = ny;
				}
				curDir = nextDir[curDir];
			}
			d++;

		}

	}

	private static void simulation() {
		for (int time = 0; time < M; time++) {
			// 1. 현재 상어 위치에서 d 방향으로 s만큼 구슬 파괴시키기
			breakRing(d[time], s[time]);
			
			// 2. 구슬 파괴 후 빈칸 생긴 칸 기준으로 당기기
			moveInside();
			
			// 3. 같은거 4개 이상이면 폭발시키기
			while (true) {
				if (!bomb4()) // 더 이상 폭발시킬 것이 없을 경우
					break;
				else {
					moveInside(); // 폭발 시켰으면 폭발 후, 이동
				}

			}
			// 4. (그룹 개수, 그룹값) 2개로 쪼개기
			split2();
		}

	}

	private static void split2() {
		int newMap[][] = new int[N][N];

		// map 탐색하면서 생긴 그룹들 newMap에 삽입..
		int cnt = 1;
		int num = 1;
		int cx=0, cy =0, nx= 0, ny= 0;
		for (int i = 1; i < N * N; i++) {
			cx = xy[i][0];
			cy = xy[i][1];
			if(map[cx][cy]==0)
				break;
			if(i!=N*N-1) {
				nx = xy[i + 1][0];
				ny = xy[i + 1][1];
			}
			if (i!=N*N-1 && map[cx][cy] == map[nx][ny]) {
				cnt++;
			} else {
				if(num>=N*N)
					break;
				int newx = xy[num][0];
				int newy = xy[num][1];
				num++;
				if(num>=N*N)
					break;
				int newx2 = xy[num][0];
				int newy2 = xy[num][1];
				num++;
				newMap[newx][newy] = cnt;
				newMap[newx2][newy2] = map[cx][cy];
				cnt = 1;

			}
		}
		map = copy(newMap);
	}

	private static int[][] copy(int[][] map) {
		int data[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				data[i][j] = map[i][j];
		}
		return data;
	}

	private static boolean bomb4() {
		int x = N / 2, y = N / 2;
		int nx = 0, ny = 0;
		int curDir = 3;
		int d = 1; // 이동해야하는 양
		int cnt = 0; // 연속으로 같은거 나오는 횟수
		List<int[]> list = new ArrayList<>(); // 연속으로 같은거 나오는 좌표
		boolean flag = false; // 폭발여부
		while (true) {
			if (x == 0 && y == 0)
				break;

			for (int k = 0; k < 2; k++) {
				for (int i = 0; i < d; i++) {
					if (x == 0 && y == 0)
						return flag;
					nx = x + dx[curDir];
					ny = y + dy[curDir];

					if (cnt == 0 && map[x][y] != 0 && map[x][y] == map[nx][ny]) {
						cnt += 2;
						list.add(new int[] { x, y });
						list.add(new int[] { nx, ny });
					} else if (cnt != 0 && map[x][y] != 0 && map[x][y] == map[nx][ny]) {
						cnt++;
						list.add(new int[] { nx, ny });
					}

					if (map[x][y] != map[nx][ny]) {
						if (cnt >= 4) {
							flag = true; // 폭발 발생
							for (int j = 0; j < list.size(); j++) {
								int cur[] = list.get(j);
								int cx = cur[0], cy = cur[1];
								res += map[cx][cy];
								map[cx][cy] = 0;
							}
						}
						cnt = 0;
						list.clear();
					}

					// System.out.println("("+x+", "+y+")->"+"("+nx+", "+ny+") 이동 "+curDir +"방향");
					x = nx;
					y = ny;
				}

				curDir = nextDir[curDir];
			}
			d++;

		}
		return flag;

	}

	static int nextDir[] = { 0, 3, 4, 2, 1 }; // 다음 이동 방향

	private static void moveInside() {
		for (int i = 1; i < N * N; i++) {
			int cx = xy[i][0];
			int cy = xy[i][1];
			if (map[cx][cy] == 0) {
				int[] nxy = find(i); // i보다 큰 것중에 0 아닌 값 찾기
				int nx = nxy[0];
				int ny = nxy[1];
				map[cx][cy] = map[nx][ny];
				map[nx][ny] = 0;
			}
		}
	}

	private static int[] find(int num) {
		int nxy[] = new int[2];
		for (int i = num + 1; i < N * N; i++) {
			int cx = xy[i][0];
			int cy = xy[i][1];
			if (map[cx][cy] != 0) {
				nxy[0] = cx;
				nxy[1] = cy;
				break;
			}
		}
		return nxy;
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(map[i][j] + " ");
			System.out.println();
		}
		System.out.println();

	}

	private static void breakRing(int d, int s) {
		int nx = N / 2, ny = N / 2;
		for (int i = 0; i < s; i++) {
			nx += dx[d];
			ny += dy[d];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				break;
			map[nx][ny] = 0;
		}

	}

}
