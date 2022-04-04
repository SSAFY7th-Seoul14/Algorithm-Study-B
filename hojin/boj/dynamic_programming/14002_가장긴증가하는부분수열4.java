import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ14002_가장긴증가하는부분수열4 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		int[] LIS = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		int max = 0;
		int indexMax = 0;
		for (int i = 0; i < n; ++i) {
			LIS[i] = 1;
			for (int j = 0; j < i; ++j) {
				if (A[j] < A[i] && LIS[i] < LIS[j] + 1) {
					LIS[i] = LIS[j] + 1;
				}
			}
			if (max < LIS[i]) {
				max = LIS[i];
				indexMax = i;
			}
		}
		Stack<int[]> stack = new Stack<>();
		stack.add(new int[] { A[indexMax], indexMax });
		for (int i = indexMax - 1; i >= 0; --i) {
			int toInput = A[i];
			int[] head = stack.peek();
			if (head[0] > toInput && LIS[i] == LIS[head[1]] - 1) {
				stack.add(new int[] { toInput, i });
			}
		}
		System.out.println(max);
		while (!stack.isEmpty()) {
			System.out.print(stack.pop()[0] + " ");
		}
		br.close();
	}

}