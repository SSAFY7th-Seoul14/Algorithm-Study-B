import java.util.*;
import java.io.*;

public class BOJ12865_평범한배낭 {
	static class Item implements Comparable<Item> {
		int weight, value;

		public Item(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		@Override
		public int compareTo(Item o) {
			return this.weight == o.weight ? o.value - this.value : this.weight - o.weight;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = stoi(st.nextToken());
		int k = stoi(st.nextToken());
		Item[] items = new Item[n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			items[i] = new Item(stoi(st.nextToken()), stoi(st.nextToken()));
		}
		Arrays.sort(items, 1, n + 1);
		int[][] dp = new int[n + 1][k + 1];
		for (int i = 1; i <= n; i++) {
			Item cur = items[i];
			int curW = cur.weight;
			int curV = cur.value;
			for (int j = 1; j <= k; j++) {
				if (curW <= j) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - curW] + curV);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		System.out.println(dp[n][k]);
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
