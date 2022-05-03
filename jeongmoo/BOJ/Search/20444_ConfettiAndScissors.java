import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 20444번. 색종이와 가위
public class BOJ20444_ConfettiAndScissors {
	static long n, k;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Long.parseLong(st.nextToken());
		k = Long.parseLong(st.nextToken());
		
		// n은 2^31승. 약 20억? O(n)만 해도 0.1초는 불가능
		// 수식을 만들던지. O(logn)으로 나눠야함.
		// k가 2^63인걸보니 약간 이분 탐색 느낌이긴 하다.
		
		// 가로 + 세로 = n + 2
		// 최소 크기 = 1 x (n+1)
		// 최대 크기 = (n+2/2) x ..
		long left = 1;
		long right = (n+2)/2;
		while (left <= right) {
			long mid = (left+right)/2;
			long cnt = mid * (n+2-mid);
			if (cnt == k) {
				System.out.println("YES");
				return;
			} else if (cnt < k) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println("NO");
	}
}
