import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 1194번. 달이 차오른다, 가자
public class BOJ1194_MoonIsRising {
	static class Point {
		int x, y, keys;

		public Point(int x, int y, int keys) {
			this.x = x;
			this.y = y;
			this.keys = keys;
		}
	}
	
	static int n, m;
	static char[][] map;
	static boolean[][][] isVisited;
	static Point start;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 세로
		m = Integer.parseInt(st.nextToken()); // 가로
		map = new char[n][m];
		isVisited = new boolean[n][m][1<<6];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if (map[i][j] == '0')
					start = new Point(i, j, 0);
			}
		}
				
		System.out.println(bfs());
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static int bfs() {
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		isVisited[start.x][start.y][0] = true;
		
		int time = -1;
		while(!q.isEmpty()) {
			time++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point p = q.poll();
						
				if (map[p.x][p.y] == '1') {
					return time;
				}
				
				for (int j = 0; j < 4; j++) {
					int curKeys = p.keys; 
					int nx = p.x + dx[j];
					int ny = p.y + dy[j];
					
					if (nx<0||ny<0||n<=nx||m<=ny)
						continue;
					
					if (isVisited[nx][ny][curKeys])
						continue;
					
					if (map[nx][ny] == '#')
						continue;
					
					// 열쇠를 만나면 줍는다.
					if ('a' <= map[nx][ny] && map[nx][ny] <= 'f') {
						curKeys |= 1 << (map[nx][ny]-'a');
					}
					
					// 문을 만나면 키가 있는지 확인한다.
					if ('A' <= map[nx][ny] && map[nx][ny] <= 'F') {
						if (0 == (curKeys & 1 << (map[nx][ny]-'A'))) {
							continue;
						}
					}					
					
					q.offer(new Point(nx, ny, curKeys));
					isVisited[nx][ny][curKeys] = true;
				}
			}
		}
		return -1;
	}
}

/*
3 7
f0.F...
aF....A
b....B1
answer : 9
*/