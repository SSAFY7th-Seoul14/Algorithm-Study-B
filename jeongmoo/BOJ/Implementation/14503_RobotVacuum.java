import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14503번. 로봇 청소기
public class BOJ14503_RobotVacuum {
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Point robot;
		st = new StringTokenizer(br.readLine());
		robot = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		int dir = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 동작
		// 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		
		int dircheck = 0;
		while(true) {
			// 1. 청소
			map[robot.x][robot.y] = 2;
			
			// 2-1. 왼쪽방향 체크
			int newdir = dir-1>=0? dir-1: dir+3;
			
			// 청소 안했으면 이동
			int nx = robot.x + dx[newdir];
			int ny = robot.y + dy[newdir];
			if (0<=nx && 0<=ny && nx<n && ny<m && map[nx][ny] == 0) {
				robot.move(nx, ny);
				dir = newdir;
				dircheck = 0;
				continue;
			}
			
			// 2-2. 못가면 2번으로 다시 돌아가고 카운트
			if (dircheck < 4) {
				dir = newdir;
				dircheck++;
				continue;
			}
			
			// 2-3. 4방향 다 청소하거나 벽이면 한칸 후진
			int backdir = dir-2>=0? dir-2 : dir+2;
			int bx = robot.x + dx[backdir];
			int by = robot.y + dy[backdir];
			
			// 2-4. 못가면 종료
			if (bx<0 || by<0 || n<=bx || m<=by || map[bx][by] == 1)
				break;
			
			dircheck = 0;
			robot.move(bx, by);
		}	
		
		// 출력
		int result = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 2)
					result++;
			}
		}
		System.out.println(result);
	}
}
