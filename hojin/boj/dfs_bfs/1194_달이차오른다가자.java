import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ1194_달이차오른다가자 {

	static class Node {
		int curR, curC, moveCnt, key;

		public Node(int curR, int curC, int key, int moveCnt) {
			this.curR = curR;
			this.curC = curC;
			this.key = key;
			this.moveCnt = moveCnt;
		}

	}

	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int n, m;
	static char[][] maze;

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		Node minsik = null;
		maze = new char[n][m];
		for (int i = 0; i < n; ++i) {
			maze[i] = br.readLine().toCharArray();
			for (int j = 0; j < m; ++j) {
				if (maze[i][j] == '0') {
					minsik = new Node(i, j, 0, 0);
					maze[i][j] = '.';
				}
			}
		}
		br.close();
		bfs(minsik);
	}

	public static void bfs(Node minsik) {
		LinkedList<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[n][m][64];
		visited[minsik.curR][minsik.curC][0] = true;
		q.offer(minsik);
		while (!q.isEmpty()) {
			Node curNode = q.poll();
			int cnt = curNode.moveCnt;
			int key = curNode.key;
			if (maze[curNode.curR][curNode.curC] == '1') {
				System.out.println(curNode.moveCnt);
				return;
			}
			for (int i = 0; i < 4; ++i) {
				int nR = curNode.curR + delta[i][0];
				int nC = curNode.curC + delta[i][1];
				if (rangeCheck(nR, nC)) {
					char curP = maze[nR][nC];
					if (curP == '#' || visited[nR][nC][key])
						continue;
					Node next = new Node(nR, nC, key, cnt + 1);
					if ('a' <= curP && curP <= 'f') {
						int curKey = curP - 'a';
						if ((key & 1 << curKey) == 0) {
							next.key |= 1 << curKey;
						}
					} else if ('A' <= curP && curP <= 'F') {
						if ((key & 1 << (curP - 'A')) == 0) {
							continue;
						}
					}
					visited[nR][nC][next.key] = true;
					q.offer(next);
				}
			}
		}
		System.out.println("-1");
	}

	public static boolean rangeCheck(int r, int c) {
		if (0 <= r && r < n && 0 <= c && c < m)
			return true;
		return false;
	}

}