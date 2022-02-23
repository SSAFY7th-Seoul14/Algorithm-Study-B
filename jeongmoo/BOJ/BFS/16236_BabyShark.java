import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 16236번. 아기 상어
public class BOJ16236_BabyShark {
	// 입력 데이터
	static int n;
	static int[][] map;
	
	// 처리 데이터
	static Point shark;
	static int sharkSize = 2;
	static int eatFishCount = 0;
	
	// 위 왼 오 아
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		// 입력
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				
				// 시작 위치 설정
				if (num == 9) {
					shark = new Point(i, j);
					map[i][j] = 0;
				}
			}
		}
		
		// 처리
		int result = bfs();

		// 출력
		System.out.println(result);
	}
	
	// 더 갈 곳이 있는지 체크
	public static boolean checkFish() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] != 0 && map[i][j] < sharkSize)
					return true;
			}
		}
		return false;
	}
	
	// bfs 반복
	public static int bfs() {
		// 가능한 물고기 중 위쪽 먼저(x값 낮은순), 같으면 왼쪽 먼저(y값 낮은순)
		PriorityQueue<Point> pq = new PriorityQueue<>((p1, p2)->p1.x!=p2.x? p1.x-p2.x : p1.y-p2.y);
		boolean[][] isVisited = new boolean[n][n];	// 방문체크
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(shark.x, shark.y));
		
		int answer = 0;
		int time = -1;
		while(!q.isEmpty()) {
			int size = q.size();
			time++;
			
			// 더 이상 먹을 수 있는 물고기가 없다면
			if(!checkFish())
				break;
			
			for (int i = 0; i < size; i++) {
				Point p = q.poll();
				
				// 사방탐색
				for (int j = 0; j < 4; j++) {
					int nx = p.x + dx[j];
					int ny = p.y + dy[j];
					
					// 범위 체크
					if (nx < 0 || ny < 0 || n <= nx || n <= ny)
						continue;
					
					// 방문 안한 곳만 방문
					if (!isVisited[nx][ny]) {
						isVisited[nx][ny] = true;
						
						int num = map[nx][ny];
						if (num == sharkSize || num == 0)	// 상어크기와 같거나 빈 칸이면 방문
							q.offer(new Point(nx, ny));
						else if (num < sharkSize) 			// 작으면 물고기들을 우선순위 큐에 넝흠
							pq.offer(new Point(nx, ny));
					}					
				}
			}
			
			// 우선순위 큐에 뭔가 있다면 (물고기 찾았다면)
			if (!pq.isEmpty()) {
				Point p = pq.poll();
				
				map[p.x][p.y] = 0;		// 먹고 빈칸으로 만듬
				shark.move(p.x, p.y);	// 상어 위치 이동
				
				// 물고기 하나 먹고 사이즈 조절
				if (++eatFishCount == sharkSize) {
					eatFishCount = 0;
					sharkSize++;
				}
				
				// 우선순위 큐 및 방문체크 배열 초기화
				pq.clear();
				isVisited = new boolean[n][n];
				
				// 큐를 초기화하고 상어 위치만 넣고 다시 시작한다.
				q.clear();
				q.offer(new Point(shark.x, shark.y));
				
				// 혹시 더 이상 방문할 곳이 없는 경우, time값은 계속 증가할 수 있으므로 마지막 물고기 먹은 시간 저장
				answer = time + 1;
			}
		}
		
		return answer;
	}
}