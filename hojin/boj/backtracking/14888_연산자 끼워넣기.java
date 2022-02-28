import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ14888 {

	static int[] arr;
	static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// + - * /
		int[] operators = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		br.close();

		// operators를 가지고 순열 돌리기
		dfs(operators, 1, n, arr[0]);

		bw.write(String.format("%d%n%d", max, min));
		bw.flush();
		bw.close();
	}

	private static void dfs(int[] operators, int cnt, int n, int sum) {
		if (cnt == n) {
			if (sum > max)
				max = sum;
			if (sum < min)
				min = sum;
			return;
		}
		for (int i = 0; i < 4; i++) {
			int tmpSum = sum;
			if (operators[i] > 0) {
				switch (i) {
				case 0:
					tmpSum += arr[cnt];
					break;
				case 1:
					tmpSum -= arr[cnt];
					break;
				case 2:
					tmpSum *= arr[cnt];
					break;
				case 3:
					tmpSum /= arr[cnt];
					break;
				}
				--operators[i];
				dfs(operators, cnt + 1, n, tmpSum);
				++operators[i];
			}
		}
	}

}