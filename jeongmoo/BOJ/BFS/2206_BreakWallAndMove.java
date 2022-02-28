import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 2206번. 벽 부수고 이동하기
public class BOJ2206_BreakWallAndMove {
	static class Point {
		int x,y;
		int isBreak;
		public Point(int x, int y, int isBreak) {
			this.x = x;
			this.y = y;
			this.isBreak = isBreak;
		}
	}
	
	static int n, m, result = Integer.MAX_VALUE;
	static char[][] map;
	static boolean[][][] isVisited;

	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		isVisited = new boolean[n][m][2];
		
		// 입력
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// 처리
		bfs();
		if (result == Integer.MAX_VALUE)
			result = -1;

		// 출력
		System.out.println(result);
	}
	
	public static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0, 0));
		isVisited[0][0][0] = true;
		
		int dist = 1;
		while(!q.isEmpty()) {
			int size = q.size();
			dist++;
			for (int i = 0; i < size; i++) {
				Point p = q.poll();
				
				if(p.x==n-1 && p.y==m-1) {
					result = dist-1;
					return;
				}
				
				for (int j = 0; j < 4; j++) {
					int nx = p.x + dx[j];
					int ny = p.y + dy[j];
					
					if (nx < 0 || ny < 0 || n <= nx || m <= ny)
						continue;
					
					// 방문한 곳이면
					if (isVisited[nx][ny][p.isBreak])
						continue;
					
					// 이미 1번 부쉈다면 
					if (p.isBreak == 1) {
						// 빈 칸만 이동가능
						if (map[nx][ny] == '0') {
							q.offer(new Point(nx, ny, 1));
							isVisited[nx][ny][1] = true;
						}
					} else {
						// 안 부쉈다면 벽도 이동가능
						if (map[nx][ny] == '0') {
							q.offer(new Point(nx, ny, 0));
							isVisited[nx][ny][0] = true;
						}
						else {
							q.offer(new Point(nx, ny, 1));
							isVisited[nx][ny][1] = true;
						}
					}
				}
			}
		}
	}
}