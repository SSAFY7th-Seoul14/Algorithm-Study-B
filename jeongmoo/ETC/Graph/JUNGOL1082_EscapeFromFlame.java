import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 정올 1082번. 화염에서 탈출
public class JUNGOL1082_EscapeFromFlame {
	static int r, c;
	static int result;
	static char[][] map;
	static ArrayList<int[]> fireList;
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		fireList = new ArrayList<>();
		
		// 입력
		int[] start = {0, 0};
		for (int i = 0; i < r; i++) {
			String line = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'S') {
					start = new int[]{i, j, 1, 0};
				} else if (map[i][j] == '*') {
					fireList.add(new int[]{i, j, 0});
				}
			}
		}
		
		// 처리
		result = -1;
		bfs(start);
		
		// 출력
		if (result == -1)
			System.out.println("impossible");
		else
			System.out.println(result);
	}
	
	public static void bfs(int[] start) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] isVisited = new boolean[r][c];
		
		// 시작점 넣기 (용사 먼저 이동)
		q.offer(start);
		isVisited[start[0]][start[1]] = true;
		
		// 불 위치 넣어줌
		for (int[] p : fireList) {
			q.offer(p);
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			// 원래 용사가 갈수 있는 위치였는데 현재 상태가 불이면 체크 x
			if (map[cur[0]][cur[1]] == '*' && cur[2] == 1)
				continue;
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if (nx<0 || ny<0 || r<=nx || c<=ny || isVisited[nx][ny])
					continue;

				if (cur[2] == 0) { // 불
					// 옆칸이 비어있으면 옮긴다.
					if (map[nx][ny] == '.' || map[nx][ny] == 'S') {
						map[nx][ny] = '*';
						q.offer(new int[] {nx, ny, cur[2]});
					}
				} else { // 용사
					// 도착지 찾으면 끝
					if (map[nx][ny] == 'D') {
						result = cur[3] + 1;
						return;
					} else if (map[nx][ny] == '.') { // 주변에 빈칸이 있는지 찾는다.
						map[nx][ny] = 'S';
						map[cur[0]][cur[1]] = '.';
						q.offer(new int[] {nx, ny, cur[2], cur[3]+1});
					}
				}
			}
		}
	}
}