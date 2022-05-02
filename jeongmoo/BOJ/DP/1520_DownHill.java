import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1520_DownHill {
	static int n,m;
	static int map[][];
	static int dp[][];
	static boolean isVisited[][];
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 데이터를 입력받는다.
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken()); // 세로
		n = Integer.parseInt(st.nextToken()); // 가로
		
		map = new int[m][n];
		dp = new int[m][n];
		isVisited = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 계산
		int result = dfs(0, 0); // dfs를 통해 0,0에서 시작하여 도착지까지 가는 경로를 계산한다.
		
		// 결과 출력
		System.out.println(result);
	}
	
	public static int dfs(int x, int y) {
		// 목적지에 도착하면 경로 개수를 1개 증가시킨다.
		if (x == m-1 && y == n-1) {
			return 1;
		}
		isVisited[x][y] = true;
		
		// 현재 위치에서 4방으로 탐색하며 이동가능한 곳이 있으면 이동한다.
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			// 범위를 벗어나는지 체크
			if(nx<0||ny<0||m<=nx||n<=ny)
				continue;
						
			// 다음 위치가 현재 위치보다 낮으면 이동가능
			// 내리막길만 이동가능하므로 방문체크를 안해도 왔던길로 되돌아갈수 없다.
			if (map[nx][ny] < map[x][y]) {
				if (isVisited[nx][ny]) {
					dp[x][y] += dp[nx][ny];
					continue;
				}

				dp[x][y] += dfs(nx, ny); 
			}
		}
		
		return dp[x][y];
	}
}
