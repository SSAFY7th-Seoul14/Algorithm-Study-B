import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5607_조합 {

	static int DIV = 1234567891;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		long[] dp = new long[1000001];
		dp[1] = 1;
		for (int i = 2; i <= 1000000; ++i) {
			dp[i] = ((dp[i - 1]) * i) % DIV;
		}
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; ++tc) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			long bottom = (dp[n - r] * dp[r]) % DIV;
			bottom = pow(bottom, DIV - 2);
			sb.append('#').append(tc).append(' ').append((dp[n] * bottom) % DIV).append('\n');
		}
		System.out.println(sb);
		br.close();
	}

	private static long pow(long num, int exp) {
		long answer;
		if (exp == 1) {
			return num;
		}
		answer = pow(num, exp / 2);
		if (exp % 2 == 0) {
			return (answer * answer) % DIV;
		} else {
			return (((answer * answer) % DIV) * num) % DIV;
		}
	}

}
