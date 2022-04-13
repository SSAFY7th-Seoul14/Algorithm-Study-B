import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15650_N과M2 {
	static StringBuilder sb;
	static int n, m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		combi(0, 1, 0);
		System.out.println(sb);
		br.close();
	}

	private static void combi(int cnt, int start, int flag) {
		if (cnt == m) {
			for (int i = 1; i <= n; ++i) {
				if ((flag & 1 << i) > 0)
					sb.append(i).append(' ');
			}
			sb.append('\n');
			return;
		}
		for (int i = start; i <= n; ++i) {
			if ((flag & 1 << i) > 0)
				continue;
			combi(cnt + 1, i + 1, flag | 1 << i);
		}
	}

}
