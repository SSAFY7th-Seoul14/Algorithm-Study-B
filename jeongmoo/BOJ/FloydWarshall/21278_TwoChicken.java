import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 21278번. 호석이 두 마리 치킨
public class BOJ21278_TwoChicken {
	static int n, min;
	static int v1, v2;
	static int[][] adj;
	static int[] selected;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
    	// 입력
    	n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	adj = new int[n+1][n+1];
    	selected = new int[2];
    	
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
    		
    		adj[from][to] = adj[to][from] = 1;
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
    	
    	// 2개 최소값 찾는다 100C2
    	min = Integer.MAX_VALUE;
    	combination(0, 1);
    	
    	// 출력
    	System.out.println(v1 + " " + v2 + " " + min*2);
    }
    
    public static void combination(int idx, int start) {
    	if (idx == 2) {
    		int dist = getDist();
    		if (dist < min) {
    			v1 = selected[0];
    			v2 = selected[1];
    			min = dist;
    		}
    		return;
    	}
    	
    	for (int i = start; i <= n; i++) {
    		selected[idx] = i;
    		combination(idx+1, i+1);
		}
    }
    
    public static int getDist() {
    	int dist = 0;
    	for (int i = 1; i <= n; i++) {
    		dist += Math.min(adj[i][selected[0]], adj[i][selected[1]]);
    	}
    	return dist;
    }
}