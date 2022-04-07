import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

// SW Expert Academy 1249번. 보급로
public class SWEA1249_SupplyRoute {
	static int n;
	static int[][] map;
	static int[][] distance;
	static boolean[][] isVisited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= t; tc++) {
        	// 입력
        	n = Integer.parseInt(br.readLine());
        	map = new int[n][n];
        	distance = new int[n][n]; 
        	isVisited = new boolean[n][n];
        	
        	for (int i = 0; i < n; i++) {
        		char[] arr = br.readLine().toCharArray();
        		for (int j = 0; j < n; j++) {
					map[i][j] = arr[j] - '0';
				}
			}
        	
        	// 계산
        	for (int i = 0; i < n; i++) {
				Arrays.fill(distance[i], Integer.MAX_VALUE);
			}
        	
        	dijkstra();

        	// 출력
        	System.out.printf("#%d %d\n", tc, distance[n-1][n-1]);
        }
    }  
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void dijkstra() {
    	PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->o1[2]-o2[2]);
    	
    	distance[0][0] = map[0][0];
    	pq.offer(new int[] {0, 0, distance[0][0]});
    	
    	while (!pq.isEmpty()) {
    		int[] cur = pq.poll();
    		
    		if (isVisited[cur[0]][cur[1]])
    			continue;
    		isVisited[cur[0]][cur[1]] = true;
    		
    		for (int i = 0; i < 4; i++) {
        		int nx = cur[0] + dx[i];
        		int ny = cur[1] + dy[i];
    			if (nx<0||ny<0||n<=nx||n<=ny)
    				continue;
    			if (distance[cur[0]][cur[1]] + map[nx][ny] < distance[nx][ny]) {
    				distance[nx][ny] = distance[cur[0]][cur[1]] + map[nx][ny];
    				pq.offer(new int[] {nx, ny, distance[nx][ny]});
    			}
    		}
    	}
    }
}