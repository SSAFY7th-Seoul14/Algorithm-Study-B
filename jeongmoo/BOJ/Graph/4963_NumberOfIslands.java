import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 4963번. 섬의 개수
public class BOJ4963_NumberOfIslands {
	static int[][] map;
	static boolean[][] isVisited;
	
	static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			if (w==0 && h==0)
				break;
			
			map = new int[h][w];
			isVisited = new boolean[h][w];
			
			// 입력
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 처리
			int count = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (!isVisited[i][j] && map[i][j] != 0) {
						dfs(i, j, w, h);
						count++;
					}
				}
			}
			
			// 출력
			System.out.println(count);
		}
	}
	
	public static void dfs(int row, int col, int w, int h) {
		isVisited[row][col] = true;
		
		for (int i = 0; i < 8; i++) {
			int nx = row + dx[i];
			int ny = col + dy[i];
			
			if(nx < 0 || ny < 0 || h <= nx || w <= ny)
				continue;
			
			if (!isVisited[nx][ny] && map[nx][ny] != 0)
				dfs(nx, ny, w, h);
		}
	}
}
