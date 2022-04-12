import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1654_랜선자르기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 랜선의 개수 1<= K <= 10,000
		int k = Integer.parseInt(st.nextToken());
		// 필요한 랜선의 개수 1<= N <= 1,000,000 K <= N
		int n = Integer.parseInt(st.nextToken());
		long right = 0;
		int[] lanLines = new int[k];
		for (int i = 0; i < k; ++i) {
			int inp = Integer.parseInt(br.readLine());
			if (inp > right)
				right = inp;
			lanLines[i] = inp;
		}
		Arrays.sort(lanLines);
		long left = 1;
		while (left <= right) {
			long mid = (left + right) / 2;
			int sum = 0;
			for (int i = k - 1; i >= 0; --i) {
				int div = lanLines[i] / (int) mid;
				if (div == 0)
					break;
				sum += div;
			}
			if (sum < n) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(right);
		br.close();
	}

}
