import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 7576번. 토마토
public class BOJ7576_Tomato {
	static int n, m;
	static int[][] map;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken()); // 가로
		n = Integer.parseInt(st.nextToken()); // 세로
		
		map = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
			
		// 처리
		int answer = bfs();
		
		// 출력
		System.out.println(answer);
	}
	
	public static int bfs() {
		Queue<Point> q = new LinkedList<>();
		
		// 익은 토마토가 모두 시작지점.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j] == 1) {
					q.offer(new Point(i,j));
				}
			}
		}
		
		// bfs
		int level = -1;
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point p = q.poll();
				for (int j = 0; j < 4; j++) {
					int nx = p.x + dx[j];
					int ny = p.y + dy[j];
					
					if(nx < 0 || ny < 0 || n <= nx || m <= ny)
						continue;
					
					if (map[nx][ny] == 0) {
						q.offer(new Point(nx,ny));
						map[nx][ny] = 1;
					}
				}
			}
			level++;
		}
		
		// 혹시 안익은 토마토가 있는지 확인
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j] == 0) {
					return -1;
				}
			}
		}
		return level;
	}
}
