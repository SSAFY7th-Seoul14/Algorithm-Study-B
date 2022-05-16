import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 2636번. 치즈
public class BOJ2636_Cheeze {
	static int n, m;
	static int[][] map;
	static int[][] visitCnt;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		Queue<int[]> cheeze = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 1)
					cheeze.offer(new int[] {i, j});
			}
		}

		// 치즈 제거 작업
		int time = 0;
		int lastCnt = cheeze.size();
		while(!cheeze.isEmpty()) {
			moveAir();
			time++;
			lastCnt = cheeze.size();
			
			int size = cheeze.size();
			for (int j = 0; j < size; j++) {
				int[] c = cheeze.poll();
				if (visitCnt[c[0]][c[1]] > 0) { // 바람이 1번이라도 닿으면
					map[c[0]][c[1]] = 0;
					continue;
				}
				cheeze.offer(c);
			}
		}
		
		System.out.println(time);
		System.out.println(lastCnt);
	}
	
	public static void moveAir() {
		visitCnt = new int[n][m];
		airCheck(0, 0); // 가장자리는 치즈가 없으므로 0,0에서 dfs
	}
	
	public static void airCheck(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || n<=nx || m<=ny) {
				continue;
			}
			
			// 바람 닿은 개수 카운팅
			visitCnt[nx][ny]++;
			
			if (visitCnt[nx][ny] > 1)
				continue;
			
			if (map[nx][ny] == 0)
				airCheck(nx, ny);
		}
	}
}
