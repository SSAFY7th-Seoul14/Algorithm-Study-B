import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SW Expert Academy 1767번. 프로세서 연결하기
public class SWEA1767_ConnectProcessor {
	static class Processor {
		int x, y;

		public Processor(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int n, result;
	static int[][] map;
	static Processor[] list;
	static int pCnt, cCnt; // 프로세서 개수, 연결된 개수
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
         
        StringTokenizer st;
        for (int tc = 1; tc <= t; tc++) {
        	n = Integer.parseInt(br.readLine()); // 7<=n<=12
        	map = new int[n][n];
        	list = new Processor[13];
        	
        	pCnt = 0;
        	for (int i = 0; i < n; i++) {
        		st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						list[pCnt++] = new Processor(i, j);
					}
				}
			}
        	
        	// 계산
        	// n이 12밖에 안되므로 dfs. 최대 4^12 = 2^24 = 약 2천만
        	// 50개 tc 4초 이내라서 백트래킹 전에는 시간초과 발생하였음.
        	result = Integer.MAX_VALUE;
        	cCnt = 0;
        	dfs(list[0], 0, 0);        	
        	
        	System.out.printf("#%d %d\n", tc, result);
        }
    }
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void dfs(Processor p, int cnt, int conCnt) {
    	int remainCnt = pCnt - cnt; // 프로세서 개수 - 현재 개수
    	
    	// 백트래킹
    	// 현재 연결 개수 + 남은 개수 < 연결된 최대개수. 이면 가능성 없음
    	if (conCnt + remainCnt < cCnt) {
    		return;
    	}
    	
    	// 프로세서 개수만큼 다 돌았으면
    	if (cnt == pCnt) {
    		// 개수가 늘어날 때는 result값도 초기화 해줘야 한다.
    		if (cCnt < conCnt) {
    			cCnt = conCnt;
    			result = Integer.MAX_VALUE;
    		}
    		
    		// 최대일 때 갱신
    		if (cCnt == conCnt) {
	    		int lineCnt = getLineCount();
	    		result = Math.min(result, lineCnt);
    		}
    		return;
    	}
    	
    	for (int i = 0; i < 4; i++) {
			int nx = p.x;
			int ny = p.y;
			
			int connect = 0;
			while(true) {
				nx += dx[i];
				ny += dy[i];
				
				if (nx<0||ny<0||n<=nx||n<=ny) {
					connect = 1;
					break;
				}
				
				if (map[nx][ny] != 0)
					break;
				
				marking(nx, ny, cnt);
			}
			dfs(list[cnt+1], cnt+1, conCnt+connect);
			unmarking(cnt);
		}
    }
    
    public static void marking(int x, int y, int mark) {
    	map[x][y] = mark+100;
    }
    
    public static void unmarking(int mark) {
    	for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == mark+100)
					map[i][j] = 0;
			}
    	}
    }
    
    public static int getLineCount() {
    	int count = 0;
    	for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1 || map[i][j] == 0)
					continue;
				count++;
			}
    	}
    	return count;
    }
}