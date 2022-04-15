//50분

import java.io.*;
import java.util.*;

public class BOJ1260_DFS와BFS {
	static class Node implements Comparable<Node> {
		int no;
		PriorityQueue<Node> next;

		public Node(int no) {
			this.no = no;
			this.next = new PriorityQueue<>();
		}

		@Override
		public int compareTo(Node o) {
			return this.no - o.no;
		}
	}

	static StringBuilder sb = new StringBuilder();
	static boolean[] visitedDFS, visitedBFS;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		Node[] nodesDFS = new Node[n + 1];
		Node[] nodesBFS = new Node[n + 1];
		visitedDFS = new boolean[n + 1];
		visitedBFS = new boolean[n + 1];
		for (int i = 1; i <= n; ++i) {
			nodesDFS[i] = new Node(i);
			nodesBFS[i] = new Node(i);
		}
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			nodesDFS[from].next.offer(nodesDFS[to]);
			nodesDFS[to].next.offer(nodesDFS[from]);
			nodesBFS[from].next.offer(nodesBFS[to]);
			nodesBFS[to].next.offer(nodesBFS[from]);
		}

		sb.append(v).append(' ');
		visitedDFS[v] = true;
		dfs(nodesDFS, nodesDFS[v]);
		sb.append('\n').append(v).append(' ');
		visitedBFS[v] = true;
		bfs(nodesBFS, nodesBFS[v]);
		System.out.println(sb);
		br.close();
	}

	private static void bfs(Node[] nodesBFS, Node node) {
		Queue<Node> q = new LinkedList<>();
		q.offer(node);
		while (!q.isEmpty()) {
			Node cur = q.poll();
			PriorityQueue<Node> next = cur.next;
			while (!next.isEmpty()) {
				Node nextNode = next.poll();
				int nextNo = nextNode.no;
				if (!visitedBFS[nextNo]) {
					visitedBFS[nextNo] = true;
					sb.append(nextNo).append(' ');
					q.offer(nextNode);
				}
			}
		}
	}

	private static void dfs(Node[] nodesDFS, Node node) {
		PriorityQueue<Node> q = node.next;
		while (!q.isEmpty()) {
			Node next = q.poll();
			int nextNo = next.no;
			if (!visitedDFS[nextNo]) {
				visitedDFS[nextNo] = true;
				sb.append(nextNo).append(' ');
				dfs(nodesDFS, next);
			}
		}
	}

}
