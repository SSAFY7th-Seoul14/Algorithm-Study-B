
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ17471_게리맨더링 {

	static class Node {
		int no, population;
		Node to;

		public Node(int no, int population, Node to) {
			this.no = no;
			this.population = population;
			this.to = to;
		}

	}

	static int n;
	static boolean[] selected;
	static int[] populations;
	static int[][] adjMat;
	static int maxPopul;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		populations = new int[n];
		selected = new boolean[n];
		maxPopul = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			populations[i] = Integer.parseInt(st.nextToken());
			maxPopul += populations[i];
		}
		adjMat = new int[n][n];
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			int linkCnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < linkCnt; ++j) {
				int inp = Integer.parseInt(st.nextToken()) - 1;
				adjMat[i][inp] = 1;
			}
		}
		subset(0);
		if (min == Integer.MAX_VALUE || min == maxPopul)
			min = -1;
		System.out.println(min);
	}

	private static void subset(int cnt) {
		if (cnt == n) {
			if (checkPossible())
				connect();
			return;
		}
		selected[cnt] = true;
		subset(cnt + 1);
		selected[cnt] = false;
		subset(cnt + 1);
	}

	private static boolean checkPossible() {
		LinkedList<Integer> qA = new LinkedList<Integer>();
		LinkedList<Integer> qB = new LinkedList<Integer>();
		boolean[] visitedA = new boolean[n];
		boolean[] visitedB = new boolean[n];
		for (int i = 0; i < n; ++i) {
			if (selected[i] && qA.isEmpty()) {
				visitedA[i] = true;
				qA.add(i);
			} else if (!selected[i] && qB.isEmpty()) {
				visitedB[i] = true;
				qB.add(i);
			}
		}
		bfs(qA, visitedA, true);
		bfs(qB, visitedB, false);
		for (int i = 0; i < n; ++i) {
			if (selected[i]) {
				if (!visitedA[i])
					return false;
			} else {
				if (!visitedB[i])
					return false;
			}
		}
		return true;
	}

	private static void bfs(LinkedList<Integer> q, boolean[] visited, boolean flag) {
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < n; ++i) {
				if (adjMat[cur][i] == 1 && selected[i] == flag && !visited[i]) {
					visited[i] = true;
					q.add(i);
				}
			}
		}
	}

	private static void connect() {
		int sumA = 0;
		for (int i = 0; i < n; ++i) {
			if (selected[i])
				sumA += populations[i];
		}
		min = Math.min(min, Math.abs(maxPopul - 2 * sumA));
	}

}
