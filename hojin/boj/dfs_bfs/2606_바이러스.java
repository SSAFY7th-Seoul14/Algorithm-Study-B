import java.io.*;
import java.util.*;

public class BOJ2606_바이러스 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		ArrayList<Integer>[] list = new ArrayList[n];
		for (int i = 0; i < n; ++i) {
			list[i] = new ArrayList<>();
		}
		int pairs = Integer.parseInt(br.readLine());
		for (int i = 0; i < pairs; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			list[from].add(to);
			list[to].add(from);
		}
		System.out.println(bfs(0, n, list));
	}

	private static int bfs(int start, int n, ArrayList<Integer>[] list) {
		boolean[] visited = new boolean[n];
		Queue<Integer> q = new LinkedList<>();
		visited[start] = true;
		q.offer(start);
		int cnt = 0;
		while (!q.isEmpty()) {
			Integer cur = q.poll();
			for (Integer i : list[cur]) {
				if (!visited[i]) {
					visited[i] = true;
					q.offer(i);
					++cnt;
				}
			}
		}
		return cnt;
	}

}
