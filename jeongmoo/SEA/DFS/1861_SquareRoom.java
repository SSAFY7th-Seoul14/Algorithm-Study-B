import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//SW Expert Academy 1861번. 정사각형 방
public class SWEA1861_SquareRoom {
	static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
     
    static int n;
    static int[][] map;
    static boolean[][] isVisited;
    
    static class Room implements Comparable<Room> {
    	int x, y, no;
		public Room(int x, int y, int no) {
			this.x = x;
			this.y = y;
			this.no = no;
		}
		@Override
		public int compareTo(Room o) {
			return this.no - o.no;
		}
    }
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
         
        StringTokenizer st;
        for (int tc = 1; tc <= t; tc++) {
        	ArrayList<Room> roomList = new ArrayList<>();
        	
            // 입력
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            isVisited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    roomList.add(new Room(i, j, map[i][j]));
                }
            }
            Collections.sort(roomList);
             
            // 처리
            int maxCount = Integer.MIN_VALUE;
            int start = Integer.MAX_VALUE;
            
            // 방을 오름차순(낮은순부터)으로 방문하지 않으면 중간이 먼저 방문 체크될 수 있어 정렬 후 오름차순 방문
            for (Room room : roomList) {
            	if (!isVisited[room.x][room.y]) {
	            	int count = dfs(room.x, room.y, 1);
	            	
	            	// 더 크면 갱신, 같으면 더 낮은 값으로 해야하나 오름차순이므로 최초값이 답(같은 경우 체크 불필요)
	                if (maxCount < count) {
	                    maxCount = count;
	                    start = map[room.x][room.y];
	                }
            	}
            }
             
            // 출력
            System.out.printf("#%d %d %d\n", tc, start, maxCount);
        }
    }
     
    public static int dfs(int x, int y, int count) {
        isVisited[x][y] = true;
        int temp = 1;
 
        int current = map[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
             
            // 범위 체크
            if (nx < 0 || ny < 0 || n <= nx || n <= ny)
                continue;
             
            // 방문 안한 곳만 방문
            if (!isVisited[nx][ny]) {
                if(current+1 == map[nx][ny]) {
                    int cnt = dfs(nx, ny, count+1);
                    temp = Math.max(temp, cnt);
                }
            }
        }
         
        return Math.max(temp, count);
    }
}