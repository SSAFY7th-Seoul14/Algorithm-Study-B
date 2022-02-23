import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA1251 {
	static class Edge {
		int r, c;
		double lenSqure;

		Edge(int r, int c, double lenSquare) {
			this.r = r;
			this.c = c;
			this.lenSqure = lenSquare;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[][] list = new int[n][2];
			for (int i = 0; i < n; i++) {
				list[i][0] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				list[i][1] = Integer.parseInt(st.nextToken());
			}
			double E = Double.parseDouble(br.readLine());
			double charge = 0;
			// n-1개에 해당하는 kruskal
			// E * (dx* dx + dy+dy)
			int linkLen = n * (n - 1) / 2;
			Edge[] links = makeEdges(list, n);
			// 환경세율은 같으므로 길이의 제곱 만큼 정렬하자
			Arrays.sort(links, (a, b) -> {
				return (int) (a.lenSqure - b.lenSqure);
			});
			// makeSet
			int[] parent = makeSet(linkLen);
			// union 하고
			int cnt = 0;
			for (int i = 0; i < linkLen; i++) {
				if (union(parent, links[i].r, links[i].c)) {
					charge += E * links[i].lenSqure;
					cnt++;
				}
				if (cnt == n - 1)
					break;
			}
			// find하고
			long ans = Math.round(charge);
			sb.append(String.format("#%d %d%n", tc, ans));
		}
		br.close();
		System.out.println(sb);
	}

	private static boolean union(int[] parent, int i, int j) {
		int pi = find(parent, i);
		int pj = find(parent, j);
		if (pi == pj)
			return false;
		parent[pj] = pi;
		return true;
	}

	private static int find(int[] parent, int i) {
		if (parent[i] == i)
			return i;
		return parent[i] = find(parent, parent[i]);
	}

	private static int[] makeSet(int n) {
		int[] set = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			set[i] = i;
		}
		return set;
	}

	private static Edge[] makeEdges(int[][] list, int n) {
		Edge[] link = new Edge[n * (n - 1) / 2];
		int index = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				double lenSquare = Math.pow(Math.abs(list[i][0] - list[j][0]), 2)
						+ Math.pow(Math.abs(list[i][1] - list[j][1]), 2);
				link[index++] = new Edge(i, j, lenSquare);
			}
		}
		return link;
	}
}