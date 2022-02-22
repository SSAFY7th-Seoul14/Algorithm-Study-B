import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[] visited;
	static int[][] contactsList;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			contactsList = new int[len][101];
			for (int i = 0; i < len / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				contactsList[from][to] = 1;
			}
			visited = new int[101];
			answer = 0;
			bfs(start);

			sb.append(String.format("#%d %d%n", tc, answer));
		}

		System.out.println(sb);
	}

	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		int maxCnt = 0;
		q.offer(start);
		visited[start] = 1;

		while (!q.isEmpty()) {
			int current = q.poll();
			for (int i = 1; i < 101; i++) {
				if (visited[i] != 0 || contactsList[current][i] != 1)
					continue;

				visited[i] = visited[current] + 1;
				q.offer(i);
			}
			maxCnt = visited[current];
		}

		for (int i = 1; i < 101; i++) {
			if (maxCnt != visited[i])
				continue;
			answer = Math.max(i, answer);
		}
	}

}
