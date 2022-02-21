import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 9372번. 상근이의 여행
public class BOJ9372_Trip {
	static int n;
	static int[][] adj;
	static boolean[] isVisited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 국가 수
			int m = Integer.parseInt(st.nextToken()); // 비행기 종류
			
			adj = new int[n+1][n+1];
			isVisited = new boolean[n+1];
			for (int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adj[from][to] = 1;
				adj[to][from] = 1;
			}
			
			// 처리
			int count = 0;
			for (int j = 1; j <= n; j++) {
				if (!isVisited[j])
					count += dfs(j);
			}
			
			// 출력
			System.out.println(count-1);
		}
	}
	
	public static int dfs(int current) {
		isVisited[current] = true;
		
		int count = 1;
		for(int i=1; i<=n; i++) {
			if (!isVisited[i] && adj[current][i] != 0)
				count += dfs(i);
		}

		return count;
	}
}
