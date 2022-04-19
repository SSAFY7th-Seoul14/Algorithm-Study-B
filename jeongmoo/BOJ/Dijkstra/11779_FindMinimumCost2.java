import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

// 백준 11779번. 최소비용 구하기 2
public class BOJ11779_FindMinimumCost2 {	
	static class Vertex implements Comparable<Vertex> {
		int no, cost;

		public Vertex(int no, int cost) {
			this.no = no;
			this.cost = cost;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.cost - o.cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine()); // 도시 개수
		int m = Integer.parseInt(br.readLine()); // 버스 개수
		
		int[][] adj = new int[n+1][n+1];
		for (int i = 0; i < n+1; i++) {			
			Arrays.fill(adj[i], -1);
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if (adj[from][to] != -1)
				adj[from][to] = Math.min(adj[from][to], cost);
			else
				adj[from][to] = cost;
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		// 다익스트라
		int[] distance = new int[n+1];
		int[] vertex = new int[n+1];
		boolean[] isVisited = new boolean[n+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		vertex[start] = start;
		
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.add(new Vertex(start, distance[start]));
		
		while(!pq.isEmpty()) {
			Vertex cur = pq.poll();
			
			if (isVisited[cur.no]) continue;
			isVisited[cur.no] = true;
			
			for (int i = 1; i <= n; i++) {
				if(!isVisited[i] && adj[cur.no][i] != -1
				&& distance[i] > distance[cur.no]+ adj[cur.no][i] ) {
					distance[i] = distance[cur.no] + adj[cur.no][i];
					vertex[i] = cur.no;
					pq.offer(new Vertex(i, distance[i]));
				}
			}
		}
		
		// 최소비용
		System.out.println(distance[end]);
		
		// 최소경로 스택에 저장 
		int count = 0;
		Stack<Integer> s = new Stack<>();
		int idx = end;
		while(true) {
			count++;
			s.push(idx);
			if (vertex[idx] != idx) {
				idx = vertex[idx];
			} else {
				break;
			}
		}
		
		// 최소경로 출력
		System.out.println(count);
		int size = s.size();
		for (int i = 0; i < size; i++) {
			System.out.print(s.pop() + " ");
		}
	}
}