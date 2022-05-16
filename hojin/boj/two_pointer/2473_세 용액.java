import java.io.*;
import java.util.*;

public class BOJ2473_세용액 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] numbers = new long[n];
		int ansL = 0, ansR = 0, ansC = 0;
		long isZero = Long.MAX_VALUE;
		for (int i = 0; i < n; ++i) {
			numbers[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(numbers);
		for (int i = 0; i < n; ++i) {
			int left = i + 1;
			int right = n - 1;
			while (left < right) {
				long sum = numbers[i] + numbers[left] + numbers[right];
				if (sum == 0) {
					System.out.printf("%d %d %d", numbers[i], numbers[left], numbers[right]);
					return;
				}
				long sumAbs = Math.abs(sum);
				if (sumAbs < isZero) {
					isZero = sumAbs;
					ansL = i;
					ansC = left;
					ansR = right;
				}
				if (sum < 0) {
					++left;
				} else {
					--right;
				}
			}
		}
		System.out.printf("%d %d %d", numbers[ansL], numbers[ansC], numbers[ansR]);
		br.close();
	}

}
