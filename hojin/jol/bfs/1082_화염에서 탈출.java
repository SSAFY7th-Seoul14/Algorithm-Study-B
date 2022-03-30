import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class JOL1082_화염에서탈출 {
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[] end;
	static int n, m, time;
	static boolean[][] visited;
	static char[][] map;
	static LinkedList<int[]> qFire = new LinkedList<>(), qPerson = new LinkedList<>();

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		visited = new boolean[n][m];
		time = 0;

		for (int i = 0; i < n; ++i) {
			String line = br.readLine();
			for (int j = 0; j < m; ++j) {
				char inp = line.charAt(j);
				if (inp == 'S') {
					qPerson.add(new int[] { i, j });
					visited[i][j] = true;
				} else if (inp == 'D') {
					end = new int[] { i, j };
				} else if (inp == '*') {
					qFire.add(new int[] { i, j });
				}
				map[i][j] = inp;
			}
		}
		if (bfs()) {
			bw.write(String.format("%d", time));
		} else {
			bw.write("impossible");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean bfs() {
		while (!qFire.isEmpty() || !qPerson.isEmpty()) {
			// 각각의 q에 대해 각 time별 bfs 수행
			int fireCurrentSize = qFire.size();
			int personSize = qPerson.size();
			while (fireCurrentSize > 0) {
				int[] curFire = qFire.poll();
				for (int i = 0; i < 4; ++i) {
					int nR = curFire[0] + delta[i][0];
					int nC = curFire[1] + delta[i][1];
					if (rangeCheck(nR, nC) && fireAvailable(nR, nC) && map[nR][nC] != '*') {
						map[nR][nC] = '*';
						qFire.add(new int[] { nR, nC });
					}
				}
				--fireCurrentSize;
			}
			while (personSize > 0) {
				int[] curPerson = qPerson.poll();
				if (curPerson[0] == end[0] && curPerson[1] == end[1])
					return true;
				for (int i = 0; i < 4; ++i) {
					int nR = curPerson[0] + delta[i][0];
					int nC = curPerson[1] + delta[i][1];
					if (rangeCheck(nR, nC) && personAvailable(nR, nC) && !visited[nR][nC]) {
						visited[nR][nC] = true;
						qPerson.add(new int[] { nR, nC });
					}
				}
				--personSize;
			}
			++time;
		}
		return false;
	}

	public static boolean personAvailable(int r, int c) {
		if (map[r][c] == 'X' || map[r][c] == '*')
			return false;
		return true;
	}

	public static boolean fireAvailable(int r, int c) {
		if (map[r][c] == 'X' || map[r][c] == 'D')
			return false;
		return true;
	}

	public static boolean rangeCheck(int r, int c) {
		if (0 <= r && r < n && 0 <= c && c < m)
			return true;
		return false;
	}
}