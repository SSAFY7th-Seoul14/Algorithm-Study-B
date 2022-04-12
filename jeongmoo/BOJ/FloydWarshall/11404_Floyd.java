import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11404번. 플로이드
public class BOJ11404_Floyd {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
    	// 입력
    	int n = Integer.parseInt(br.readLine()); 
    	int m = Integer.parseInt(br.readLine());
    	int[][] adj = new int[n+1][n+1];
    	
    	final int INF = 9999999;
    	for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i==j) continue;
				adj[i][j] = INF;
			}
		}
    	
    	for (int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int from = Integer.parseInt(st.nextToken());
    		int to = Integer.parseInt(st.nextToken());
    		int weight = Integer.parseInt(st.nextToken());
    		
    		adj[from][to] = Math.min(adj[from][to], weight);
		}
    	
    	// 플로이드
    	for (int k = 1; k <= n; k++) {
    		for (int i = 1; i <= n; i++) {
    			if (i==k) continue;
    			for (int j = 1; j <= n; j++) {
    				if (j==k || i==j) continue;
    				if (adj[i][j] > adj[i][k] + adj[k][j])
    					adj[i][j] = adj[i][k] + adj[k][j];
    			}
    		}
		}
    	
    	// 출력
    	for (int i = 1; i <= n; i++) {
    		for (int j = 1; j <= n; j++) {
    			if (adj[i][j] == INF)
    				System.out.print("0 ");
    			else
    				System.out.print(adj[i][j] + " ");
    		}
    		System.out.println();
		}
    }
}