import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3238_이항계수구하기 {

	static long[] facto;
	static int p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		facto = new long[100001];
		facto[0] = 1;
		facto[1] = 1;
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; ++tc) {
			st = new StringTokenizer(br.readLine());
			long n = Long.parseLong(st.nextToken());
			long r = Long.parseLong(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			for (int i = 2; i < p; ++i) {
				facto[i] = (facto[i - 1] * i) % p;
			}
			long answer = lucas(n, r);
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
		br.close();
	}

	private static long lucas(long n, long r) {
		if (n < p)
			return nCr((int) n, (int) r);
		int nn = (int) (n % p);
		int rr = (int) (r % p);
		if (nn < rr)
			return 0;
		return nCr(nn, rr) * lucas(n / p, r / p) % p;
	}

	private static long nCr(int n, int r) {
		long bottom = (facto[n - r] * facto[r]) % p;
		bottom = pow(bottom, p - 2);
		return (facto[n] * bottom) % p;
	}

	private static long pow(long num, long exp) {
		if (exp == 1) {
			return num;
		} else if (exp == 0) {
			return 1;
		}
		long answer = pow(num, exp / 2);
		if (exp % 2 == 0) {
			return (answer * answer) % p;
		} else {
			return (((answer * answer) % p) * num) % p;
		}
	}
}