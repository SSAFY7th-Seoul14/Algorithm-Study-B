import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 17143번. 낚시왕
public class BOJ17143_KingOfFishing {
	static class Shark {
		int x, y;
		int speed, dir, size;
		boolean isDead;

		public Shark(int x, int y, int speed, int dir, int size) {
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
	
	static int result = 0;
	static int r, c;
	static int[][] map;
	static Shark[] sharkList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()); // 행
		c = Integer.parseInt(st.nextToken()); // 열
		map = new int[r][c];
		
		int m = Integer.parseInt(st.nextToken()); // 상어개수
		sharkList = new Shark[m+1];
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			map[x][y] = i; // 상어번호
			
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharkList[i] = new Shark(x, y, s, d, z); // 속력, 방향, 크기
		}
		
		goFishing();
		System.out.println(result);
	}
	
	public static void goFishing() {
		// 열 이동하면서 낚시한다.
		for (int j = 0; j < c; j++) {
			// 가장 가까운 행먼저 낚시
			for (int i = 0; i < r; i++) { 
				if (map[i][j] != 0) {
					Shark s = sharkList[map[i][j]];
					result += s.size;
					s.isDead = true;
					map[i][j] = 0;
					break;
				}
			}
			
			// 상어이동
			moveSharks();
		}
	}
	
	public static void moveSharks() {
		// 이동 하면서 배열 비워둔다.
		for (int i = 1; i < sharkList.length; i++) {
			Shark s = sharkList[i];
			if (!s.isDead)
				move(s);
		}
		
		// 배열 다시 채우면서 겹치는것들 처리
		for (int i = 1; i < sharkList.length; i++) {
			Shark s = sharkList[i];
			if (!s.isDead) {
				if (map[s.x][s.y] == 0) { // 비어있다면 상어 위치
					map[s.x][s.y] = i;
				} else { // 있으면 더 큰 상어만 살린다.
					Shark prev = sharkList[map[s.x][s.y]];
					if (s.size > prev.size) {
						prev.isDead = true;
						map[s.x][s.y] = i;
					} else {
						s.isDead = true;
					}
				}
			}
		}
	}
	
	public static void move(Shark s) {
		int x = s.x;
		int y = s.y;
		map[x][y] = 0; // 이동하면서 맵 지워둔다.
		
		int moveDist = s.speed;
		int dir = s.dir;
		while (0 < moveDist) {			
			if (dir == 1) { // 위
				if (--x < 0) {
					x = 1;
					dir = 2;
				}
			} else if (dir == 2) { // 아래 
				if (++x >= r) {
					x = r-2;
					dir = 1;
				}
			} else if (dir == 3) { // 오
				if (++y >= c) {
					y = c-2;
					dir = 4;
				}
			} else if (dir == 4) { // 왼
				if (--y < 0) {
					y = 1;
					dir = 3;
				}
			}
			moveDist--;
		}

		// 상어 이동 완료
		s.x = x;
		s.y = y;
		s.dir = dir;
	}
}