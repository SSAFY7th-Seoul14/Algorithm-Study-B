import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 16946번. 벽 부수고 이동하기 4
public class BOJ16946_BreakWallAndMove4 {
	static int n, m;
	static char[][] map;
	static boolean[][] isVisited;
	static int[][] emptyIdxMap;
	static HashMap<Integer, Integer> emptyCntMap;

	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][m];
		isVisited = new boolean[n][m];
		// 입력
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// 처리
		// 빈 칸은 dfs 돌려서 emptyIdxMap에는 각각의 idx를
		// emptyCntMap에는 idx마다 개수를 저장한다.
		emptyIdxMap = new int[n][m];
		emptyCntMap = new HashMap<>();
		int idx = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == '0' && !isVisited[i][j]) {
					int count = dfs(i, j, idx);
					emptyCntMap.put(idx, count);
					idx++;
				}
			}
		}
		
		int[][] result = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 1인 칸에서만 체크
				if (map[i][j] == '1') {
					// 4방탐색하여 0인경우 해당 emptyGroup의 idx를 set에 저장한다.
					HashSet<Integer> emptyIdxSet = new HashSet<>();
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (nx<0 || ny<0 || n<=nx || m<=ny)
							continue;
						if (map[nx][ny] == '0') {
							int emptyIdx = emptyIdxMap[nx][ny];
							emptyIdxSet.add(emptyIdx);
						}
					}
					
					// 해당 idx의 count를 모두 더해주고 자기까지 1을 더한다.
					result[i][j] = 1;
					for (int emptyIdx : emptyIdxSet) {
						result[i][j] += emptyCntMap.get(emptyIdx);
					}
				}
			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(result[i][j]%10);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static int dfs(int x, int y, int idx) {
		isVisited[x][y] = true;
		emptyIdxMap[x][y] = idx;
		
		int count = 1;		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx<0 || ny<0 || n<=nx || m<=ny || isVisited[nx][ny])
				continue;
			
			if (map[nx][ny] == '0') {
				count += dfs(nx, ny, idx);
			}
		}
		
		return count;
	}
}