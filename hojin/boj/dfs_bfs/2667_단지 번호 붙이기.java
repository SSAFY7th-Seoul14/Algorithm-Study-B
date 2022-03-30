import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class BOJ2667_단지번호붙이기 {

	static char[][] map;
	static int n, cnt;
	static boolean[][] visited;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			map[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (map[i][j] == '1' && !visited[i][j]) {
					visited[i][j] = true;
					cnt = 1;
					dfs(i, j);
					pq.offer(cnt);
				}
			}
		}
		bw.write(String.valueOf(pq.size()));
		bw.newLine();
		while (!pq.isEmpty()) {
			bw.write(String.valueOf(pq.poll()));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int r, int c) {
		for (int i = 0; i < 4; ++i) {
			int nR = r + delta[i][0];
			int nC = c + delta[i][1];
			if (0 <= nR && nR < n && 0 <= nC && nC < n && map[nR][nC] == '1' && !visited[nR][nC]) {
				visited[nR][nC] = true;
				++cnt;
				dfs(nR, nC);
			}
		}
	}

}