import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 2018번. 수들의 합 5
public class BOJ2018_SumOfNumbers5 {
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// 처리
		int count = 1;
		
		int left = 1;
		int right = 2;
		int sum = 3;
		while (left <= n/2) {
			if (sum < n) {
				right++;
				sum += right;
			} else if (sum > n) {
				sum -= left;
				left++;
			} else { // 같을 때
				count++;
				
				sum -= left;
				left++;
			}
		}
		
		System.out.println(count);
	}
}
