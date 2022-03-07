import java.io.*;
import java.util.*;

class pair implements Comparable<pair> {
	int first, second;

	public pair(int x, int y) {
		this.first = x;
		this.second = y;
	}

	@Override
	public int compareTo(pair o) {
		return this.second - o.second;
	}
}

public class Main {
	static int n, m, ans1, ans2;
	static int arr[][] = new int[101][101];
	static boolean visit[][] = new boolean[101][101];
	static Queue<pair> q = new LinkedList<pair>();
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };

	// 색맹일때랑 아닐때랑 똑같이 bfs돌지만 조건만 다르게 설정
	static void bfs(boolean flag) {
		int x, y, nx, ny;
		while (!q.isEmpty()) {
			x = q.peek().first;
			y = q.peek().second;
			q.poll();
			for (int i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
					if (visit[nx][ny])
						continue;
					// 정상일때
					if (!flag) {
						if (arr[nx][ny] == arr[x][y]) {
							visit[nx][ny] = true;
							q.add(new pair(nx, ny));
						}
					}
					// 색맹일때
					else {
						// 파란색만 따로 구별
						if ((arr[x][y] == arr[nx][ny]) || (arr[x][y] != 3 && arr[nx][ny] != 3)) {
							visit[nx][ny] = true;
							q.add(new pair(nx, ny));
						}
					}
				}
			}
		}
		return;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		String s;
		char c;
		for (int i = 0; i < n; i++) {
			s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				c = s.charAt(j);
				if (c == 'R')
					arr[i][j] = 1;
				else if (c == 'G')
					arr[i][j] = 2;
				else
					arr[i][j] = 3;
			}
		}
		int r = 0, g = 0, b = 0;

		// 이중 for문 돌면서 아직 방문하지 않은 지점을 찾아 bfs로 영역을 찾아낸다
		// 처음엔 정상 조건으로 bfs
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visit[i][j]) {
					q.add(new pair(i, j));
					visit[i][j] = true;
					bfs(false);
					if (arr[i][j] == 1)
						r++;
					else if (arr[i][j] == 2)
						g++;
					else
						b++;
				}
			}
		}
		// 방문 배열 초기화 후 재사용
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				visit[i][j] = false;
		}
		ans1 = r + g + b;
		r = g = b = 0;

		// 이번엔 색맹인 조건으로 다시 bfs
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visit[i][j]) {
					q.add(new pair(i, j));
					visit[i][j] = true;
					bfs(true);
					if (arr[i][j] == 1)
						r++;
					else if (arr[i][j] == 2)
						g++;
					else
						b++;
				}
			}
		}
		ans2 = r + g + b;
		System.out.println(ans1 + " " + ans2);
	}
}
