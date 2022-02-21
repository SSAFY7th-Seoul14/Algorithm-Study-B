import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1182번. 부분수열의 합
public class BOJ1182_SumOfSubsequences {
	static int n, s, arr[];
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		subset(0, 0, 0);
		System.out.println(result);
	}
	
	// 부분수열의 합이기 때문에 부분집합 중 아무것도 고르지않는 경우의 수는 빼야함
	public static void subset(int idx, int sum, int count) {
		if(idx == n) {
			if (sum == s && count > 0)
				result++;
			return;
		}
		
		subset(idx+1, sum+arr[idx], count+1);
		subset(idx+1, sum, count);
	}
}
