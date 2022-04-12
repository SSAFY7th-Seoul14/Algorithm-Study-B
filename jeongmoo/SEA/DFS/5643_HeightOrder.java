import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SW Expert Academy 5643번. 키 순서
public class SWEA5643_HeightOrder {
	static int n, m;
	static int[][] adj;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
         
        StringTokenizer st;
        for (int tc = 1; tc <= t; tc++) {
        	// 입력
        	n = Integer.parseInt(br.readLine()); // 정점 수
        	adj = new int[n+1][n+1];
        	
        	m = Integer.parseInt(br.readLine()); // 간선 수
        	for (int i = 0; i < m; i++) {
        		st = new StringTokenizer(br.readLine());
        		int from = Integer.parseInt(st.nextToken());
        		int to = Integer.parseInt(st.nextToken());
        		adj[from][to] = 1;        		
			}
        	
        	// 계산
        	// 학생 1~n까지 체크
        	int count = 0;
        	for (int i = 1; i <= n; i++) {
        		boolean[] visited = new boolean[n+1];
				
        		// 들어오고 나가는 정점을 모두 방문한다.
        		in(i, visited);
        		out(i, visited);
        		
        		int visit = 0;
        		for (int j = 1; j <= n; j++) {
					if (visited[j] == true)
						visit++;
				}
        		
        		// 모든 정점을 방문하면 가능한 경우
        		if (visit == n)
        			count++;
			}

        	// 출력
        	System.out.printf("#%d %d\n", tc, count);
        }
    }  
    
    public static void in(int v, boolean[] visited) {
    	visited[v] = true;
    	// 들어오는 값은 v열 고정하고 세로로 확인한다.
    	for (int i = 1; i <= n; i++) {
    		if (i==v) continue;
    		if (visited[i]) continue;
    		if (adj[i][v] != 0)
    			in(i, visited);
		}
    }
    
    public static void out(int v, boolean[] visited) {
    	visited[v] = true;
    	// 나가는 값은 v행 고정하고 가로로 확인한다.
    	for (int i = 1; i <= n; i++) {
    		if (i==v) continue;
    		if (visited[i]) continue;
    		if (adj[v][i] != 0)
    			out(i, visited);
		}
    }
}