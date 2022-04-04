import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11441번. 합 구하기
public class BOJ11441_Sum {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] data = new int[n+1];
		int[] sum = new int[n+1];

		StringTokenizer st = new StringTokenizer(br.readLine());;
		for (int i = 1; i <= n; i++) {
			data[i] = Integer.parseInt(st.nextToken());
			sum[i] = sum[i-1] + data[i];
		}
		
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			System.out.println(getSum(start, end, sum));
		}
	}
	
	public static int getSum(int start, int end, int[] sum) {
		return sum[end] - sum[start-1];
	}
}
