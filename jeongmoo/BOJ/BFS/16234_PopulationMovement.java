import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 16234번. 인구 이동
public class BOJ16234_PopulationMovement {
	static int n, l, r;
	static int[][] map;
	static boolean[][] isVisited;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
			
		// 처리
		int answer = 0;
		while(true) {
			boolean isMove = false;
			isVisited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!isVisited[i][j]) {
						if(bfs(i, j))
							isMove = true;
					}
				}
			}
			
			// 이동이 있으면 하루증가, 없으면 반복종료
			if (!isMove)
				break;
			answer++;
		}
		
		// 출력
		System.out.println(answer);
	}
	
	public static boolean bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x, y));
		isVisited[x][y] = true;
		
		Queue<Point> visited = new LinkedList<>();
		visited.offer(new Point(x, y));
		int total = map[x][y];		

		// bfs 하면서 열린곳 전부 방문
		while(!q.isEmpty()) {
			Point p = q.poll();
			for (int j = 0; j < 4; j++) {
				int nx = p.x + dx[j];
				int ny = p.y + dy[j];
				
				if(nx < 0 || ny < 0 || n <= nx || n <= ny)
					continue;
				
				if (!isVisited[nx][ny]) {
					int diff = Math.abs(map[p.x][p.y] - map[nx][ny]);
					if (l <= diff && diff <= r) {
						q.offer(new Point(nx,ny));
						isVisited[nx][ny] = true;
						
						visited.add(new Point(nx,ny));
						total += map[nx][ny];
					}					
				}
			}
		}
		
		// 한 곳이라도 방문하면 n빵
		if (1 < visited.size()) {
			int avg = total / visited.size();
			while(!visited.isEmpty()) {
				Point p = visited.poll();
				map[p.x][p.y] = avg;
			}
			return true;
		}
		return false;
	}
}
