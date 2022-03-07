import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753 {

	static class Node {
		int vertex, weight;
		Node link;

		public Node(int vertex, int weight, Node link) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.link = link;
		}

	}

	static class Vertex implements Comparable<Vertex> {
		int no, minDistance;

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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 정점의 개수 V
		int V = Integer.parseInt(st.nextToken());
		// 간선의 개수 E
		int E = Integer.parseInt(st.nextToken());
		// 시작 정점 번호 start
		int start = Integer.parseInt(br.readLine());

		// Node array로 인접리스트 생성
		Node[] adjList = new Node[V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			// u에서 v로 가는 가중치 w를 인접 리스트에 저장
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[u] = new Node(v, w, adjList[u]);
		}

		br.close();

		// distance 거리배열 무한대로 초기화
		int[] distance = new int[V + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		// 방문했는지, 즉 최소비용이 확정 됐는지 확인하기 위한 visited
		boolean[] visited = new boolean[V + 1];

		PriorityQueue<Vertex> pQueue = new PriorityQueue<Vertex>();

		// 시작점을 PQ에 넣어주면서 시작
		distance[start] = 0;
		pQueue.offer(new Vertex(start, distance[start]));

		while (!pQueue.isEmpty()) {
			// 1단계: 최소비용 확정 정점 선택
			Vertex current = pQueue.poll();
			if (visited[current.no])
				continue;
			visited[current.no] = true;

			// 2단계: 기존 최소비용들과 1단계에서 확정된 최소비용 정점을 경유지로 했을 때의 최소비용 비교 후 갱신
			for (Node temp = adjList[current.no]; temp != null; temp = temp.link) {
				if (!visited[temp.vertex] && distance[temp.vertex] > distance[current.no] + temp.weight) {
					distance[temp.vertex] = distance[current.no] + temp.weight;
					pQueue.offer(new Vertex(temp.vertex, distance[temp.vertex]));
				}

			}
		}
		for (int i = 1; i <= V; i++) {
			if (distance[i] == Integer.MAX_VALUE) {
				bw.write("INF");
			} else {
				bw.write(Integer.toString(distance[i]));
			}
			bw.newLine();
		}

		bw.flush();
		bw.close();
	}

}