import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// SW Expert Academy 1953번. 탈주범 검거
public class SWEA1953_ArrestFugitive {
	static int n, m;
	static int[][] map;
	static boolean[][] isVisited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
         
        StringTokenizer st;
        for (int tc = 1; tc <= t; tc++) {
        	st = new StringTokenizer(br.readLine());
        	
        	// 입력
        	n = Integer.parseInt(st.nextToken()); // 세로
        	m = Integer.parseInt(st.nextToken()); // 가로
        	map = new int[n][m];
        	isVisited = new boolean[n][m];
        	
        	int x = Integer.parseInt(st.nextToken()); // 시작 x좌표
        	int y = Integer.parseInt(st.nextToken()); // 시작 y좌표
        	int l = Integer.parseInt(st.nextToken()); // 이동 가능한 시간
        	
        	for (int i = 0; i < n; i++) {
        		st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
        	
        	// 계산
        	int count = bfs(x, y, l);
        	
        	// 출력
        	System.out.printf("#%d %d\n", tc, count);
        }
    }  
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] dir = {{}, {0, 1, 2, 3}, {0, 1}, {2, 3}, {0, 3}, {1, 3}, {1, 2}, {0, 2}};
    
    public static int bfs(int x, int y, int l) {
    	Queue<int[]> q = new LinkedList<>();
    	q.offer(new int[] {x, y});
    	isVisited[x][y] = true;
    	
    	int count = 1;
    	int time = 0;
    	while (!q.isEmpty()) {
    		int size = q.size();
    		time++;
    		
    		if (time == l)
    			break;
    		
    		for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				
				int type = map[cur[0]][cur[1]];
				for (int j = 0; j < dir[type].length; j++) {
					int d = dir[type][j];
					int nx = cur[0] + dx[d];
					int ny = cur[1] + dy[d];
					if (nx<0||ny<0||n<=nx||m<=ny)
	    				continue;
					if (isVisited[nx][ny]||map[nx][ny] == 0)
						continue;
					
					int nextType = map[nx][ny];
					if (d==0) { // 위
						if (nextType == 3 || nextType == 4 || nextType == 7)
							continue;
					} else if (d==1) { // 아래
						if (nextType == 3 || nextType == 5 || nextType == 6)
							continue;
					} else if (d==2) { // 왼
						if (nextType == 2 || nextType == 6 || nextType == 7)
							continue;
					} else if (d==3) { // 오
						if (nextType == 2 || nextType == 4 || nextType == 5)
							continue;
					}
					
					q.offer(new int[] {nx, ny});
					isVisited[nx][ny] = true;
					count++;
				}
			}
    	}

    	return count;
    }
}