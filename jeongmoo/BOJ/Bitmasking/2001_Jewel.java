import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 2001번. 보석 줍기
public class BOJ2001_Jewel {
	static int n, m;
	static int[] jewel;
	static boolean[][] isVisited;
	static int[][] adj;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		adj = new int[n+1][n+1];
		isVisited = new boolean[n+1][1<<14];
		jewel = new int[n+1];
		
		// 섬의 보석마다 번호를 매긴다. 같은 섬에서 여러번 줍는걸 방지하기 위해 
		for (int i = 0; i < k; i++) {
			int no = Integer.parseInt(br.readLine());
			jewel[no] = 1<<i;
		}
		
		// 인접 배열의 가중치가 곧 max값으로 설정
		for (int i = 0; i< m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int max = Integer.parseInt(st.nextToken());
			adj[from][to] = adj[to][from] = max;
		}
		
		int result = 0;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {1,0});
		isVisited[1][0] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int v = cur[0];
			int state = cur[1];
			
			if (v == 1) {
				result = Math.max(result, Integer.bitCount(state));
			}
			
			for (int i = 1; i <= n; i++) {
				if (adj[v][i] > 0) { // 연결 체크
					// 현재 보석개수로 방문 체크
					if (isVisited[i][state])
						continue;
					
					// 가진 보석 개수가 max보다 크면 못지나감
					if (adj[v][i] < Integer.bitCount(state))
						continue;
					
					int jewelNo = jewel[i];
					if (jewelNo>0 && (state&jewelNo)==0 ) { // 현재 섬에 보석이 있고, 아직 안 주웠으면  
						q.offer(new int[] {i, state|jewelNo});
						isVisited[i][state|jewelNo] = true;
					}
					
					q.offer(new int[] {i, state});
					isVisited[i][state] = true;
				}
			}
		}
		
		System.out.println(result);
	}
}
