import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// SW Expert Academy 1238번. Contact
public class SWEA1238_Contact {	
	static int[][] adj;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 1; i <= 10; i++) {
			// 초기화
			adj = new int[101][101];
			
			// 입력
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); 		// 데이터 개수
			int start = Integer.parseInt(st.nextToken());	// 시작점
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n/2; j++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adj[from][to] = 1;
			}
			
			// 처리
			int answer = bfs(start);
			
			// 출력
			System.out.printf("#%d %d\n", i, answer);
		}
	}
	
	public static int bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] isVisited = new boolean[101];
		
		q.offer(start);
		isVisited[start] = true;
		
		int max = 0;
		while(!q.isEmpty()) {
			max = 0;
			int size = q.size();
			for(int j=0; j<size; j++) {
				int current = q.poll();
				for (int i = 0; i < 100; i++) {
					if (!isVisited[i] && adj[current][i] != 0) {
						q.offer(i);
						isVisited[i] = true;
					}
				}				
				max = Math.max(max, current);
			}
		}
		return max;
	}
}
