import java.io.*;
import java.util.*;

// BOJ / 청소년 상어 / G2
public class Main_19236 {
	static class Fish {
		int num, x, y, d;
		boolean isAlive;

		public Fish(int num, int x, int y, int d, boolean isAlive) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.d = d;
			this.isAlive = isAlive;
		}
	}

	static class Shark {
		int x, y, d, sum;

		public Shark(int x, int y, int d, int sum) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.sum = sum;
		}
	}

	static int map[][];
	static Fish[] fishes;
	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int res; // 상어 먹은 물고기 번호 합 최대

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	

		map = new int[4][4];
		fishes = new Fish[17]; // 1~16까지의 물고기 관리
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1;
				map[i][j] = num;
				fishes[num] = new Fish(num, i, j, d, true);
			}
		}

		// (0,0)에 상어
		int fNum = map[0][0];
		Shark shark = new Shark(0, 0, fishes[fNum].d, fNum);
		fishes[fNum].isAlive = false;
		map[0][0] = -1; //
		dfs(shark, map, fishes);
		System.out.println(res);
	}

	private static void dfs(Shark shark, int[][] map, Fish[] fishes) {
		if (shark.sum > res) {
			res = shark.sum;
		}
		// 물고기 이동
		fishMove(shark, map, fishes);
		// 상어 이동
		for (int d = 1; d <= 3; d++) {
			int nx = shark.x + dx[shark.d] * d;
			int ny = shark.y + dy[shark.d] * d;
			// 상어 이동 처리
			if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4)
				continue;
			if (map[nx][ny] == 0) // 빈 자리이면 패스
				continue;

			// dfs 실행 전에 원본 백업
			Shark shark2 = new Shark(shark.x, shark.y, shark.d, shark.sum);
			int[][] map2 = copy(map);
			Fish[] fishes2 = copy(fishes);

			shark2.x = nx;
			shark2.y = ny;
			shark2.d = fishes[map[nx][ny]].d;
			shark2.sum = shark.sum + map[nx][ny];
			fishes2[map[nx][ny]].isAlive = false;
			map2[nx][ny] = -1;
			map2[shark.x][shark.y] = 0;
			// 상어 이동
			dfs(shark2, map2, fishes2);

		}

	}

	private static void fishMove(Shark shark, int[][] map, Fish[] fishes) {
		for (int i = 1; i < 17; i++) {
			Fish cur = fishes[i];
			if(!cur.isAlive)
				continue;
			int cnt = 0;
			int cx = cur.x, cy = cur.y;
			int nx = 0, ny = 0;
			while (true) {
				nx = cur.x + dx[cur.d];
				ny = cur.y + dy[cur.d];
				if ((nx < 0 || nx >= 4 || ny < 0 || ny >= 4) || (nx == shark.x && ny == shark.y)) {
					cur.d = (cur.d + 1) % 8;
					cnt++;
				}else
					break;
				if (cnt >= 8) {
					break;
				}
			}
			if (cnt < 8) {
				// map 바꾸기
				int tmp = map[nx][ny];
				map[nx][ny] = map[cur.x][cur.y];
				map[cur.x][cur.y] = tmp;
				
				// fish x, y 좌표 바꾸기
				
				fishes[i].x = nx;
				fishes[i].y = ny;
				if(tmp==0)
					continue;
				fishes[tmp].x = cx;
				fishes[tmp].y = cy;
			
			}

		}

	}

	private static Fish[] copy(Fish[] fishes) {
		Fish[] tmp = new Fish[17];
		for (int i = 1; i < 17; i++) {
			Fish cur = fishes[i];
			tmp[i] = new Fish(cur.num, cur.x, cur.y, cur.d, cur.isAlive);
		}
		return tmp;
	}

	private static int[][] copy(int[][] map) {
		int[][] tmp = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		return tmp;
	}
}
