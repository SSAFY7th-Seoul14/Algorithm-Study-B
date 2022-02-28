import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 1753번. 최단경로
// 인접행렬 -> 메모리초과
// 인접리스트로 변경
public class BOJ1753_ShortestPath {
	static class Node {
		int vertex;
		int weight;
		Node link;

		public Node(int vertex, int weight, Node link) {
			this.vertex = vertex;
			this.weight = weight;
			this.link = link;
		}
	}
	static class Vertex implements Comparable<Vertex> {
		int no, minDistance; // 정점번호, 출발지에서 자신으로의 최소비용

		public Vertex(int no, int minDistance) {
			this.no = no;
			this.minDistance = minDistance;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.minDistance - o.minDistance;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());	// 정점
		int E = Integer.parseInt(st.nextToken());	// 간선
		int start = Integer.parseInt(br.readLine()); // 시작
		
		// 입력
		Node[] adjList = new Node[V+1]; // 정점수 크기로 생성
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
		// 처리 - 다익스트라
		int[] distance = new int[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		boolean[] isVisited = new boolean[V+1];
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		
		distance[start] = 0;
		pq.offer(new Vertex(start, distance[start]));
		
		while (!pq.isEmpty()) {
			Vertex current = pq.poll();
			
			if(isVisited[current.no]) continue;
			isVisited[current.no] = true;

			for (Node temp=adjList[current.no]; temp != null; temp = temp.link) {
				if(!isVisited[temp.vertex]
					&& distance[temp.vertex] > distance[current.no] + temp.weight) {
					distance[temp.vertex] = distance[current.no] + temp.weight;
					pq.offer(new Vertex(temp.vertex, distance[temp.vertex]));
				}
			}
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if (distance[i] == Integer.MAX_VALUE)
				sb.append("INF").append("\n");
			else
				sb.append(distance[i]).append("\n");
		}
		System.out.println(sb);
	}
}