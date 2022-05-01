import java.io.*;
import java.util.*;

public class BOJ11725_트리의부모찾기 {
	static class Node {
		int val;
		Node parent;
		ArrayList<Node> link;

		public Node(int val) {
			this.val = val;
			this.link = new ArrayList<>();
		}

	}

	static boolean[] visited;
	static Node[] nodes;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = stoi(br.readLine());
		nodes = new Node[n + 1];
		visited = new boolean[n + 1];
		for (int i = 1; i <= n; ++i) {
			nodes[i] = new Node(i);
		}
		for (int i = 1; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			int first = stoi(st.nextToken());
			int second = stoi(st.nextToken());
			nodes[first].link.add(nodes[second]);
			nodes[second].link.add(nodes[first]);
		}
		makeTree();
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= n; ++i) {
			sb.append(nodes[i].parent.val).append('\n');
		}
		System.out.println(sb);
		br.close();
	}

	private static void makeTree() {
		LinkedList<Node> q = new LinkedList<>();
		q.add(nodes[1]);
		visited[1] = true;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int curVal = cur.val;
			ArrayList<Node> link = cur.link;
			int len = link.size();
			for (int i = 0; i < len; ++i) {
				Node next = link.get(i);
				int nextVal = next.val;
				if (!visited[nextVal]) {
					next.parent = cur;
					visited[nextVal] = true;
					q.add(nodes[nextVal]);
				}
			}
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
