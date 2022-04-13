import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA4013_특이한자석 {
	static class Magnet {
		int[] list;
		int curTop;
		int dir;
		int nth;

		public Magnet(int[] list, int nth) {
			this.list = list;
			this.curTop = 0;
			this.dir = 0;
			this.nth = nth;
		}

		public void rotate() {
			this.curTop += -this.dir + 8;
			this.curTop %= 8;
			this.dir = 0;
		}
	}

	static Magnet[] magnets;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; ++tc) {
			int k = Integer.parseInt(br.readLine());
			magnets = new Magnet[4];
			for (int i = 0; i < 4; ++i) {
				st = new StringTokenizer(br.readLine());
				int[] tmpList = new int[8];
				for (int j = 0; j < 8; ++j) {
					tmpList[j] = Integer.parseInt(st.nextToken());
				}
				magnets[i] = new Magnet(tmpList, i);
			}
			for (int i = 0; i < k; ++i) {
				st = new StringTokenizer(br.readLine());
				bfs(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
				for (int j = 0; j < 4; ++j) {
					magnets[j].rotate();
				}
			}
			int answer = 0;
			for (int i = 0; i < 4; ++i) {
				answer += magnets[i].list[magnets[i].curTop] == 1 ? Math.pow(2, i) : 0;
			}
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
		br.close();
	}

	public static void bfs(int nth, int dir) {
		LinkedList<Magnet> q = new LinkedList<>();
		boolean[] visited = new boolean[4];
		Magnet start = magnets[nth];
		visited[start.nth] = true;
		start.dir = dir;
		q.offer(start);
		while (!q.isEmpty()) {
			Magnet cur = q.poll();
			int curDir = cur.dir;
			int curNth = cur.nth;
			int nextNth = curNth - 1;
			if (0 <= nextNth) {
				Magnet next = magnets[nextNth];
				if (next.list[(next.curTop + 2) % 8] != cur.list[(cur.curTop + 6) % 8] && !visited[nextNth]) {
					visited[nextNth] = true;
					next.dir = -curDir;
					q.offer(next);
				}
			}
			nextNth = curNth + 1;
			if (nextNth < 4) {
				Magnet next = magnets[nextNth];
				if (next.list[(next.curTop + 6) % 8] != cur.list[(cur.curTop + 2) % 8] && !visited[nextNth]) {
					visited[nextNth] = true;
					next.dir = -curDir;
					q.offer(next);
				}
			}
		}
	}
}
