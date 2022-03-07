import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 1916번. 최소비용 구하기
public class BOJ1916_FindMinCost {
	static class Node implements Comparable<Node> {
		int vertex, weight;
		Node link;
		public Node(int vertex, int weight, Node link) {
			this.vertex = vertex;
			this.weight = weight;
			this.link = link;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		Node[] nodes = new Node[n+1];
		StringTokenizer st;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			nodes[from] = new Node(to, weight, nodes[from]);
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		// 계산
		int[] distance = new int[n+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		boolean[] visited = new boolean[n+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		distance[start] = 0;
		pq.offer(new Node(start, 0, null));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			if (visited[current.vertex]) continue;
			if (current.vertex == end) break;
			visited[current.vertex] = true;
			
			for (Node temp = nodes[current.vertex]; temp !=null; temp = temp.link) {
				if (!visited[temp.vertex] && distance[temp.vertex] > distance[current.vertex] + temp.weight) {
					distance[temp.vertex] = distance[current.vertex] + temp.weight;
					pq.offer(new Node(temp.vertex, distance[temp.vertex], null));
				}
			}			
		}
		
		// 출력
		System.out.println(distance[end]);
	}
}
