import java.util.*;
import java.io.*;

public class BOJ2533_사회망서비스SNS {
	static class Node {
		int val;
		ArrayList<Node> link;

		public Node(int val) {
			this.val = val;
			this.link = new ArrayList<>();
		}

	}

	static boolean[] visited;
	static Node[] nodes;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		// 입력 처리 초기화
		init();
		// 문제 해결부
		solve();
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}

	private static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 친구 관계 트리의 정점 개수 N이 주어진다. 단, 2 ≤ N ≤ 1,000,000
		int n = stoi(br.readLine());
		nodes = new Node[n + 1];
		visited = new boolean[n + 1];
		for (int i = 1; i <= n; ++i) {
			nodes[i] = new Node(i);
		}
		for (int i = 1; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = stoi(st.nextToken());
			int v = stoi(st.nextToken());
			nodes[u].link.add(nodes[v]);
			nodes[v].link.add(nodes[u]);
		}
		dp = new int[n + 1][2];
	}

	private static void solve() {
		// dp[i][0] = i가 일반인
		// dp[i][1] = i가 얼리어답터
		visited[1] = true;
		makeTree(1);
	}

	private static void makeTree(int nth) {
		ArrayList<Node> link = nodes[nth].link;
		int curVal = nodes[nth].val;
		// 마지막 leaf에서는 본인이 어답터이면 1
		dp[curVal][1] = 1;
		// 아니면 0 그대로
		for (Node next : link) {
			int nextVal = next.val;
			if (!visited[nextVal]) {
				visited[nextVal] = true;
				makeTree(nextVal);
				// 본인이 일반인이면 그 전 Node가 반드시 얼리어답터여야한다.
				dp[curVal][0] += dp[nextVal][1];
				// 본인이 얼리어답터면 그 전이 어떤 사람이든 상관 없다.
				dp[curVal][1] += Math.min(dp[nextVal][0], dp[nextVal][1]);
			}
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
