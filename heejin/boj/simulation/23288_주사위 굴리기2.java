import java.io.*;
import java.util.*;

// BOJ / 주사위 굴리기 2
public class Main_23288 {
	static int N, M, K;
	static int map[][];
	static int dice[] = { 1, 2, 3, 4, 5, 6 }; // 위, 아래, 동, 서, 남, 북
	static int dx[] = { -1, 0, 1, 0 }; // 북동남서
	static int dy[] = { 0, 1, 0, -1 };
	static int res; // 점수 총합(결과)

	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		K = Integer.parseInt(st.nextToken()); // 횟수

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		simulation();
		System.out.println(res);
	}

	static int dir = 1;// 시작 방향은 동쪽

	private static void simulation() {
		int cx = 0, cy = 0, nx = 0, ny = 0;
		for (int time = 0; time < K; time++) { // 주사위 K번 굴리기
			// 주사위 이동 (x, y 좌표 갱신)
			nx = cx + dx[dir];
			ny = cy + dy[dir];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M) { // 범위 벗어나면
				dir = (dir + 2) % 4; // 반대방향
				nx = cx + dx[dir];
				ny = cy + dy[dir];
			}
			cx = nx;
			cy = ny;
			diceRoll(dir); // 이동한 방향에 따른 주사위 배열 갱신
			
			// 현재 x,y좌표에서 얻을 수 있는 점수 구하기
			visited = new boolean[N][M];
			dfs(cx, cy);
			// 끝났으면 A, B 비교 후 다음 좌표로 이동
			if (dice[5] > map[cx][cy]) // A>B
				dir = (dir + 1) % 4; // 시계 90도
			else if (dice[5] < map[cx][cy]) // A<B
				dir = (dir - 1 + 4) % 4; // 반시계 90도

		}

	}

	private static void diceRoll(int dir) { // 이동 방향에 따른 주사위 배열 갱신
		int[] copy = copy(dice);
		if (dir == 0) { // 북쪽으로 굴리기
			dice[0] = copy[4];
			dice[1] = copy[0];
			dice[4] = copy[5];
			dice[5] = copy[1];

		} else if (dir == 1) { // 동쪽
			dice[0] = copy[3];
			dice[2] = copy[0];
			dice[3] = copy[5];
			dice[5] = copy[2];
		} else if (dir == 2) { // 남쪽
			dice[0] = copy[1];
			dice[1] = copy[5];
			dice[4] = copy[0];
			dice[5] = copy[4];
		} else { // 서쪽
			dice[0] = copy[2];
			dice[2] = copy[5];
			dice[3] = copy[0];
			dice[5] = copy[3];
		}
	}

	private static void dfs(int cx, int cy) { // 현재 좌표에서 dir을 기준으로 움직이며 점수 갱신
		visited[cx][cy]=true;
		res += map[cx][cy];
		for(int i=0;i<4;i++) {
			int nx = cx + dx[i];
			int ny = cy + dy[i];
			if(nx<0 || nx>=N || ny<0|| ny>=M) 
				continue;
			if(!visited[nx][ny] && map[cx][cy]==map[nx][ny])
				dfs(nx, ny);
		}
	}

	private static int[] copy(int[] dice) {
		int[] tmp = new int[6];
		for (int i = 0; i < 6; i++)
			tmp[i] = dice[i];
		return tmp;
	}
}
