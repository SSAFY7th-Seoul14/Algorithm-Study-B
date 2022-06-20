import java.util.*;
import java.io.*;

public class BOJ13397_구간나누기2 {

	static int MAX = 10001, MIN = -1;
	static int ans = MAX;
	static int[] arr;
	static int m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		int left = 0;
		int right = -1;
		for (int i = 0; i < n; ++i) {
			int el = stoi(st.nextToken());
			right = Math.max(right, el);
			arr[i] = el;
		}
		binarySearch(left, right);
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
		br.close();
	}

	private static void binarySearch(int left, int right) {
		while (left <= right) {
			int mid = (left + right) / 2;
			if (check(mid)) {
				ans = Math.min(ans, mid);
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
	}

	private static boolean check(int val) {
		int max = MIN;
		int min = MAX;
		int cnt = 0;
		for (int i : arr) {
			int tmpMax = Math.max(max, i);
			int tmpMin = Math.min(min, i);
			if (tmpMax - tmpMin <= val) {
				max = tmpMax;
				min = tmpMin;
			} else {
				max = i;
				min = i;
				cnt++;
			}
			if (cnt >= m)
				return false;
		}
		return true;
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
