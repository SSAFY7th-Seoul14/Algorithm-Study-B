import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 15683번. 감시
public class BOJ15683_Monitor {
	static class Point {
		int x, y, type;

		public Point(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}
	
	static int result = Integer.MAX_VALUE;
	static ArrayList<Point> cctvList = new ArrayList<>();
	static int n, m;
	static int[][] map;
	
	// 상우하좌
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]!=0 && map[i][j] < 6)
					cctvList.add(new Point(i, j, map[i][j]));
			}
		}
		
		// 계산
		dfs(0);
		
		
		// 출력
		System.out.println(result);
	}
	
	public static void dfs(int count) {
		// 모두 탐색하면 그중에서 최소값 찾음
		if (count == cctvList.size()) {
			result = Math.min(result, counting());
			return;
		}
		
		Point cctv = cctvList.get(count);
		int markNo = count+10;
		
		// 5는 한방향		
		if (cctv.type == 5) {
			mark(cctv, 0, markNo);
			dfs(count+1);
			reset(markNo);
		// 2는 두방향
		} else if (cctv.type == 2){
			for (int i = 0; i < 2; i++) {
				mark(cctv, i, markNo);
				dfs(count+1);
				reset(markNo);
			}			
		// 1, 3, 4는 네방향을 탐색해야함.
		} else {
			for (int i = 0; i < 4; i++) {
				mark(cctv, i, markNo);
				dfs(count+1);
				reset(markNo);
			}
		}
	}
	
	// 범위 체크
	public static boolean rangeCheck(int x, int y) {
		if (x < 0 || y < 0 || n <= x || m <= y || map[x][y] == 6)
			return false;
		return true;
	}
	
	// 해당 방향으로 벽이나 범위끝까지 마킹
	public static void marking(int x, int y, int dir, int markNo) {
		int nx = x;
		int ny = y;
		
		while (true) {
			nx += dx[dir];
			ny += dy[dir];
			if (!rangeCheck(nx, ny))
				break;
			if (map[nx][ny] == 0)
				map[nx][ny] = markNo;
		}
	}
	
	// cctv 종류에 따라 마킹
	public static void mark(Point cctv, int dir, int markNo) {
		switch (cctv.type) {
		case 5:
			for (int i = 0; i < 4; i++) {
				marking(cctv.x, cctv.y, i, markNo);
			}			
			break;
		case 2:
			marking(cctv.x, cctv.y, dir, markNo);
			marking(cctv.x, cctv.y, dir+2, markNo);
			break;
		case 1:
			marking(cctv.x, cctv.y, dir, markNo);
			break;
		case 3:
			marking(cctv.x, cctv.y, dir, markNo);
			marking(cctv.x, cctv.y, (dir+1)%4, markNo);
			break;
		case 4:
			marking(cctv.x, cctv.y, dir, markNo);
			marking(cctv.x, cctv.y, (dir+1)%4, markNo);
			marking(cctv.x, cctv.y, (dir+2)%4, markNo);
			break;
		default:
			break;
		}		
	}
	
	// 스텝 끝나면 마킹을 되돌린다.
	public static void reset(int markNo) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == markNo)
					map[i][j] = 0;
			}
		}
	}
	
	// 사각지대 카운팅
	public static int counting() {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0)
					count++;
			}
		}
		return count;
	}
}
