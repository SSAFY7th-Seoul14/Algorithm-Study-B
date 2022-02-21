import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA1859 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<Integer>();
		for (int tc = 1; tc <= T; tc++) {
			// 2 <= n <= 1,000,000
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] price = new int[n];
			for (int i = 0; i < n; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			long ans = 0;
			// 각각의 값들을 비교해주기 위해 우선 맨 오른쪽 집어넣고
			stack.push(price[n - 1]);
			// 오른쪽부터 stack에 넣어가면서
			for (int i = n - 2; i >= 0; i--) {
				int ith = price[i];
				int top = stack.peek();
				// 팔수 있는 경우가 갱신되는 경우
				if (ith >= top) {
					stack.pop();
					stack.push(ith);
				} else {
					// top보다 작으면 사고 팔 수 있는 경우의 수
					ans += top - ith;
				}
			}
			sb.append(String.format("#%d %d%n", tc, ans));
			stack.clear();
		}
		System.out.println(sb);
	}

}