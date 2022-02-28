import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 17144번. 미세먼지 안녕!
public class BOJ17144_GoodbyeDust {
	static int r, c, t;
	static int[][] map;
	static int[][] change;
	static int[] airCleaner = new int[2];
	
	// 공기 청정기 방향대로. 위오아왼. 바람 역으로 빨아들임
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		change = new int[r][c];
		
		// 입력
		int cleanerCount = 0;
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				// 공기 청정기 등록
				if (map[i][j] == -1)
					airCleaner[cleanerCount++] = i;
			}
		}
		
		// 처리
		// t초 돌고
		for (int i = 0; i < t; i++) {
			spread();
		}
		
		// 총합 계산
		int result = 2; // 공기 청정기 값 -2되므로 초기값 2로 설정
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				result += map[i][j];
			}
		}
		
		
		// 출력
		System.out.println(result);
	}
	
	public static void spread() {
		// 일단 기본상태 복사
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				change[i][j] = map[i][j];
			}
		}
		
		// 확산
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				// 미세먼지가 있으면
				int dust = map[i][j];
				if(dust > 0) {
					int spreadDust = dust/5;
					int count = 0;
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						
						// 맵을 나가거나 공기청정기면 패스
						if(nx<0 || ny<0 || r<=nx || c<=ny || map[nx][ny] == -1)
							continue;
						
						change[nx][ny] += spreadDust;	// change 배열에 확산
						count++;
					}
					change[i][j] -= count*spreadDust;
				}
			}
		}
		
		// 공기청정기 작동
		for (int i = 0; i < 2; i++) {
			// 시작점 (x, y)
			int x = airCleaner[i];
			int y = 0;
			
			// 시작 방향, 순환체크 해야하는 행
			int dir = 0;
			int maxRow = airCleaner[i];
			boolean firstDust = true;
			
			// 반복
			while(true) {
				// 현재 확인할 새 좌표
				int nx = x;
				int ny = y;
				
				// 위쪽 청정기는 시계방향, 아래는 반대방향 으로 빨아들임 (역방향)
				if (i==0) {
					nx += dx[dir];
					ny += dy[dir];
					
					// 이동 불가능하면 방향 바꿔준다.
					if (nx<0 || ny<0 || maxRow<nx || c<=ny ) {
						dir++;
						continue;
					}
				} else {
					nx += dx[dir]*-1; // 아랫방향은 역방향으로 돌아야 하므로 행이동은 반대로
					ny += dy[dir];
					
					// 이동 불가능하면 방향 바꿔준다.
					if (nx<maxRow || ny<0 || r<=nx || c<=ny ) {
						dir++;
						continue;
					}
				}
				
				// 처음에는 빨아들여야 하므로 덮어씌우지않는다.
				if (firstDust)
					firstDust = false;
				else {
					// 공기 청정기를 만나면 끝까지 돌았으므로 마지막칸은 0 넣어주고 종료
					if (change[nx][ny] == -1) {
						change[x][y] = 0;
						break;
					}
					
					// 이전 값에 현재 값 넣는다.
					change[x][y] = change[nx][ny];
				}
				
				// 이전 좌표 갱신
				x = nx;
				y = ny;
			}
		}
		
		// 다시 원본 배열로 copy한다.
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				map[i][j] = change[i][j];
			}
		}
	}
}