import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ21610_마법사상어와비바라기 {
	static class Command {
		int dir, dist;

		public Command(int dir, int dist) {
			this.dir = dir;
			this.dist = dist;
		}
	}

	static class Clouds {
		int locR, locC;

		public Clouds(int locR, int locC) {
			this.locR = locR;
			this.locC = locC;
		}

		public void work() {
			++A[locR][locC];
			cloudsDisappear[locR][locC] = true;
		}

		public void move(int dir, int dist) {
			locR += dist * delta[dir][0];
			locC += dist * delta[dir][1];
			while (locR < 0) {
				locR += n;
			}
			while (locC < 0) {
				locC += n;
			}
			locR %= n;
			locC %= n;
		}

		public void waterCopy() {
			for (int i = 1; i < 8; i++) {
				int nR = locR + delta[i][0];
				int nC = locC + delta[i][1];
				if (rangeCheck(nR, nC)) {
					if (A[nR][nC] > 0) {
						++A[locR][locC];
					}
				}
				++i;
			}
		}

		private boolean rangeCheck(int nR, int nC) {
			if (0 <= nR && nR < n && 0 <= nC && nC < n)
				return true;
			return false;
		}
	}

	static int[][] delta = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } }, A;
	static int n, m, sum;
	static Command[] commands;
	static boolean[][] cloudsDisappear;

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		A = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		commands = new Command[m];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			commands[i] = new Command(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
		}
		bibaragi();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sum += A[i][j];
			}
		}
		System.out.println(sum);
		br.close();
	}

	private static void bibaragi() {
		ArrayList<Clouds> q = new ArrayList<Clouds>();
		q.add(new Clouds(n - 1, 0));
		q.add(new Clouds(n - 1, 1));
		q.add(new Clouds(n - 2, 0));
		q.add(new Clouds(n - 2, 1));
		cloudsDisappear = new boolean[n][n];
		for (int i = 0; i < m; i++) {
			int size = q.size();
			for (int j = 0; j < size; j++) {
				q.get(j).move(commands[i].dir, commands[i].dist);
			}
			for (int j = 0; j < size; j++) {
				q.get(j).work();
			}
			for (int j = 0; j < size; j++) {
				q.get(j).waterCopy();
			}
			q.clear();
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if (A[j][k] >= 2 && !cloudsDisappear[j][k]) {
						q.add(new Clouds(j, k));
						A[j][k] -= 2;
					}
				}
			}
			cloudsDisappear = new boolean[n][n];
		}
	}

}