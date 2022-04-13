import java.io.*;
import java.util.*;

// BOJ / 낚시왕 / G2 / 80분
// https://www.acmicpc.net/problem/17143
public class Main_17143 {
	static class Fish implements Comparable<Fish> {
		int r, c, s, d, z; 

		public Fish(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(Fish o) { // 크기 내림차 순으로 정렬
			return o.z - this.z;
		}
	}

	static int R, C, M;
	static Fish map[][];
	static int dx[] = { 0, -1, 1, 0, 0 }; // 1:위, 2:아래, 3:오른쪽, 4:왼쪽
	static int dy[] = { 0, 0, 0, 1, -1 };
	static int reverse[] = { 0, 2, 1, 4, 3 }; // 반대방향
	static List<Fish> list;
	static int res; // 낚시왕이 잡은 물고기 크기 합(정답)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new Fish[R + 1][C + 1];

		list = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[r][c] = new Fish(r, c, s, d, z);
			list.add(new Fish(r, c, s, d, z));
		}

		simulation();
		System.out.println(res);
	}

	private static void simulation() {
		for (int t = 1; t <= C; t++) { // 낚시왕의 위치
			// 1. 현재 열에서 잡을 수 있는 물고기 있는지
			fishDown(t);
			// 2. 물고기 이동
			fishMove();
			// 3. 이동한 물고기들 map에 배치
			setMap();
		}
	}

	private static void setMap() { // map에다가 list에 저장된 fish들 배치
		mapClear(); // map 배치 전 정리
		Collections.sort(list); // 물고기들을 크기 순으로 정렬
		for(int i=0;i<list.size();i++) {
			Fish cur = list.get(i);
			if(map[cur.r][cur.c]==null) { //비어있을 때만 배치 (크기 순 정렬이기 때문)
				map[cur.r][cur.c]=cur;
			}
		}
		
		reset(); //다음 턴을 위해 현재 map의 정보를 list에 저장
	}

	private static void reset() { //map의 정보를 list에 저장
		list.clear();
		for(int i=1;i<=R;i++) {
			for(int j=1;j<=C;j++) {
				if(map[i][j]!=null) {
					list.add(map[i][j]);
				}
			}
		}	
	}

	private static void mapClear() { //map 정리
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (map[i][j] != null)
					map[i][j] = null;
			}
		}
	}

	private static void fishMove() { // 물고기 이동
		for (int i = 0; i < list.size(); i++) {
			Fish cur = list.get(i);
			// 현재 상어를 이동 방향으로 속력만큼 이동
			int nx = cur.r, ny = cur.c;
			int moveCnt = cur.s; // 움직여야 하는 칸 수(속력)
			while (moveCnt > 0) {
				if ((nx <= 1 && cur.d==1) || (nx >= R && cur.d==2)
						|| (ny <= 1 && cur.d==4) || (ny >= C && cur.d==3)) {
					cur.d = reverse[cur.d];
				}
				nx += dx[cur.d];
				ny += dy[cur.d];
				
				moveCnt--;
			}
			cur.r = nx;
			cur.c = ny;
		}
	}

	private static void fishDown(int c) { //낚시왕의 현재 열에서 잡을 수 있는 물고기
		for (int i = 1; i <= R; i++) {
			if (map[i][c] != null) { // 상어가 있다면
				res += map[i][c].z;
				map[i][c] = null; // 비우기
				reset();//map에 변화가 일어났으니 list도 변경
				break;
			}
		}

	}
}
