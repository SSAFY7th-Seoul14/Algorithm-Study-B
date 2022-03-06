import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1647_도시분할계획 {
	static class Bridge implements Comparable<Bridge> {
		int from, to, price;

		public Bridge(int from, int to, int price) {
			this.from = from;
			this.to = to;
			this.price = price;
		}

		@Override
		public int compareTo(Bridge o) {
			return this.price - o.price;
		}

//		@Override
//		public String toString() {
//			return "Bridge [from=" + from + ", to=" + to + ", price=" + price + "]";
//		}

	}

	static int n, m, villageCnt;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 집의 개수 2 <= n <= 100,000
		n = Integer.parseInt(st.nextToken());
		// 길의 개수 1 <= m <= 1,000,000
		m = Integer.parseInt(st.nextToken());
		Bridge[] bridgeList = new Bridge[m];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			// A번 집과 B번 집 연결 유지비 C
			bridgeList[i] = new Bridge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		br.close();
		Arrays.sort(bridgeList);
//		for (Bridge bridge : bridgeList) {
//			System.out.println(bridge);
//		}
		villageCnt = n;
		parents = makeSet();
		int money = 0;
		for (int i = 0; i < m; i++) {
			if (union(bridgeList[i].from, bridgeList[i].to)) {
				money += bridgeList[i].price;
				--villageCnt;
			}
			if (villageCnt == 2)
				break;
		}
		System.out.println(money);
	}

	private static boolean union(int i, int j) {
		int iR = findSet(i);
		int jR = findSet(j);
		if (iR == jR)
			return false;
		parents[jR] = iR;
		return true;
	}

	private static int findSet(int i) {
		if (parents[i] == i)
			return i;
		return parents[i] = findSet(parents[i]);
	}

	private static int[] makeSet() {
		int[] parents = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}
		return parents;
	}

}