import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ20056_마법사상어와파이어볼 {

	static class Fireball {
		int r, c, weight, dir, speed;

		public Fireball(int r, int c, int weight, int speed, int dir) {
			this.r = r;
			this.c = c;
			this.weight = weight;
			this.speed = speed;
			this.dir = dir;
			// Fireball 생성시 map에다가 기록
			map[r][c].add(this);
		}

		public void move() {
			// 해당 위치 Fireball을 빼주고
			map[this.r][this.c].poll();
			int dir = this.dir;
			this.r += ((delta[dir][0] + n) * this.speed);
			this.r %= n;
			this.c += ((delta[dir][1] + n) * this.speed);
			this.c %= n;
			// 이동한 위치로 Fireball 추가
			map[this.r][this.c].add(this);
		}

	}

	static int[][] delta = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
	static int n, m;
	// fireball list는 명령 list로 fireball을 담고 있을 뿐 묶이지는 않는다.
	static LinkedList<Fireball> fbQ;
	// 지도는 fireball과 묶여있기 때문에 fireball 안으로 묶어준다.
	static LinkedList<Fireball>[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 이동 다닐 지도 크기
		n = Integer.parseInt(st.nextToken());
		// 초기 fireball 개수
		m = Integer.parseInt(st.nextToken());
		// 명령 횟수
		int k = Integer.parseInt(st.nextToken());
		fbQ = new LinkedList<>();
		map = new LinkedList[n][n];
		// 각 Fireball들이 저장되어 있을 map 초키화
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				map[i][j] = new LinkedList<>();
			}
		}
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			fbQ.add(new Fireball(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		// k번 명령
		for (int i = 0; i < k; ++i) {
			commandToMove();
		}
		int sum = 0;
		for (Fireball fireball : fbQ) {
			sum += fireball.weight;
		}
		System.out.println(sum);
		br.close();
	}

	private static void commandToMove() {
		// fireball 모두 이동시키기
		while (!fbQ.isEmpty()) {
			// buffer를 처리하듯이 우선 채워져있던 queue는 비운다.
			fbQ.poll().move();
		}
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				int locSize = map[i][j].size();
				// 1인 위치는 다음 턴을 위해서 queue를 채운다.
				if (locSize == 1) {
					fbQ.add(map[i][j].peek());
				} else if (locSize >= 2)
					addFireballs(i, j);
			}
		}
	}

	private static void addFireballs(int curR, int curC) {
		int sumWeight = 0;
		int sumSpeed = 0;
		int sumCnt = 0;
		// 처음 원소 dir 저장하기 위한 checkDir
		int checkDir = 0;
		// 같은 것만 나온다면 flag = true로 반환
		boolean flagDir = true;
		while (!map[curR][curC].isEmpty()) {
			Fireball curFire = map[curR][curC].poll();
			sumWeight += curFire.weight;
			sumSpeed += curFire.speed;
			++sumCnt;
			// 홀수, 짝수
			int dir = curFire.dir % 2;
			if (flagDir) {
				// 맨처음 dir이 무엇인지 체크하기 위한 코드
				if (checkDir == 0) {
					// 홀수이면
					if (dir == 1) {
						// 1저장
						checkDir = 1;
					} else {
						// 짝수는 2저장
						checkDir = 2;
					}
				} else {
					// 처음 상태와 다른 dir이 나타나면 false 전환
					if ((checkDir == 1 && dir == 0) || (checkDir == 2 && dir == 1)) {
						flagDir = false;
					}
				}
			}
		}
		int newWeight = sumWeight / 5;
		int newSpeed = sumSpeed / sumCnt;
		int newDir = flagDir ? 0 : 1;
		// newWeight가 0보다 클 경우만 fbQ에 넣기
		if (newWeight > 0) {
			for (int i = 0; i <= 6; i += 2) {
				fbQ.add(new Fireball(curR, curC, newWeight, newSpeed, newDir + i));
			}
		}
	}

}
