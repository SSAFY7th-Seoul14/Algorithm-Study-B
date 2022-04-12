import java.io.*;
import java.util.*;

// BOJ / 소수 구하기 / S3 / 15분
// https://www.acmicpc.net/problem/1929
public class Main_1929 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		boolean isPrime[] = new boolean[N + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false; // 0,1은 소수 X

		for (int i = 2; i <= N; i++) {
			for (int j = 2; j <= N; j++) {
				if (i * j > N) // 범위 벗어날 경우
					break;
				if (!isPrime[i * j]) // 소수 아닌 경우(예전에 처리)
					continue;
				isPrime[i * j] = false;
			}
		}

		for (int i = M; i <= N; i++) {
			if (isPrime[i]) // 소수인 경우
				System.out.println(i);
		}
	}

}
