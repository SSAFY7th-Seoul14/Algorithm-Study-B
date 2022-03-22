import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 1261번. 알고스팟
public class BOJ1261_AlgoSpot {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		char[][] data = new char[m][n];
		for (int i = 0; i < m; i++) {
			data[i] = br.readLine().toCharArray();
		}
		
		// 계산. 다익스트라
		int[][] distance = new int[m][n];
		for (int i = 0; i < m; i++) {
			Arrays.fill(distance[i], Integer.MAX_VALUE);
		}
		distance[0][0] = data[0][0]-'0';
		
		boolean[][] visited = new boolean[m][n];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->o1[2]-o2[2]);
		pq.offer(new int[] {0,0,data[0][0]-'0'});
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if(visited[cur[0]][cur[1]])
				continue;
			visited[cur[0]][cur[1]] = true;
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (nx<0 || ny<0 || m<=nx || n<=ny)
					continue;
				
				if(!visited[nx][ny] && 
					distance[nx][ny] > distance[cur[0]][cur[1]] + data[nx][ny]-'0') {
					distance[nx][ny] = distance[cur[0]][cur[1]] + data[nx][ny]-'0';
					pq.offer(new int[] {nx, ny, distance[nx][ny]});
				}
			}
		}
		
		// 출력
		System.out.println(distance[m-1][n-1]);
	}
}