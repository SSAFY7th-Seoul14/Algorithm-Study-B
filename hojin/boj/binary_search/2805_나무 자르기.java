import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2805_나무자르기 {

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] trees = new int[n];
		st = new StringTokenizer(br.readLine());
		int maxTreeH = 0;
		for (int i = 0; i < n; ++i) {
			int eachH = Integer.parseInt(st.nextToken());
			if (eachH > maxTreeH)
				maxTreeH = eachH;
			trees[i] = eachH;
		}
		Arrays.sort(trees);
		// 높이값 이분 탐색
		int ans = binarySearch(trees, m, maxTreeH);
		System.out.println(ans);
		br.close();
	}

	private static int binarySearch(int[] trees, int target, int maxTreeH) {
		int left = 0;
		int right = maxTreeH - 1;
		int mid = 0;
		while (left <= right) {
			mid = (left + right) / 2;
			long compareH = findTarget(trees, mid, trees.length);
			if (compareH < target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}

	private static long findTarget(int[] trees, int mid, int n) {
		long sum = 0;
		for (int i = n - 1; i >= 0; --i) {
			if (trees[i] > mid) 
				sum += trees[i] - mid;
			else
				break;
		}
		return sum;
	}

}