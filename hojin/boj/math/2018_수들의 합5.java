import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class BOJ2018_수들의합5 {

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 1 <= <= 10,000,000
		int n = Integer.parseInt(br.readLine());
		// n의 약수 중에서 홀수의 개수 구하기
		LinkedList<Integer> divisors = new LinkedList<Integer>();
		int limit = (int) Math.pow(n, 0.5);
		for (int i = 1; i <= limit; i++) {
			if (n % i == 0) {
				if ((n / i) % 2 == 1)
					divisors.add(n / i);
				if ((n/i) == i) continue;
				if (i % 2 == 1)
					divisors.add(i);
			}
		}
		System.out.println(divisors.size());
	}

}
