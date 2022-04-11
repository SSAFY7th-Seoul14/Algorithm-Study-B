import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1956번. 운동
public class BOJ1956_Exercise {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
    	// 입력
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	int[][] adj = new int[n][n];
    	
    	final int INF = 9999999;
    	for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				adj[i][j] = INF;
			}
		}
    	
    	for (int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int from = Integer.parseInt(st.nextToken()) - 1;
    		int to = Integer.parseInt(st.nextToken()) - 1;
    		int weight = Integer.parseInt(st.nextToken());
    		
    		adj[from][to] = Math.min(adj[from][to], weight);
		}
    	
    	// 플로이드
    	for (int k = 0; k < n; k++) {
    		for (int i = 0; i < n; i++) {
    			for (int j = 0; j < n; j++) {
    				if (adj[i][j] > adj[i][k] + adj[k][j])
    					adj[i][j] = adj[i][k] + adj[k][j];
    			}
    		}
		}
    	
    	// 출력
    	int min = adj[0][0];
    	for (int i = 1; i < n; i++) {
    		min = Math.min(min, adj[i][i]);
		}
    	
    	if (min == INF)
    		System.out.println("-1");
    	else
    		System.out.println(min);
    }
}