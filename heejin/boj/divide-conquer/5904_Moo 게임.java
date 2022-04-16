import java.io.*;
import java.util.*;

// BOJ / Moo 게임 / S1 / 70분
// https://www.acmicpc.net/problem/5904
public class Main_5904 {
	static int N;
	static int dp[]; // Moo 수열의 길이
	static char res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[50];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 3;
		int k = 0;
		while (dp[k] <= N) { // Moo 수열 길이 구하기
			k++;
			dp[k] = dp[k - 1] * 2 + (k + 2) + 1;
		}
		Moo(N);
		System.out.println(res);

	}

	private static void Moo(int n) {
		if (n == 1) {
			res = 'm';
			return;
		}
		if (n == 2 || n == 3) {
			res = 'o';
			return;
		}

		int k = 0;

		while (dp[k] < n)
			k++;

		int mid = dp[k - 1] + k + 3; // S(k)= S(k-1)+ moo.. + S(k-1)에서 중간 moo... 끝의 o자리
		if (mid < n) // dp[k-1]에서 처리
			Moo(n - mid);
		else if (n == dp[k - 1] + 1)
			res = 'm';
		else
			res = 'o';

	}
}
