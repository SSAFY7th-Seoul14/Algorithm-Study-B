import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2512_예산 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 3<= <=10,000
		int n = Integer.parseInt(br.readLine());
		// 입력값 중 최대는 100000
		int max = 0;
		int[] budget = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int input = Integer.parseInt(st.nextToken());
			if (input > max)
				max = input;
			budget[i] = input;
		}
		long m = Long.parseLong(br.readLine());
		int min = 1;
		long sum = 0;
		while (min <= max) {
			sum = 0;
			int mid = (min + max) / 2;
			for (int i = 0; i < n; i++) {
				if (mid < budget[i]) {
					sum += mid;
				} else {
					sum += budget[i];
				}
			}
			if (sum > m) {
				max = mid - 1;
			} else {
				min = mid + 1;
			}
		}
		System.out.println(max);
	}

}