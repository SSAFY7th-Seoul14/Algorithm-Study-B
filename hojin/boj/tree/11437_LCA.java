import java.util.*;
import java.io.*;

public class BOJ11437_LCA {

	static int n;
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	static int[] depth;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		n = stoi(br.readLine());
		list = new ArrayList[n + 1];
		visited = new boolean[n + 1];
		depth = new int[n + 1];
		parents = new int[n + 1];
		for (int i = 1; i <= n; ++i) {
			list[i] = new ArrayList<>();
		}
		// 노드간 연결은 list로 받아놓기
		for (int i = 0; i < n - 1; ++i) {
			st = new StringTokenizer(br.readLine());
			int first = stoi(st.nextToken());
			int second = stoi(st.nextToken());
			list[second].add(first);
			list[first].add(second);
		}
		// root 처리
		visited[1] = true;
		depth[1] = 0;
		parents[1] = 0;
		makeTree(1, 1);
		int m = stoi(br.readLine());
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int from1 = stoi(st.nextToken());
			int from2 = stoi(st.nextToken());
			sb.append(LCA(from1, from2)).append('\n');
		}
		System.out.println(sb);
		br.close();
	}

	// dfs로 tree 생성
	private static void makeTree(int cur, int nextDepth) {
		// 현재 노드에 연결된 노드에 대해서
		for (Integer next : list[cur]) {
			// 트리에 포함되지 않았다면 방문하지 않았다면
			if (!visited[next]) {
				// 방문처리
				visited[next] = true;
				// dfs가 다음 깊이를 들고 있으므로 node에 해당하는 번호에 depth 기록
				depth[next] = nextDepth;
				// 부모는 하나뿐인 트리, 부모는 cur로 다음 node에 기록
				parents[next] = cur;
				makeTree(next, nextDepth + 1);
			}
		}
	}

	private static int LCA(int from1, int from2) {
		// 둘의 depth가 다르다면
		while (depth[from1] != depth[from2]) {
			// 두 탐색의 depth가 같아질때까지 처리
			if (depth[from1] < depth[from2])
				from2 = parents[from2];
			else
				from1 = parents[from1];
		}
		// 같은 depth부터는
		while (from1 != from2) {
			// 계속 부모를 따라간다.
			from1 = parents[from1];
			from2 = parents[from2];
		}
		// 같은 번호를 찾았다면 반환
		return from1;
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}