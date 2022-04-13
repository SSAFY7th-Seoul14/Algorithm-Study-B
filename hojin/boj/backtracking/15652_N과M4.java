import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15652_N과M4 {
	static StringBuilder sb;
	static int n, m;
	static int[] numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		numbers = new int[m];
		combi(0, 1);
		System.out.println(sb);
		br.close();
	}

	private static void combi(int cnt, int start) {
		if (cnt == m) {
			for (int i = 0; i < m; ++i) {
					sb.append(numbers[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		for (int i = start; i <= n; ++i) {
			numbers[cnt] = i;
			combi(cnt + 1, i);
		}
	}

}
