import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10815_숫자카드 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 1 ≤ N ≤ 500,000
		int n = Integer.parseInt(br.readLine());
		int[] cards = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i)
			cards[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(cards);
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; ++i)
			sb.append(binarySearch(cards, Integer.parseInt(st.nextToken()), n)).append(' ');
		System.out.println(sb);
		br.close();
	}

	private static int binarySearch(int[] cards, int target, int n) {
		int left = 0;
		int right = n - 1;
		int mid;
		while (left <= right) {
			mid = (left + right) / 2;
			if (cards[mid] == target)
				return 1;
			else if (cards[mid] < target)
				left = mid + 1;
			else
				right = mid - 1;
		}
		return 0;
	}

}
