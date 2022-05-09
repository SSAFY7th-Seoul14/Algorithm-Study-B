import java.util.*;
import java.io.*;

public class BOJ2559_수열 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 2 이상 100,000 이하
		int n = stoi(st.nextToken());
		// K는 1과 N 사이의 정수
		int k = stoi(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		int max = Integer.MIN_VALUE;
		int sumK = 0;
		for (int i = 0; i < n; ++i) {
			arr[i] = stoi(st.nextToken());
			if (i < k - 1) {
				sumK += arr[i];
			} else if (i == k - 1) {
				sumK += arr[i];
				max = sumK;
			} else {
				sumK -= arr[i - k];
				sumK += arr[i];
				max = Math.max(max, sumK);
			}
		}
		System.out.println(max);
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
