import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] times = new int[n][2];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			times[i][0] = Integer.parseInt(st.nextToken());
			times[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(times, (a, b) -> {
			return a[1] == b[1] ? a[0] - b[0] : a[1] - b[1];
		});
		Stack<int[]> stack = new Stack<int[]>();
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if (stack.isEmpty() || times[i][0] >= stack.peek()[1]) {
				cnt++;
				stack.push(times[i]);
			}
		}
		System.out.println(cnt);
	}
}