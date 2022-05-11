import java.util.*;
import java.io.*;

public class BOJ15566_개구리1 {
	static class Frog implements Comparable<Frog> {
		int[] interest;
		int fav1, fav2;
		int nth, seat;

		public Frog(int nth, int food, int hobby, int fam, int philo) {
			this.interest = new int[5];
			this.nth = nth;
			this.interest[1] = food;
			this.interest[2] = hobby;
			this.interest[3] = fam;
			this.interest[4] = philo;
			this.seat = -1;
		}

		@Override
		public int compareTo(Frog o) {
			return this.seat - o.seat;
		}
	}

	static boolean[] selected;
	static int n;
	static Frog[] frogs;
	static int[][] logs;
	static StringBuilder sb = new StringBuilder();
	static boolean success = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		int m = stoi(st.nextToken());
		frogs = new Frog[n + 1];
		selected = new boolean[n + 1];
		// 개구리 입력 받기
		for (int i = 1; i <= n; ++i) {
			st = new StringTokenizer(br.readLine());
			frogs[i] = new Frog(i, stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()),
					stoi(st.nextToken()));
		}
		// 같은 자리에 앉아야하는 개구리가 둘일 경우 불가능하므로 이를 체크
		boolean wrong = false;
		for (int i = 1; i <= n; ++i) {
			st = new StringTokenizer(br.readLine());
			int fav1 = stoi(st.nextToken());
			frogs[i].fav1 = fav1;
			int fav2 = stoi(st.nextToken());
			frogs[i].fav2 = fav2;
			if (fav1 == fav2) {
				// 90%에서 계속 틀리던 부분은 여기에서 문제가 있었음... 같은 장소에 앉고 싶은 애들이 있을 경우에는 아예 dfs 들어갈 필요도 없는데
				if (selected[fav1]) {
					wrong = true;
				}
				frogs[i].seat = fav1;
				selected[fav1] = true;
			}
		}
		logs = new int[n + 1][n + 1];
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			int subject = stoi(st.nextToken());
			logs[from][to] = subject;
		}
		// 입력부터 불가능하면 dfs를 들어가지도 않고 넘어가기
		if (!wrong)
			dfs(1);
		if (!success)
			sb.append("NO");
		System.out.println(sb);
		br.close();
	}

	private static void dfs(int cur) {
		if (!success && cur > n) {
			// 개구리 다 배치하고 나면
			if (check()) {
				sb.append("YES\n");
				Arrays.sort(frogs, 1, n + 1);
				for (int i = 1; i <= n; ++i) {
					sb.append(frogs[i].nth).append(' ');
				}
				success = true;
			}
			return;
		}
		// cur번째 개구리 자리가 배정되지 않으면
		if (frogs[cur].seat == -1) {
			// -1인 개구리는 fav가 다름이 보장
			int fav1 = frogs[cur].fav1;
			if (!selected[fav1]) {
				selected[fav1] = true;
				frogs[cur].seat = fav1;
				dfs(cur + 1);
				if (success)
					return;
				frogs[cur].seat = -1;
				selected[fav1] = false;
			}
			int fav2 = frogs[cur].fav2;
			if (!selected[fav2]) {
				selected[fav2] = true;
				frogs[cur].seat = fav2;
				dfs(cur + 1);
				if (success)
					return;
				frogs[cur].seat = -1;
				selected[fav2] = false;
			}
		}
		// 이미 자리가 워져 있으면 개구리로 넘어가기
		else {
			dfs(cur + 1);
			if (success)
				return;
		}
	}

	private static boolean check() {
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (logs[i][j] > 0) {
					if (!canTalk(i, j)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private static boolean canTalk(int r, int c) {
		int sub = logs[r][c];
		Frog f1 = null;
		Frog f2 = null;
		for (int i = 1; i <= n; ++i) {
			if (frogs[i].seat == r) {
				f1 = frogs[i];
			}
			if (frogs[i].seat == c) {
				f2 = frogs[i];
			}
		}
		if (f1.interest[sub] == f2.interest[sub])
			return true;
		else
			return false;
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
