import java.util.*;
import java.io.*;

public class BOJ2001_보석줍기 {

	// 방문 정보를 들고 다닐 Point
	static class Point {
		// 다음 섬으로 넘어가기 전에 몇번째 섬인지
		int ith,
				// 보석은 몇개 들고 있는지
				jewel;

		public Point(int ith, int jewel) {
			this.ith = ith;
			this.jewel = jewel;
		}

	}

	static int n, m, k;
	static ArrayList<int[]>[] islands;
	static int[] jewelry;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 섬 개수 n
		n = stoi(st.nextToken());
		// 다리 개수 m
		m = stoi(st.nextToken());
		// 보석 개수 k
		k = stoi(st.nextToken());
		islands = new ArrayList[n + 1];
		for (int i = 1; i <= n; ++i) {
			islands[i] = new ArrayList<>();
		}
		jewelry = new int[n + 1];
		for (int i = 1; i <= k; ++i) {
			// n개의 섬 중에 어떤 섬에 몇번째 보석이 있나 정보 담기
			// 0보다 크면 보석이 있는 것
			jewelry[stoi(br.readLine())] = i;
		}
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = stoi(st.nextToken());
			int b = stoi(st.nextToken());
			int maxW = stoi(st.nextToken());
			// 다리 정보 담기
			islands[a].add(new int[] { b, maxW });
			islands[b].add(new int[] { a, maxW });
		}
		System.out.println(bfs());
		br.close();
	}

	private static int bfs() {
		// 방문 배열 생성. 비트마스킹으로 방문 확인 할 것이기 때문에 2^(k+1)크기 만큼 만들기
		boolean[][] visited = new boolean[n + 1][1 << (k + 1)];
		LinkedList<Point> q = new LinkedList<>();
		q.offer(new Point(1, 0));
		visited[1][0] = true;
		int answer = 0;
		while (!q.isEmpty()) {
			Point curP = q.poll();
			int curI = curP.ith;
			int jewel = curP.jewel;
			// 몇번째 보석에 방문인지 여부를 비트마스킹으로 해둬서 1이 몇개인지 세어줘야 한다.
			int curCnt = count(jewel);
			if (curI == 1) {
				answer = Math.max(answer, curCnt);
			}
			for (int[] info : islands[curI]) {
				int nextNo = info[0];
				int stiff = info[1];
				// 다리를 건널 수 있을 경우만 넘어가기
				if (curCnt <= stiff && !visited[nextNo][jewel]) {
					// 보석이 있을 경우 보석을 들고 넘어갈 수 있고
					if (jewelry[nextNo] > 0) {
						int nextJ = jewel | 1 << jewelry[nextNo];
						q.offer(new Point(nextNo, nextJ));
						visited[nextNo][nextJ] = true;
					}
					// 보석이 없으면 지금 상태 그대로 넘어가기
					q.offer(new Point(nextNo, jewel));
					visited[nextNo][jewel] = true;
				}
			}
		}
		return answer;
	}

	private static int count(int jewel) {
		int cnt = 0;
		for (int i = 1; i <= k; ++i) {
			if ((jewel & (1 << i)) > 0)
				++cnt;
		}
		return cnt;
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
