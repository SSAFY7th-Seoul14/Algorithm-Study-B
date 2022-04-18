import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15651_N과M3 {
	static int n, m;
	static int[] numbers, numbersM;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		numbers = new int[m];
		permu(0, 0);
		System.out.println(sb);
		br.close();
	}

	private static void permu(int cnt, int flag) {
		if (cnt == m) {
			for (int i = 0; i < m; ++i) {
				sb.append(numbers[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		for (int i = 1; i <= n; ++i) {
			numbers[cnt] = i;
			permu(cnt + 1, flag | 1 << i);
		}
	}

}