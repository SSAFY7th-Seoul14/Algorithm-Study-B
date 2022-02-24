import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1238번. 파티
public class BOJ1238_Party {
	static int result = Integer.MIN_VALUE;
	static int n, adj[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 정점 개수
		int m = Integer.parseInt(st.nextToken()); // 간선 개수
		int x = Integer.parseInt(st.nextToken()); // 모이는 곳
		
		// 입력
		adj = new int[n+1][n+1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adj[from][to] = weight;
		}
		
		// 다익스트라
		// 시작점을 i로 바꿔가며 계속 탐색
		int[] time = new int[n+1];
		for (int i = 1; i <= n; i++) {
			time[i] = dijkstra(i, x);
			time[i] += dijkstra(x, i);
		}
		Arrays.sort(time);		
		
		// 출력
		System.out.println(time[n]);
	}
	
	public static int dijkstra(int start, int end) {
		int[] distance = new int[n+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		boolean[] isVisited = new boolean[n+1];
		
		distance[start] = 0;
		for (int i = 1; i <= n; i++) {
			int min = Integer.MAX_VALUE;
			int current = 0;
			for (int j = 1; j <= n; j++) {
				if (!isVisited[j] && min > distance[j]) {
					min = distance[j];
					current = j;
				}
			}
			
			isVisited[current] = true;
			if (current == end)
				return distance[end];
			
			for (int j = 1; j <= n; j++) {
				if (!isVisited[j] && adj[current][j] != 0
					&& distance[j] > distance[current] + adj[current][j])
					distance[j] = distance[current] + adj[current][j];
			}
		}
		return distance[end];
	}
}