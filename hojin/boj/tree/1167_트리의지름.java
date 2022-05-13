import java.io.*;
import java.util.*;

public class BOJ1167_트리의지름 {
	static class Node {
		int val;
		ArrayList<Edge> link;
		int accum;

		public Node(int val) {
			this.val = val;
			this.link = new ArrayList<>();
			this.accum = 0;
		}

	}

	static class Edge {
		Node to;
		int length;

		public Edge(Node to, int length) {
			this.to = to;
			this.length = length;
		}

	}

	static Node[] nodes;
	static Node root;
	static boolean[] visited;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int V = stoi(br.readLine());
		nodes = new Node[V + 1];
		visited = new boolean[V + 1];
		for (int i = 1; i <= V; ++i) {
			nodes[i] = new Node(i);
		}
		int max = 0;
		for (int i = 0; i < V; ++i) {
			st = new StringTokenizer(br.readLine());
			int nth = stoi(st.nextToken());
			while (true) {
				int to = stoi(st.nextToken());
				if (to == -1)
					break;
				int length = stoi(st.nextToken());
				nodes[nth].link.add(new Edge(nodes[to], length));
			}
			int size = nodes[nth].link.size();
			if (max < size) {
				root = nodes[nth];
				max = size;
			}
		}
		if (V == 2) {
			System.out.println(root.link.get(0).length);
			return;
		}
		answer = 0;
		visited[root.val] = true;
		dfs(root);
		System.out.println(answer);
		br.close();
	}

	private static void dfs(Node node) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		ArrayList<Edge> link = node.link;
		int size = link.size();
		if (size == 1) {
			// leaf 노드에서 accum은 0 그대로
			return;
		}
		for (Edge edge : link) {
			Node next = edge.to;
			int nextLen = edge.length;
			if (!visited[next.val]) {
				visited[next.val] = true;
				dfs(next);
				// 최대 길이를 각 노드에 저장해두고 dp하듯이 갱신하면 된다.
				int nextAccum = next.accum + nextLen;
				pq.add(nextAccum);
				if (node.accum < nextAccum) {
					node.accum = nextAccum;
				}
			}
		}
		// 임의로 root를 정했더니 root에서 최대가 발생하지 않을 수 있다.
		while (pq.size() > 2) {
			pq.poll();
		}
		int sum = 0;
		while (!pq.isEmpty()) {
			sum += pq.poll();
		}
		// 그래서 매번 node마다 pq에 넣고 가장 큰 길이를 갱신해줘야 한다.
		if (sum > answer) {
			answer = sum;
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}