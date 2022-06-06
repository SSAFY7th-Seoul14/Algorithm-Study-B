import java.util.*;
import java.io.*;

public class BOJ12100_2048Easy {
	static int n;
	static int[][] delta = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = stoi(br.readLine());
		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		for (int i = 0; i < 4; i++) {
			int[][] cMap = copyMap(map);
			nextDir(i, cMap, 1);
		}
		System.out.println(max);
	}

	private static void nextDir(int d, int[][] map, int cnt) {
		if (cnt > 5) {
			calcScore(map);
			return;
		}
		Queue<Integer> q = new LinkedList<Integer>();
		// 움직이고
		switch (d) {
		case 1:
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] > 0) {
						q.add(map[i][j]);
						map[i][j] = 0;
					}
				}
				int j = 0;
				while (!q.isEmpty()) {
					map[i][j] = q.poll();
					if (!q.isEmpty() && map[i][j] == q.peek()) {
						map[i][j] += q.poll();
					}
					j++;
				}
			}
			break;
		case 2:
			for (int j = 0; j < n; j++) {
				for (int i = 0; i < n; i++) {
					if (map[i][j] > 0) {
						q.add(map[i][j]);
						map[i][j] = 0;
					}
				}
				int i = 0;
				while (!q.isEmpty()) {
					map[i][j] = q.poll();
					if (!q.isEmpty() && map[i][j] == q.peek()) {
						map[i][j] += q.poll();
					}
					i++;
				}
			}
			break;
		case 3:
			for (int i = 0; i < n; i++) {
				for (int j = n - 1; j >= 0; j--) {
					if (map[i][j] > 0) {
						q.add(map[i][j]);
						map[i][j] = 0;
					}
				}
				int j = n - 1;
				while (!q.isEmpty()) {
					map[i][j] = q.poll();
					if (!q.isEmpty() && map[i][j] == q.peek()) {
						map[i][j] += q.poll();
					}
					j--;
				}
			}
			break;
		default:
			for (int j = 0; j < n; j++) {
				for (int i = n - 1; i >= 0; i--) {
					if (map[i][j] > 0) {
						q.add(map[i][j]);
						map[i][j] = 0;
					}
				}
				int i = n - 1;
				while (!q.isEmpty()) {
					map[i][j] = q.poll();
					if (!q.isEmpty() && map[i][j] == q.peek()) {
						map[i][j] += q.poll();
					}
					i--;
				}
			}
			break;
		}
		// 다음 이동으로
		for (int i = 0; i < 4; i++) {
			int[][] cMap = copyMap(map);
			nextDir(i, cMap, cnt + 1);
		}
	}

	private static void calcScore(int[][] map) {
		for (int[] is : map) {
			for (int i : is) {
				max = Math.max(max, i);
			}
		}
	}

	private static int[][] copyMap(int[][] map) {
		int[][] cMap = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cMap[i][j] = map[i][j];
			}
		}
		return cMap;
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
