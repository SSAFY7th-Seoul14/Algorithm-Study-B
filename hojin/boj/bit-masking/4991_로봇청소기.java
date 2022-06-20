import java.util.*;
import java.io.*;

public class BOJ4991_로봇청소기 {

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	static class Person {
		Point p;
		int visited, cnt;

		public Person(Point p, int visited, int cnt) {
			this.p = p;
			this.visited = visited;
			this.cnt = cnt;
		}

	}

	static char[][] map;
	static Point start;
	static ArrayList<Point> dirtyList;
	static Queue<Person> q;
	static int[][] delta = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int ans;
	static int h, w;
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			w = stoi(st.nextToken());
			h = stoi(st.nextToken());
			if (w == 0 && h == 0)
				break;
			map = new char[h][w];
			start = null;
			dirtyList = new ArrayList<>();
			ans = INF;
			for (int i = 0; i < h; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < w; j++) {
					switch (map[i][j]) {
					case 'o':
						start = new Point(i, j);
						map[i][j] = '.';
						break;
					case '*':
						dirtyList.add(new Point(i, j));
						break;
					default:
						break;
					}
				}
			}
			bfs();
			bw.write(String.valueOf(ans == INF ? -1 : ans));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static void bfs() {
		int n = dirtyList.size();
		boolean[][][] mapVisited = new boolean[h][w][1 << n];
		q = new LinkedList<>();
		q.offer(new Person(start, 0, 0));
		while (!q.isEmpty()) {
			Person cur = q.poll();
			Point curP = cur.p;
			int r = curP.r;
			int c = curP.c;
			int curVisited = cur.visited;
			int curCnt = cur.cnt;
			for (int i = 0; i < 4; i++) {
				int nR = r + delta[i][0];
				int nC = c + delta[i][1];
				if (rangeCheck(nR, nC)) {
					int nVisited = curVisited;
					int nCnt = curCnt + 1;
					if (!mapVisited[nR][nC][curVisited]) {
						switch (map[nR][nC]) {
						case '*':
							for (int j = 0; j < n; j++) {
								Point dirtyP = dirtyList.get(j);
								if (dirtyP.r == nR && dirtyP.c == nC) {
									nVisited |= 1 << j;
									break;
								}
							}
							if (nVisited == (1 << n) - 1) {
								ans = Math.min(ans, nCnt);
								break;
							}
						case '.':
							mapVisited[nR][nC][nVisited] = true;
							q.offer(new Person(new Point(nR, nC), nVisited, nCnt));
							break;
						default:
							break;
						}
					}
				}
			}

		}
	}

	private static boolean rangeCheck(int r, int c) {
		return 0 <= r && r < h && 0 <= c && c < w;
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
