import java.io.*;
import java.util.*;

public class Main_20056 {
	static class FireBall {
		int r, c, m, s, d;

		public FireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	static int N, M, K;
	static int res;
	static ArrayList<FireBall> map[][];
	static List<FireBall> list;
	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 격자 크기
		M = Integer.parseInt(st.nextToken()); // 파이어볼 개수
		K = Integer.parseInt(st.nextToken()); // 이동 명령 횟수
		map = new ArrayList[N][N]; // 파이어볼들 저장하는 격자

		for (int i = 0; i < N; i++) { // 격자 초기화
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<FireBall>();
			}
		}
		list = new ArrayList<>(); // 파이어볼 리스트

		// FireBall 정보 입력받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1; // (1,1)부터 시작하는 것을 (0,0) 시작으로 바꿈
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list.add(new FireBall(r, c, m, s, d));
		}

		simulation(); // 시뮬레이션 시작

		res = getResult(); // 남아있는 파이어볼 질량 합 구하기
		System.out.println(res);
	}

	private static int getResult() {
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).m;
		}
		return sum;

	}

	private static void simulation() {
		for (int time = 0; time < K; time++) { // K번동안 명령
			// 1. 파이어볼 이동
			for (int i = 0; i < list.size(); i++) {
				FireBall cur = list.get(i);
				// r, c 이동
				cur.r = (cur.r + N + dx[cur.d] * (cur.s % N)) % N;
				cur.c = (cur.c + N + dy[cur.d] * (cur.s % N)) % N;

				map[cur.r][cur.c].add(cur); // 이동후 map에 배치

			}

			// 2. 이동끝난 후 2개 이상의 파이어볼 있는 칸 체크
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].size() > 1) {
						divide4(i, j);
					}
				}
			}

			// map에 배치한거 list에 담기
			list.clear();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].size() != 0) {
						for (int k = 0; k < map[i][j].size(); k++) {
							FireBall cur = map[i][j].get(k);
							list.add(cur);
						}
					}
				}
			}
			// map 내용들 list에 다 담았으면, 다음 턴을 위해 map clear
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].size() > 0)
						map[i][j].clear();
				}
			}
		}
	}

	private static void divide4(int x, int y) { // 4방향으로 나눠지기
		int sumM = 0, sumS = 0; // 질량합, 속력합
		boolean flag = true; // 방향 전부 홀,짝이면 true, 아니면 false

		for (int i = 0; i < map[x][y].size(); i++) {
			FireBall cur = map[x][y].get(i);
			sumM += cur.m;
			sumS += cur.s;
			if (i != map[x][y].size() - 1) {
				FireBall next = map[x][y].get(i + 1);
				if (cur.d % 2 != next.d % 2) // 홀, 짝 섞여있을 경우 flag=false
					flag = false;
			}
		}

		int newM = 0, newS = 0;
		newM = sumM / 5;
		newS = sumS / map[x][y].size();

		map[x][y].clear();
		setMap(newM, newS, flag, x, y);

	}

	private static void setMap(int newM, int newS, boolean flag, int x, int y) {
		if (newM != 0) { // 파이어볼 질량 0 아닐때만 살아남으므로
			if (flag) {
				for (int i = 0; i < 4; i++) {
					FireBall newFireBall = new FireBall(x, y, newM, newS, 2 * i);
					map[x][y].add(newFireBall);
				}
			} else {
				for (int i = 0; i < 4; i++) {
					FireBall newFireBall = new FireBall(x, y, newM, newS, 2 * i + 1);
					map[x][y].add(newFireBall);
				}
			}
		}
	}

}
