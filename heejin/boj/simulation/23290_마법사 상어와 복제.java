import java.io.*;
import java.util.*;

// BOJ / G1 / 마법사 상어와 복제 / 2시간 30분
// https://www.acmicpc.net/problem/23290
public class Main_23290 {

	static class Fish implements Cloneable {
		int x;
		int y;
		int d;

		public Fish(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		protected Fish clone() throws CloneNotSupportedException { // 클래스 복제
			return (Fish) super.clone();
		}

	}

	static int M, S;
	static int fdx[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 }; // 물고기 이동
	static int fdy[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	static int dx[] = { 0, -1, 0, 1, 0 }; // 상어 이동
	static int dy[] = { 0, 0, -1, 0, 1 };

	static ArrayList<Fish> map[][];
	static ArrayList<Fish> list;
	static int smell[][]; // 물고기 냄새
	static int sx, sy; // 상어 위치

	static int res; // 격자에 있는 물고기 수(정답)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 물고기 수
		S = Integer.parseInt(st.nextToken()); // 마법 시행 횟수

		// 물고기 냄새
		smell = new int[4][4];
		// map 생성
		map = new ArrayList[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++)
				map[i][j] = new ArrayList<Fish>();
		}
		list = new ArrayList<Fish>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());

			list.add(new Fish(r, c, d));

		}
		
		// 상어 위치 입력
		st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken()) - 1;
		sy = Integer.parseInt(st.nextToken()) - 1;

		simulation();
		System.out.println(res);

	}

	private static void simulation() throws CloneNotSupportedException {
		for (int time = 0; time < S; time++) {
			// 1. 물고기 복제 마법
			ArrayList<Fish> copy = copy(list);
			
			// 2. 물고기 이동
			for (int i = 0; i < list.size(); i++) {
				Fish cur = list.get(i);
				cur = moveFish(cur);
			}
			// 2-1. 물고기 이동한 후 map에 배치
			setMap();
			
			// 3. 상어 연속 이동(백트래킹)
			fishNum = Integer.MIN_VALUE; // 해당 방향으로 갈 때 먹는 물고기 수 초기화
			sharkBacktracking(0); // 가장 많이 먹고, 사전순으로 적은 방향 찾기 by 중복순열
			sharkMove();
			
			// 4. 물고기 냄새 격자에서 사라짐
			smellRemove();
			
			// 5. 복제마법 map에 처리
			setCopyMap(copy);
			
			// 6. map에 있는 내용 list에 담기(물고기 개수도 세기)
			reset();

		}
	}

	private static void sharkMove() { // sharkBacktracking에서 얻은 최종 방향으로 이동하며 물고기 개수 줄이기 + 물고기 냄새 남기기

		for (int i = 0; i < 3; i++) {
			sx += dx[shkMove[i]];
			sy += dy[shkMove[i]];
			if (map[sx][sy].size() > 0) {
				smell[sx][sy] = 3;
				map[sx][sy].clear();
			}

		}

	}

	private static void reset() { // 다음 턴을 위해 map의 정보를 list에 저장하고, map 리셋
		list.clear(); // 기존 list 클리어
		int cnt = 0; // 물고기 개수
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < map[i][j].size(); k++) {
					Fish cur = map[i][j].get(k);
					list.add(cur);
					cnt++;
				}
				map[i][j].clear();
			}
		}
		res = cnt; // 정답 갱신
	}

	private static void setCopyMap(ArrayList<Fish> copy) { // 1번에서 시전한 복제마법 map에 적용시키기
		for (int i = 0; i < copy.size(); i++) {
			Fish cur = copy.get(i);
			map[cur.x][cur.y].add(cur);
		}

	}

	private static void smellRemove() { // 물고기 냄새 지우기 
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (smell[i][j] > 0) // 냄새가 남아있을 경우
					smell[i][j]--;
			}
		}

	}

	static int result[] = new int[3]; // 상어 이동 방향(임시)
	static int shkMove[] = new int[3]; // 상어 이동 방향(최종)
	static int fishNum = Integer.MIN_VALUE;

	private static void sharkBacktracking(int idx) { // 상어 이동방향 정하기 by 중복순열
		if (idx == 3) {
			int fnum = checkFish(); // 해당 방향으로 상어가 이동했을 때 물고기 수
			if(fnum==-1) // 못 가는 지역
				return;
			if (fishNum < fnum) {
				fishNum = fnum;
				for (int i = 0; i < 3; i++) {
					shkMove[i] = result[i];
				}
			}
			return;
		}

		for (int i = 1; i <= 4; i++) { // 1~4까지 중복순열
			result[idx] = i;
			sharkBacktracking(idx + 1);
		}

	}

	private static int checkFish() { // sharkBacktracking에서 정한 방향으로 갔을 때 먹는 물고기 수
		boolean visited[][] = new boolean[4][4];
		int cnt = 0; // 물고기 수
		int nx = sx, ny = sy;
		for (int i = 0; i < 3; i++) {
			nx += dx[result[i]];
			ny += dy[result[i]];

			if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
				cnt = 0;
				cnt = -1; // 범위 벗어남
				break;
			}
			if (visited[nx][ny]) // 이미 방문한 지역이면 물고기 수 책정 x
				continue;
			cnt += map[nx][ny].size();
			visited[nx][ny] = true;
		}
		return cnt;

	}

	private static void setMap() {
		for (int i = 0; i < list.size(); i++) {
			Fish cur = list.get(i);
			map[cur.x][cur.y].add(cur);
		}
	}

	private static Fish moveFish(Fish cur) {
		int nx = 0, ny = 0;
		int cnt = 0;
		while (true) {
			nx = cur.x + fdx[cur.d];
			ny = cur.y + fdy[cur.d];
			if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && // 격자 안이고, 물고기 냄새 x이고, 상어 위치 아니면
					smell[nx][ny] == 0 && !(nx == sx && ny == sy))
				break;
			cur.d = cur.d - 1; // 반시계 45도 회전
			if (cur.d <= 0)
				cur.d = 8;
			cnt++;
			if (cnt == 8) // 8바퀴 다 돌았을 경우 -> 갈 곳 x
				break;
		}
		if (cnt < 8) {
			cur.x = nx;
			cur.y = ny;
		}

		return cur;
	}

	private static ArrayList<Fish> copy(ArrayList<Fish> list) throws CloneNotSupportedException { // 리스트 복사
		ArrayList<Fish> tmp = new ArrayList<Fish>();
		for (int i = 0; i < list.size(); i++) {
			Fish cur = list.get(i);
			tmp.add(cur.clone());
		}
		return tmp;
	}
}
