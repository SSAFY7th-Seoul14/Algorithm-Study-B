import java.util.*;
import java.io.*;

public class BOJ19542_전단지돌리기 {

	static ArrayList<Integer>[] list;
	static int n, d, answer, s;
	static int[] depth;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 노드 개수 1 <= N <= 100000
		n = Integer.parseInt(st.nextToken());
		list = new ArrayList[n + 1];
		depth = new int[n + 1];
		for (int i = 1; i <= n; ++i) {
			list[i] = new ArrayList<>();
		}
		// 위치 1<= S <= n
		s = Integer.parseInt(st.nextToken());
		// 힘 0<= D <= n
		d = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n - 1; ++i) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list[x].add(y);
			list[y].add(x);
		}
		answer = 0;
		// 시작점과 부모가 없으므로 -1로 넣고 dfs 시작
		dfs(s, -1);
		System.out.println(2 * answer);
		br.close();
	}

	private static int dfs(int cur, int parents) {
		for (int next : list[cur]) {
			// 연결되어있는 노드가 부모면 탐색하지 않는다.
			if (next != parents) {
				// 현재 깊이는 가장 아래 노드 기준으로 정해진다.
				depth[cur] = Math.max(depth[cur], dfs(next, cur) + 1);
			}
		}
		// 리프노드부터 깊이까지 길이가 d보다 클 경우는 무조건 도달해야하는 경우이므로 더해주고, s는 출발점이므로 제외
		if (cur != s && depth[cur] >= d) {
			++answer;
		}
		return depth[cur];
	}

}