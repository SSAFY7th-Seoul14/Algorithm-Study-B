import java.util.*;
import java.io.*;

public class BOJ2352_반도체설계 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = stoi(br.readLine());
		int[] arr = new int[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = stoi(st.nextToken());
		}
		int[] LIS = new int[n + 1];
		int index = 0;
		for (int i = 1; i <= n; i++) {
			int num = arr[i];
			if (LIS[index] > num) {
				LIS[lowerBound(LIS, index, num)] = num;
			} else {
				LIS[++index] = arr[i];
			}
		}
		System.out.println(index);
	}

	private static int lowerBound(int[] LIS, int end, int num) {
		int start = 1;
		while (start < end) {
			int mid = (start + end) / 2;
			if (LIS[mid] >= num) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return end;
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
