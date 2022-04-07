import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2458번. 키 순서
public class BOJ2458_HeightOrder {
	static int n, m;
	static int[][] adj;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         
    	// 입력
    	n = Integer.parseInt(st.nextToken()); // 정점 수
    	m = Integer.parseInt(st.nextToken()); // 간선 수
    	adj = new int[n+1][n+1];
    	
    	for (int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int from = Integer.parseInt(st.nextToken());
    		int to = Integer.parseInt(st.nextToken());
    		adj[from][to] = 1;        		
		}
    	
    	// 계산
        // 메모이제이션 적용값을 0행, 0열에 저장하기 위해 -1로 초기화
        for (int i = 1; i <= n; i++) {
            adj[i][0] = -1;
        }

        // 학생 1~n까지 체크
        for (int i = 1; i <= n; i++) {
            // 탐색 전인 학생만 탐색 (메모이제이션 적용)
            if (adj[i][0] == -1)
                dfs(i);
        }

        // 자신보다 작은 학생수 카운트
        // 인접행렬에 직접적으로 매핑을 다 해두었기 때문에 그 값을 이용해서
        // 정점마다 들어오는 값 (작은 학생수)를 0행에 저장해둔다.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                adj[0][j] += adj[i][j];
            }
        }

        // 저장된 0행, 0열 (들어오고, 나갈 수 있는 값)값을 이용해 계산한다.
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (adj[i][0] + adj[0][i] == n-1)
                count++;
        }

    	// 출력
    	System.out.println(count);
    }  
    
    public static void dfs(int v) {
    	for (int i = 1; i <= n; i++) {
    		if (adj[v][i] != 0) { // 나가는 경우, 나보다 큰 학생이면
    			if (adj[i][0] == -1) // 메모이제이션 저장값 없으면
    				dfs(i);
    			
    			if (adj[i][0] > 0) { // i보다 큰 학생이 있다면
    				// 나보다 큰 학생이 알고 있는 다른 학생간의 키 관계를 나와의 직접 관계로 매핑
    				// v < i < j  ==>  v < j
    				for (int j = 1; j <= n; j++) {
						if (adj[i][j] == 1)
							adj[v][j] = 1;
					}
    			}
    		}
		}
    	
    	// 직접 매핑 후 그 값을 이용해
    	// 정점마다 나갈 수 있는 값들을 0열에 저장해둔다. (메모이제이션)
    	int cnt = 0;
    	for (int i = 1; i <= n; i++) {
			cnt += adj[v][i];
		}
    	adj[v][0] = cnt;
    }
}