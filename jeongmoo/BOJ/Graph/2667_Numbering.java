import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// 백준 2667번. 단지번호붙이기
public class BOJ2667_Numbering {
	static int n;
	static char[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int totalCount = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					int count = dfs(i, j);
					if (count > 0) {
						totalCount++;
						list.add(count);
					}
				}
			}
		}
		Collections.sort(list);
		
		System.out.println(totalCount);
		for (int num : list) {
			System.out.println(num);
		}
	}
	
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	public static int dfs(int x, int y) {
		visited[x][y] = true;
		if (map[x][y] == '0')
			return 0;
		
		int count = 1;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx<0 || ny<0 || n<=nx || n<=ny)
				continue;
			
			if (visited[nx][ny])
				continue;
			
			count += dfs(nx, ny);
		}
		
		return count;
	}
}
