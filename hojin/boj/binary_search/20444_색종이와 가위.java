import java.util.*;
import java.io.*;

public class BOJ20444_색종이와가위 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long n = Long.parseLong(st.nextToken());
		long k = Long.parseLong(st.nextToken());
		long left = 0;
		long right = n / 2;
		while (left <= right) {
			long mid = (left + right) / 2;
			long compare = (mid + 1) * (n - mid + 1);
			if (compare == k) {
				System.out.println("YES");
				return;
			} else if (compare > k) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println("NO");
		br.close();
	}

}
