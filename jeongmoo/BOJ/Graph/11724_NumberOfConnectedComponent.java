import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11724번. 연결 요소의 개수
public class BOJ11724_NumberOfConnectedComponent {
	static int n;
	static int[][] adj;
	static boolean[] isVisited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		isVisited = new boolean[n+1];
		adj = new int[n+1][n+1];
		for (int i = 0; i < m; i++) {
			 st = new StringTokenizer(br.readLine());
			 int from = Integer.parseInt(st.nextToken());
			 int to = Integer.parseInt(st.nextToken());
			 
			 adj[from][to] = 1;
			 adj[to][from] = 1;
		}
		
		// 처리
		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (!isVisited[i]) {
				dfs(i);
				count++;
			}
		}
		
		// 출력
		System.out.println(count);
	}
	
	public static void dfs(int current) {
		isVisited[current] = true;
		
		for(int i=1; i<=n; i++) {
			if (!isVisited[i] && adj[current][i] != 0)
				dfs(i);
		}
	}
}
