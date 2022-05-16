import java.io.*;
import java.util.*;

public class BOJ2470_두용액 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] numbers = new int[n];
		for (int i = 0; i < n; ++i) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numbers);
		int isZero = 2000000001;
		int ansL = 0, ansR = 0;
		int left = 0;
		int right = n - 1;
		while (left < right) {
			int sum = numbers[left] + numbers[right];
			if (sum == 0) {
				ansL = left;
				ansR = right;
				break;
			}
			int sumAbs = Math.abs(sum);
			if (sumAbs < isZero) {
				isZero = sumAbs;
				ansL = left;
				ansR = right;
			}
			if (Math.abs(numbers[left] + numbers[right - 1]) < Math.abs(numbers[left + 1] + numbers[right])) {
				--right;
			} else {
				++left;
			}
		}
		System.out.printf("%d %d", numbers[ansL], numbers[ansR]);
		br.close();
	}

}
