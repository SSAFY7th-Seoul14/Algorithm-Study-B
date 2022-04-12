import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10816_숫자카드2 {
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// 숫자 카드의 개수 N(1 ≤ N ≤ 500,000)
		n = Integer.parseInt(br.readLine());
		int[] cards = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			// -10,000,000 <= <= 10,000,000
			cards[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cards);
		// 1 ≤ M ≤ 500,000
		int m = Integer.parseInt(br.readLine());
		int[] toCompare = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; ++i) {
			toCompare[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < m; ++i) {
			int target = toCompare[i];
			sb.append(upperBound(cards, target) - lowerBound(cards, target)).append(" ");
		}
		System.out.println(sb);
		br.close();
	}

	private static int upperBound(int[] cards, int target) {
		int left = 0;
		int right = n;
		int mid = (left + right) / 2;
		while (left < right) {
			if (cards[mid] <= target)
				left = mid + 1;
			else
				right = mid;
			mid = (left + right) / 2;
		}
		return right;
	}

	private static int lowerBound(int[] cards, int target) {
		int left = 0;
		int right = n;
		int mid = (left + right) / 2;
		while (left < right) {
			if (cards[mid] < target)
				left = mid + 1;
			else
				right = mid;
			mid = (left + right) / 2;
		}
		return right;
	}

}
