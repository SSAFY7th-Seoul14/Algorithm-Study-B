import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준1012번. 유기농 배추
public class BOJ1012_OrganicCabbage {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for (int i = 0; i < t; i++) {
			// 입력
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[n][m];
			for (int j = 0; j < k; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[y][x] = 1;
			}
			
			int count = 0;
			for (int j = 0; j < n; j++) {
				for (int l = 0; l < m; l++) {
					if (map[j][l] == 1) {
						dfs(j, l, n, m, map);
						count++;
					}
				}
			}
			
			System.out.println(count);
		}
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void dfs(int row, int col, int n, int m, int[][] map) {
		map[row][col] = 2;
		
		for (int j = 0; j < 4; j++) {
			int nx = row + dx[j];
			int ny = col + dy[j];
			if (nx < 0 || ny < 0 || n <= nx || m <= ny)
				continue;
			
			if (map[nx][ny] == 1)
				dfs(nx, ny, n, m, map);
		}
	}
	
}