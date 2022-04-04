import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 14442번. 벽 부수고 이동하기2
public class BOJ14442_BreakWallAndMove2 {
	static class Point {
		int x,y;
		int breakCnt;
		public Point(int x, int y, int breakCnt) {
			this.x = x;
			this.y = y;
			this.breakCnt = breakCnt;
		}
	}
	
	static int n, m, k, result = Integer.MAX_VALUE;
	static char[][] map;
	static boolean[][][] isVisited;

	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		isVisited = new boolean[n][m][k+1];
		
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
		q.offer(new Point(0, 0, k));
		isVisited[0][0][k] = true;
		
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
					
					// 벽이 아니면 그냥 이동
					if (map[nx][ny] == '0') {
						if (isVisited[nx][ny][p.breakCnt])
							continue;
						
						q.offer(new Point(nx, ny, p.breakCnt));
						isVisited[nx][ny][p.breakCnt] = true;
					} else {
						// 더 부술수 있을 때
						if (p.breakCnt > 0) {
							if (isVisited[nx][ny][p.breakCnt-1])
								continue;
							
							q.offer(new Point(nx, ny, p.breakCnt-1));
							isVisited[nx][ny][p.breakCnt-1] = true;
						}
					}
				}
			}
		}
	}
}