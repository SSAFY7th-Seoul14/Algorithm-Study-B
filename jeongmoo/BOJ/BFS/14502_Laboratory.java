import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 14502번. 연구소
public class BOJ14502_Laboratory {
	static int n, m;
	static int max = 0;
	static int[][] map;
	
	static int[] dx = {-1, 1, 0 ,0};
	static int[] dy = {0, 0, -1, 1};	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];

		for(int i = 0; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeWall(0);
		System.out.println(max);
	}
	
	public static void find() {
		// 일단 바이러스를 전부 넣는다.
		// 넣으면서 최초 빈칸의 개수를 센다. (빈칸 0)
		int blankCount = 0;
		Queue<Point> q = new LinkedList<>();
		int[][] copyMap = new int[n][m];
		for(int i = 0; i < n ; i++) {
			for(int j = 0; j < m; j++) {
				if (map[i][j] == 2) {
					q.offer(new Point(i, j));
				} else if(map[i][j] == 0) {
					blankCount++;
				}
				copyMap[i][j] = map[i][j];
			}
		}
		
		// 바이러스가 갈수있는 개수를 카운팅
		int virusCount = 0;
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			// 4방 탐색
			for(int i=0; i<4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				if(nx<0 || ny<0 || n<=nx || m<=ny)
					continue;
				
				// 빈칸이면 큐에 넣고 바이러스 카운트를 올린다.
				if(copyMap[nx][ny] == 0) {
					q.offer(new Point(nx, ny));
					copyMap[nx][ny] = 2;
					virusCount++;
				}
			}
		}
		
		// 갈수있는 칸의 최대를 구한다.
		max = Math.max(max, blankCount-virusCount);
	}
	
	// 브루트포스로 벽 3개 만들때마다 bfs
	public static void makeWall(int idx) {
		if (3==idx) {
			find();
			return;
		}
		
		for(int i = 0; i < n ; i++) {
			for(int j = 0; j < m; j++) {
				if (map[i][j] > 0) // 1벽, 2바이러스, 3임시벽
					continue;
				
				map[i][j] = 3;
				makeWall(idx+1);
				map[i][j] = 0; 
			}
		}
	}
}