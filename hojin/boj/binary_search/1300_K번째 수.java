import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1300_k번째수 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		int left = 1;
		int right = k;
		int answer = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			// 개수 세어주는 것이 핵심
			long cnt = 0;
			for (int i = 1; i <= n; ++i) {
				cnt += Math.min(n, mid / i);
			}
			if (cnt < k) {
				left = mid + 1;
			} else {
				right = mid - 1;
				// k가 mid 값의 lowerBound보다 클 경우는 해당하는 경우이므로 answer에 저장
				answer = mid;
			}
		}
		System.out.println(answer);
		br.close();
	}

}
