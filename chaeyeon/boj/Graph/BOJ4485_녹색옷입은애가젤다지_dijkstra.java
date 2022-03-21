package 최단경로;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//처음에는 bfs로 풀었는데 시간이 오래 걸렸다.
//다익스트라 문제라는 걸 몰랐으면 못 풀었을 거 같기도 하다.
//N-1, N-1까지의 최단경로를 다익스트라를 이용하여 구함
public class BOJ4485_녹색옷입은애가젤다지_dijkstra {
	public static class Pos implements Comparable<Pos> {
		int x,y,loss;

		public Pos(int x, int y, int loss) {
			super();
			this.x = x;
			this.y = y;
			this.loss = loss;
		}

		@Override
		public int compareTo(Pos o) {
			return this.loss - o.loss;
		}
		
		
	}
	
	static int[][] d = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		while(true) {
			
			
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			int[][] map  = new int[N][N];
			int[][] distance = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					distance[i][j] = Integer.MAX_VALUE;
				}
			}
			
			PriorityQueue<Pos> pq = new PriorityQueue<>();
			boolean[][] visited = new boolean[N][N];
			int min = Integer.MAX_VALUE;
			
			distance[0][0] = 0;
			pq.offer(new Pos(0,0,map[0][0]));
			
			while(!pq.isEmpty()) {
				Pos current = pq.poll();
				
				if(visited[current.x][current.y]) continue;
				
				visited[current.x][current.y] = true;
				
				if(current.x == N-1 && current.y == N-1) {
					min = Math.min(current.loss, min);
					break;
				}
				
				for(int i=0; i < 4; i++) {
					int dx = current.x + d[i][0];
					int dy = current.y + d[i][1];

					if(dx >= 0 && dy >= 0 && dx < N && dy < N && !visited[dx][dy]
							&& distance[dx][dy] > distance[current.x][current.y]+map[dx][dy]) {
						
						pq.add(new Pos(dx, dy, current.loss+map[dx][dy]));
						distance[dx][dy] = distance[current.x][current.y]+map[dx][dy];

					}
				}
			}
			sb.append("Problem ").append(tc++).append(": ").append(min).append("\n");

		}
		
		System.out.println(sb);
	}

}
