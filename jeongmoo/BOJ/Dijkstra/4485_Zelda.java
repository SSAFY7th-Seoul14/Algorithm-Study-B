import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 4485번. 녹색 옷 입은 애가 젤다지?
public class BOJ4485_Zelda {
	static class Point implements Comparable<Point> {
		int x, y, dist;

		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Point o) {
			return this.dist - o.dist;
		}		
	}
	
	static int n;
	static int[][] data;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = 1;
		while(true) {
			n = Integer.parseInt(br.readLine());
			if (n==0)
				break;
			
			data = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					data[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 탐색
			int result = dijkstra();
			
			// 출력
			System.out.printf("Problem %d: %d\n", tc++, result);			
		}
	}
	
	public static int dijkstra() {
		int[][] distance = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(distance[i], Integer.MAX_VALUE);			
		}
		distance[0][0] = data[0][0];
		
		boolean[][] visited = new boolean[n][n];
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(0, 0, distance[0][0]));
		
		while(!pq.isEmpty()) {
			Point cur = pq.poll();
			
			if(visited[cur.x][cur.y])
				continue;
			visited[cur.x][cur.y] = true;
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (nx<0 || ny<0 || n<=nx || n<=ny)
					continue;
				
				if (!visited[nx][ny] && 
					distance[nx][ny] > distance[cur.x][cur.y] + data[nx][ny]) {
					distance[nx][ny] = distance[cur.x][cur.y] + data[nx][ny];
					pq.offer(new Point(nx, ny, distance[nx][ny]));
				}
			}
		}
		
		return distance[n-1][n-1];
	}
}