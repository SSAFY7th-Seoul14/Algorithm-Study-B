import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 2178번. 미로 탐색
public class BOJ2178_MazeExploration {
	// 상하좌우
	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// 처리
		Queue<Point> q = new LinkedList<>();
		map[0][0] = '2';
		q.offer(new Point(0, 0));
		
		int level = 0;
		while(!q.isEmpty()) {
			// 큐의 사이즈만큼 poll 해야 거리 1씩 구분할 수 있음. 
			int size = q.size();
			// size를 미리 캐싱하지않고 반복문에 그대로 사용하면 offer하면서 size가 늘어
			// level이 제대로 올라가지 않는다!!!
			for (int i = 0; i < size; i++) {				
				Point p = q.poll();
				if (p.x == m-1 && p.y == n-1) {
					q.clear();
					break;
				}
				
				for (int j = 0; j < 4; j++) {
					int nx = p.x + dx[j];
					int ny = p.y + dy[j];
					
					if (nx < 0 || ny < 0 || m <= nx || n <= ny)
						continue;
					
					if (map[ny][nx] == '1') {
						map[ny][nx] = '2';
						q.offer(new Point(nx, ny));
					}
				}
			}
			level++;
		}
		
		// 출력
		System.out.println(level);
	}
}