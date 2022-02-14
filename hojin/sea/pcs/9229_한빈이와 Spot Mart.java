import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA9229 {
	static int[] weightsList;
	static int limit, n, currentMax;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken());
			limit = Integer.parseInt(st.nextToken());

			weightsList = new int[n];
			currentMax = -1;

			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < n; i++) {
				weightsList[i] = Integer.parseInt(st.nextToken());
			}

			combination(0, 0, 0);

			sb.append("#").append(tc).append(" ").append(currentMax).append("\n");
		}
		in.close();
		System.out.println(sb);
	}

	private static void combination(int cnt, int start, int weight) {
		if (cnt == 2) {
			if (weight <= limit) {
				currentMax = Math.max(weight, currentMax);
			}
			return;
		}
		for (int i = start; i < n; i++) {
			combination(cnt + 1, i + 1, weight + weightsList[i]);
		}
	}
}
