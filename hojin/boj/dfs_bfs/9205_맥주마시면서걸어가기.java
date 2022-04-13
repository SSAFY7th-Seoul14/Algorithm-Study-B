import java.util.*;
import java.io.*;

public class BOJ9205_맥주마시면서걸어가기 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static Point from, to;
	static Point[] convenStore;
	static LinkedList<Point> q;
	static boolean[] visited;
	static int n;
	static boolean isHappy;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; ++tc) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			from = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			convenStore = new Point[n];
			visited = new boolean[n];
			for (int i = 0; i < n; ++i) {
				st = new StringTokenizer(br.readLine());
				convenStore[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			to = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			bfs();
			if (isHappy)
				sb.append("happy\n");
			else
				sb.append("sad\n");
		}
		System.out.println(sb);
		br.close();
	}

	private static void bfs() {
		q = new LinkedList<>();
		q.offer(from);
		while (!q.isEmpty()) {
			Point curP = q.poll();
			if (isAvailable(curP, to)) {
				isHappy = true;
				return;
			}
			for (int i = 0; i < n; ++i) {
				if (!visited[i] && isAvailable(curP, convenStore[i])) {
					visited[i] = true;
					q.offer(convenStore[i]);
				}
			}
		}
		isHappy = false;
	}

	private static boolean isAvailable(Point curP, Point nextP) {
		double between = Math.abs(curP.r - nextP.r) + Math.abs(curP.c - nextP.c);
		double needBeer = Math.ceil(between / 50.0);
		if (needBeer > 20)
			return false;
		else
			return true;
	}

}
