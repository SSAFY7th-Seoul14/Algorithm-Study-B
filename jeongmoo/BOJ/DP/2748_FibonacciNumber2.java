import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 2748번. 피보나치 수 2
public class BOJ2748_FibonacciNumber2 {
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// 계산
		long result = fibonacci(n);
		
		// 출력
		System.out.println(result);
	}
	
	static long fibo[] = new long[91];
	public static long fibonacci(int n) {
		if (n<2)
			return n;
		
		if (fibo[n] != 0)
			return fibo[n];
		
		return fibo[n] = fibonacci(n-1) + fibonacci(n-2);
	}
}
