import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 7569번. 토마토
class Triple {
	int x, y, h;

	public Triple(int h, int x, int y) {
		this.x = x;
		this.y = y;
		this.h = h;
	}	
}

public class BOJ7569_Tomato {
	static int n, m, h;
	static int[][][] map;
	
	static int[] dx = {-1, 1, 0, 0, 0, 0};
	static int[] dy = {0, 0, -1, 1, 0, 0};
	static int[] dh = {0, 0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken()); // 가로
		n = Integer.parseInt(st.nextToken()); // 세로
		h = Integer.parseInt(st.nextToken()); // 높이
		
		map = new int[h][n][m];
		
		for (int k = 0; k < h; k++) {
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					map[k][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
			
		// 처리
		int answer = bfs();
		
		// 출력
		System.out.println(answer);
	}
	
	public static int bfs() {
		Queue<Triple> q = new LinkedList<>();
		
		// 익은 토마토가 모두 시작지점.
		for (int k = 0; k < h; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(map[k][i][j] == 1) {
						q.offer(new Triple(k,i,j));
					}
				}
			}
		}
		
		// bfs
		int level = -1;
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Triple t = q.poll();
				for (int j = 0; j < 6; j++) {
					int nx = t.x + dx[j];
					int ny = t.y + dy[j];
					int nh = t.h + dh[j];
					
					if(nx < 0 || ny < 0 || n <= nx || m <= ny || nh < 0 || h <= nh)
						continue;
					
					if (map[nh][nx][ny] == 0) {
						q.offer(new Triple(nh, nx,ny));
						map[nh][nx][ny] = 1;
					}
				}
			}
			level++;
		}
		
		// 혹시 안익은 토마토가 있는지 확인
		for (int k = 0; k < h; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(map[k][i][j] == 0) {
						return -1;
					}
				}
			}
		}
		return level;
	}
}
