import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 7562번. 나이트의 이동
public class BOJ7562_MoveKnight {
	static int result = 0;
	static int l = 0;
	static boolean[][] isVisited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for (int i = 0; i < t; i++) {
			// 입력
			l = Integer.parseInt(br.readLine());  // 한 변의 길이
			int[][] map = new int[l][l];
			isVisited = new boolean[l][l];
			
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			map[endX][endY] = 2;
			
			// 처리
			bfs(startX, startY, map);
			
			// 출력
			System.out.println(result);
		}
	}
	
	static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
	static int[] dy = {1, -1, 2, -2, 2, -2, 1, -1};	
	
	public static void bfs(int x, int y, int[][] map) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x, y));
		isVisited[x][y] = true;
		
		result = -1;
		while(!q.isEmpty()) {
			result++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point p = q.poll();
				if (map[p.x][p.y] == 2) {
					return;
				}
				
				for (int j = 0; j < 8; j++) {
					int nx = p.x + dy[j];
					int ny = p.y + dx[j];
					if (nx < 0 || ny < 0 || l <= nx || l <= ny)
						continue;
					
					if (!isVisited[nx][ny]) {
						q.offer(new Point(nx, ny));
						isVisited[nx][ny] = true;
					}
				}
			}
		}
	}	
}