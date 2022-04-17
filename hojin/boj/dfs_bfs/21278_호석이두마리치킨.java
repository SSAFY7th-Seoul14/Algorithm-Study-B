import java.io.*;
import java.util.*;

public class BOJ21278_호석이두마리치킨 {
	static class Point {
		int no, dist;

		public Point(int no, int dist) {
			this.no = no;
			this.dist = dist;
		}
	}

	static int[][] adjMat;
	static int min = Integer.MAX_VALUE, n;
	static int[] minArr = new int[2];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		adjMat = new int[n + 1][n + 1];
		int m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			adjMat[A][B] = 1;
			adjMat[B][A] = 1;
		}
		combi(0, 1);
		System.out.printf("%d %d %d", minArr[0], minArr[1], min);
		br.close();
	}

	static int[] selected = new int[2];

	private static void combi(int cnt, int start) {
		if (cnt == 2) {
			checkMin();
			return;
		}
		for (int i = start; i <= n; ++i) {
			selected[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}

	static LinkedList<Point> q = new LinkedList<>();
	static boolean[] visited;

	private static void checkMin() {
		visited = new boolean[n + 1];
		q.add(new Point(selected[0], 0));
		visited[selected[0]] = true;
		q.add(new Point(selected[1], 0));
		visited[selected[1]] = true;
		int sum = 0;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			int no = cur.no;
			int dist = cur.dist;
			sum += dist;
			for (int i = 1; i <= n; ++i) {
				if (!visited[i] && adjMat[no][i] > 0) {
					visited[i] = true;
					q.add(new Point(i, dist + 2));
				}
			}
		}
		if (sum < min) {
			minArr[0] = selected[0];
			minArr[1] = selected[1];
			min = sum;
		}
	}

}
