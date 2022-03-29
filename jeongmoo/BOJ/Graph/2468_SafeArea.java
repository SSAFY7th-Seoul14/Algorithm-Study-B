import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2468번. 안전 영역
public class BOJ2468_SafeArea {
	static int n;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		int count = 0;
		for (int i = 0; i <= 100; i++) {
			visited = new boolean[n][n];
			count = 0;
			
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if (!visited[j][k]) {
						if (dfs(j, k, i))
							count++;
					}
				}
			}
			
			result = Math.max(result, count);
		}
		
		System.out.println(result);
	}
	
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	public static boolean dfs(int x, int y, int height) {
		visited[x][y] = true;
		if (map[x][y] <= height)
			return false;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx<0 || ny<0 || n<=nx || n<=ny)
				continue;
			
			if (visited[nx][ny])
				continue;
			
			if (map[nx][ny] >= height)
				dfs(nx, ny, height);
		}
		
		return true;
	}
}
