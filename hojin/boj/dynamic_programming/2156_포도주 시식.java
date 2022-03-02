// 해당 블로그 참고 https://st-lab.tistory.com/135
// 1시간 해결

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2156 {

	static int[] dp, wines;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 포도잔의 개수 n
		int n = Integer.parseInt(br.readLine());
		wines = new int[n + 1];
		dp = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			wines[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		Arrays.fill(dp, -1);
		if (n == 0) {
			System.out.println(wines[n]);
		} else {
			dp[0] = wines[0];
			dp[1] = wines[1];
			if (n < 2) {
				System.out.println(dp[n]);
			} else {
				dp[2] = wines[1] + wines[2];
				System.out.println(DP(n));
			}
		}

	}

	private static int DP(int n) {

		if (dp[n] == -1) {
			dp[n] = Math.max(Math.max(DP(n - 3) + wines[n - 1], DP(n - 2)) + wines[n], DP(n - 1));
		}

		return dp[n];
	}

}


// 30분 시간 초과
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int max = 0, n;
	static boolean[] selected;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int[] wines = new int[n];
		for (int i = 0; i < n; i++) {
			wines[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		selected = new boolean[n];
		if (n < 3) {
			for (int i : wines) {
				max += i;
			}
		} else {
			DP(wines, 0);
		}

		System.out.println(max);
	}

	private static void DP(int[] wines, int amount) {
		if (amount > max)
			max = amount;
		for (int i = 0; i < n; i++) {
			if (!selected[i] && isAvailable(i)) {
				selected[i] = true;
				DP(wines, amount + wines[i]);
				selected[i] = false;
			}
		}
	}

	private static boolean isAvailable(int num) {
		if (num >= 2 && num <= n - 3) {
			if ((selected[num - 2] && selected[num - 1]) || (selected[num + 1] && selected[num + 2])
					|| (selected[num - 1] && selected[num + 1])) {
				return false;
			}
		} else if (num == 1) {
			if ((selected[num + 1] && selected[num + 2]) || (selected[num - 1] && selected[num + 1])) {
				return false;
			}
		} else if (num == n - 2) {
			if ((selected[num - 1] && selected[num - 2]) || (selected[num - 1] && selected[num + 1])) {
				return false;
			}
		} else if (num == 0) {
			if (selected[num + 1] && selected[num + 2]) {
				return false;
			}
		} else if (num == n - 1) {
			if (selected[num - 1] && selected[num - 2]) {
				return false;
			}
		}
		return true;
	}

}
