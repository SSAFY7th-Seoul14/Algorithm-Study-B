import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11402_이항계수4 {
	static int m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long n = Long.parseLong(st.nextToken());
		long k = Long.parseLong(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		long[] facto = new long[m];
		facto[0] = 1;
		for (int i = 1; i < m; ++i)
			facto[i] = (facto[i - 1] * i) % m;
		int answer = 1;
		int nFacto, kFacto;
		while (n > 0 && k > 0) {
			nFacto = (int) (n % m);
			kFacto = (int) (k % m);
			if (nFacto < kFacto) {
				answer = 0;
				break;
			}
			answer *= (facto[nFacto] * pow((facto[kFacto] * facto[nFacto - kFacto]) % m, m - 2)) % m;
			answer %= m;
			n /= m;
			k /= m;
		}
		System.out.println(answer);
		br.close();
	}

	private static long pow(long num, int exp) {
		if (exp <= 1)
			return (long) Math.pow(num, exp);
		long powNo = pow(num, exp / 2);
		if (exp % 2 == 1)
			return (num * powNo % m) * powNo % m;
		else
			return powNo * powNo % m;
	}

}
