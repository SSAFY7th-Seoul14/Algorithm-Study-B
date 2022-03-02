import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ10026_적록색약 {

	static class Node {
		char rgb;
		int r, c;

		public Node(char rgb, int r, int c) {
			super();
			this.rgb = rgb;
			this.r = r;
			this.c = c;
		}

	}

	static char[][] map;
	static boolean[][] visited1, visited2;
	static int n, cnt1, cnt2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		map = new char[n][];
		visited1 = new boolean[n][n];
		visited2 = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		br.close();
		cnt1 = cnt2 = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited1[i][j]) {
					visited1[i][j] = true;
//					dfsDefault(map[i][j], i, j);
					bfs(map[i][j], i, j);
					++cnt1;
				}
				if (!visited2[i][j]) {

					dfsRG(map[i][j], i, j);
					++cnt2;
				}
			}
		}
		System.out.println(String.format("%d %d", cnt1, cnt2));
	}

	private static void bfs(char rgb, int r, int c) {
		Queue<Node> q = new LinkedList<Node>();
		q.offer(new Node(rgb, r, c));

		while (!q.isEmpty()) {
			Node current = q.poll();
			for (int i = 0; i < 4; i++) {
				int nR = current.r + delta[i][0];
				int nC = current.c + delta[i][1];
				if (0 <= nR && nR < n && 0 <= nC && nC < n && map[nR][nC] == current.rgb && !visited1[nR][nC]) {
					visited1[nR][nC] = true;
					q.offer(new Node(current.rgb, nR, nC));
				}
			}
		}
	}

	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private static void dfsDefault(char rgb, int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nR = r + delta[i][0];
			int nC = c + delta[i][1];
			if (0 <= nR && nR < n && 0 <= nC && nC < n && map[nR][nC] == rgb && !visited1[nR][nC]) {
				visited1[nR][nC] = true;
				dfsDefault(rgb, nR, nC);
			}
		}
	}

	private static void dfsRG(char rgb, int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nR = r + delta[i][0];
			int nC = c + delta[i][1];
			if (0 <= nR && nR < n && 0 <= nC && nC < n
					&& (map[nR][nC] == rgb || (rgb == 'R' && map[nR][nC] == 'G') || (rgb == 'G' && map[nR][nC] == 'R'))
					&& !visited2[nR][nC]) {
				visited2[nR][nC] = true;
				dfsRG(rgb, nR, nC);
			}
		}
	}

}