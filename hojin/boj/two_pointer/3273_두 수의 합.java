import java.util.*;
import java.io.*;

public class BOJ3273_두수의합 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = stoi(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = stoi(st.nextToken());
		}
		Arrays.sort(arr);
		int x = stoi(br.readLine());
		int ans = 0;
		int l = 0;
		int r = n - 1;
		while (l < r) {
			int sum = arr[l] + arr[r];
			if (sum == x) {
				ans++;
				l++;
				r--;
			} else if (sum > x)
				r--;
			else
				l++;

		}
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
		br.close();
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
