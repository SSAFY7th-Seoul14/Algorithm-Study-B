import java.io.*;
import java.util.*;

// BOJ / 상어 중학교 / G2 / 2시간 50분
// https://www.acmicpc.net/problem/21609
public class Main_21609 {

	static class Blocks implements Comparable<Blocks> {
		int x, y; // 기준 블록의 좌표
		int cnt; // 블록 그룹의 총 블록 개수
		int rainbowCnt; // 무지개 블록 개수
		List<int[]> xy;
		

		public Blocks(int x, int y, int cnt, int rainbowCnt, List<int[]> blocks) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.rainbowCnt = rainbowCnt;
			this.xy = blocks;
		}

		@Override
		public int compareTo(Blocks o) {

			if (this.cnt == o.cnt) {
				if (this.rainbowCnt == o.rainbowCnt) {
					if (this.x == o.x) {
						return o.y - this.y; // 열 큰 순
					} else
						return o.x - this.x; // 행 큰 순
				} else
					return o.rainbowCnt - this.rainbowCnt;

			} else
				return o.cnt - this.cnt; // 블록 총 개수 많은 순
		}
	}

	static final int SPACE = 6; // 빈 칸
	static int N, M;
	static int map[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static boolean finish; // 오토 게임 종료 여부
	static List<Blocks> list; // 블록 그룹들
	static int res;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 격자 크기
		M = Integer.parseInt(st.nextToken()); // 색상 개수
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 시뮬레이션 시작
		while (true) {
			finish = false;
			makeBlockGroup();
			if (finish)
				break;
			removeBigBlocks();
			gravity();
			move90();
			gravity();
		}
		System.out.println(res);
	}


	private static void makeBlockGroup() { // 블록 그룹 만들기
		list = new ArrayList<>();
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				clear();
				if (!visited[i][j] && map[i][j] != -1 && map[i][j] != SPACE) {
					bfs(i, j);
				}		
			}
		}
		if (list.size() == 0) // 블록그룹 없으면 오토게임 종료
			finish = true;
	}

	private static void clear() { // 무지개 블록은 다른 그룹에서도 사용 가능하니 visited 초기화
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==0)
					visited[i][j] = false;
			}
		}
	}

	private static void bfs(int x, int y) { // 블록 그룹 찾기
		List<int[]> blocks = new ArrayList<int[]>(); // 블록 리스트들 저장

		Queue<int[]> q = new LinkedList<>();
		int color = map[x][y]; // 기준 색
		visited[x][y] = true;
		q.offer(new int[] { x, y });
		blocks.add(new int[] { x, y });

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0], cy = cur[1];
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;
				if (color == 0 && map[nx][ny]>0) {  //기준색이 아직 무지개일 경우, 만나는 최초 일반 블록을 기준색으로 잡기
					color = map[nx][ny];
				}
				if (!visited[nx][ny] && map[nx][ny] != -1 && map[nx][ny] != SPACE
						&& (color == map[nx][ny] || map[nx][ny] == 0)) {
					q.offer(new int[] { nx, ny });
					visited[nx][ny] = true;
					blocks.add(new int[] { nx, ny });
				}
			}
		}

		Collections.sort(blocks, new Comparator<int[]>() { // 기준 블록 찾기 위해 정렬
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					return o1[1] - o2[1]; // 행 같으면, 열 오름차순
				} else
					return o1[0] - o2[0]; // 행 오름차순
			}
		});

		int cnt = blocks.size(); // 블록그룹 총 개수
		int rainbowCnt = 0; // 무지개 블록 개수
		for (int i = 0; i < blocks.size(); i++) {
			int[] cur = blocks.get(i);
			int c = map[cur[0]][cur[1]];
			if (c == 0) { // 무지개 블록일 경우
				rainbowCnt++;
			}
		}
		int bx = 0, by = 0; // 블록 그룹 기준 좌표
		for (int i = 0; i < blocks.size(); i++) {
			int cur[] = blocks.get(i);
			if (map[cur[0]][cur[1]] != 0) {
				bx = cur[0];
				by = cur[1];
				break;
			}
		}
		if (cnt >= 2 && cnt - rainbowCnt >= 1) // 블록 2개 이상, 일반 블록 하나라도 있어야함
			list.add(new Blocks(bx, by, cnt, rainbowCnt, blocks));
	}

	private static void removeBigBlocks() { // 2. 가장 큰 블록 그룹 제거
		Collections.sort(list); // 블록 그룹들 기준에 맞게 정렬

		Blocks blocks = list.get(0); // 없앨 블록 그룹

		List<int[]> xy = blocks.xy; 
		for (int i = 0; i < xy.size(); i++) { 		// 해당 그룹에 해당하는 모든 블록들 제거
			int[] cur = xy.get(i);
			map[cur[0]][cur[1]] = SPACE; 
		}
		res += blocks.cnt * blocks.cnt;
	}

	private static void gravity() { // 중력 작용
		for(int j=0;j<N;j++) {
			for(int i=N-1;i>=0;i--) {
				if(map[i][j]==-1 || map[i][j]==SPACE) continue; 
				int bottom = i+1; // 빈 칸 행
				while(true) { // 빈칸 찾기
					if(bottom==N) break;
					if(map[bottom][j]==SPACE) bottom++; // 빈칸 찾기
					else
						break; 
				}
				if(bottom==i+1) continue; 
				map[bottom-1][j] = map[i][j];
				map[i][j] = SPACE; 
			}
		}
	}


	private static void move90() { // 90도 반시계 회전
		int[][] tmp = copy(map);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[N - 1 - j][i] = tmp[i][j];
			}
		}
	}

	private static int[][] copy(int[][] map) { // 배열 복사
		int[][] tmp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				tmp[i][j] = map[i][j];
		}
		return tmp;
	}
}
