import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ4485_녹색옷입은애가젤다지 {

	static class Vertex implements Comparable<Vertex> {
		int r, c, minDist;

		public Vertex(int r, int c, int minDist) {
			super();
			this.r = r;
			this.c = c;
			this.minDist = minDist;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.minDist - o.minDist;
		}
	}

	static int[][] delta = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }, map, costs;
	static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		// test case 번호
		int tc = 1;
		while (true) {
			n = Integer.parseInt(br.readLine());
			if (n == 0) {
				break;
			}
			// 위치의 비용이 담겨있는 map 배열
			map = new int[n][n];
			// 정점까지의 최소 비용이 담겨 있는 costs 배열
			costs = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 모든 정점으로의 비용을 무한대로 갱신
					costs[i][j] = Integer.MAX_VALUE;
				}
			}
			// 다익스트라 실행
			dijkstra();
			bw.write(String.format("Problem %d: %d%n", tc++, costs[n - 1][n - 1]));
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static void dijkstra() {
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		// 출발점 비용 입력
		costs[0][0] = map[0][0];
		pq.offer(new Vertex(0, 0, costs[0][0]));

		while (!pq.isEmpty()) {
			Vertex cur = pq.poll();
			int r = cur.r;
			int c = cur.c;
			// 현재 정점을 기준으로 4방 탐색
			for (int i = 0; i < 4; i++) {
				int nR = r + delta[i][0];
				int nC = c + delta[i][1];
				if (rangeCheck(nR, nC)) {
					// 탐색한 인접 정점(nR, nC)까지의 현재 비용이 현재 점(r, c)에서 이동하는 비용(map[nR][nC])보다 크다면
					if (costs[nR][nC] > costs[r][c] + map[nR][nC]) {
						// 정점의 최소 비용을 갱신
						costs[nR][nC] = costs[r][c] + map[nR][nC];
						pq.offer(new Vertex(nR, nC, costs[nR][nC]));
					}
				}
			}
		}

		for (int[] is : costs) {
			System.out.println(Arrays.toString(is));
		}
		System.out.println();
	}

	private static boolean rangeCheck(int r, int c) {
		if (0 <= r && r < n && 0 <= c && c < n)
			return true;
		return false;
	}

}