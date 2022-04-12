import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SW Expert Academy 5656번. 벽돌 깨기
public class SWEA5656_BreakingBricks {
	static int result;
	static int n, w, h;
	static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
         
        StringTokenizer st;
        for (int tc = 1; tc <= t; tc++) {
        	// 입력
        	st = new StringTokenizer(br.readLine());
        	n = Integer.parseInt(st.nextToken());
        	w = Integer.parseInt(st.nextToken());
        	h = Integer.parseInt(st.nextToken());
        	map = new int[h][w];
        	
        	for (int i = 0; i < h; i++) {
        		st = new StringTokenizer(br.readLine());
        		for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
        	
        	// 계산
        	// n <=4, w <= 12, h <= 15
        	// n은 4이므로 12^4 = 2만
        	// 맵크기 12x15 = 180. 
        	// 50개 테케 3초이므로 구지 최적화하지 않아도 모든 경우 돌아도 될 것 같다. 
        	result = Integer.MAX_VALUE;
        	dfs(0, map);
        	
        	// 출력
        	if (result == Integer.MAX_VALUE) // n번전에 다 터져서 result 갱신이 안된 경우 남은 게 0개
        		result = 0;
        	System.out.printf("#%d %d\n", tc, result);
        }
    }
    
    public static void dfs(int cnt, int[][] data) {
    	if (cnt == n) {
    		result = Math.min(result, getCount(data));
    		return;
    	}
    	
    	// 1열 ~ w열까지 선택을 완탐
    	for (int i = 0; i < w; i++) {
			// 일단 맵을 copy한다.
    		int[][] newMap = mapCopy(data);
    		int[] startPoint = getStarting(newMap, i);
    		
    		if (startPoint == null) // 해당 열은 벽돌이 없다.
    			continue; 
    		
    		// 벽돌 부수고, 내린 다음. 다음으로 넘어간다.
    		breaking(newMap, startPoint, new boolean[h][w]);
    		brickDown(newMap);
    		dfs(cnt+1, newMap);
		}    	
    }
    
    // 벽돌 부순 후 남은 벽돌을 아래로 내린다.
    public static void brickDown(int[][] data) {
    	// 열먼저 고정하고
    	for (int i = 0; i < w; i++) {
    		// 맨 아래 행부터 위로 올라가면서 내린다.
    		int emptyHeight = h-1;
    		for (int j = h-1; j >= 0; j--) {
				if (data[j][i] != 0) {
					data[emptyHeight][i] = data[j][i];
					if (emptyHeight != j)
						data[j][i] = 0;
					emptyHeight--;
				}
			}
    	}
    }
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    // 시작점에서 벽돌 숫자 크기에 따라 연계되면서 부서지는 작업을 처리
    public static void breaking(int[][] data, int[] cur, boolean[][] isVisited) {
    	int size = data[cur[0]][cur[1]] - 1; // 상하좌우로 이동해야할 칸수
    	
    	// 방문 체크 및 벽돌을 부순다.
    	isVisited[cur[0]][cur[1]] = true;
    	data[cur[0]][cur[1]] = 0; // 현재 위치 0으로
    	
    	for (int i = 0; i < 4; i++) {
			int nx = cur[0]; 
			int ny = cur[1];
			
			// 벽돌 숫자에 따라 그만큼 이동하면서 연쇄 파괴
			for (int j = 0; j < size; j++) {
				nx += dx[i];
				ny += dy[i];
				
				if (nx<0||ny<0||h<=nx||w<=ny)
					continue;
				if (isVisited[nx][ny])
					continue;
				
				breaking(data, new int[] {nx, ny}, isVisited);
			}			
		}
    }
    
    // 해당 열의 시작지점 (가장 위) 위치를 찾는 함수
    public static int[] getStarting(int[][] data, int c) {
    	for (int i = 0; i < h; i++) {
			if (data[i][c] != 0) {
				return new int[] {i, c};
			}
		}
		return null; 
    }
    
    // 배열 복사해서 리턴하는 함수
    public static int[][] mapCopy(int[][] data) {
    	int[][] newMap = new int[h][w];
    	for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				newMap[i][j] = data[i][j];
			}
		}
    	return newMap;
    }
    
    // 남은 벽돌 개수를 세는 함수
    public static int getCount(int[][] data) {
    	int count = 0;
    	for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (data[i][j] != 0)
					count++;
			}
    	}
    	return count;
    }
}