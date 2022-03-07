import java.io.InputStreamReader;
import java.io.BufferedReader;

// 백준 10026번. 적록색약
public class BOJ10026_RedGreenWeakness {
	static int n;
	static char[][] map, map2;
	static boolean[][] isVisitedNormal;
	static boolean[][] isVisitedWeakness;
	
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		map2 = new char[n][n];
		isVisitedNormal = new boolean[n][n];
		isVisitedWeakness = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 'G')
					map2[i][j] = 'R';
				else
					map2[i][j] = map[i][j];
			}
		}
		
		// 처리
		int resultNormal = 0;
		int resultWeakness = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!isVisitedNormal[i][j]) {
					dfs(i, j, map[i][j], false);
					resultNormal++;
				}
				if (!isVisitedWeakness[i][j]) {
					dfs(i, j, map2[i][j], true);
					resultWeakness++;
				}
			}
		}
		
		// 출력
		System.out.println(resultNormal + " " + resultWeakness);
	}
	
	public static void dfs(int x, int y, char color, boolean isWeakenss) {
		if (isWeakenss)
			isVisitedWeakness[x][y] = true;
		else
			isVisitedNormal[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx<0 || ny<0 || n<=nx || n<=ny)
				continue;
			
			if (isWeakenss) {
				if (!isVisitedWeakness[nx][ny] && map2[nx][ny] == color)
					dfs(nx, ny, color, isWeakenss);
			} else {
				if (!isVisitedNormal[nx][ny] && map[nx][ny] == color)
					dfs(nx, ny, color, isWeakenss);
			}
		}
	}
}
