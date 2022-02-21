import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10163 {

	static int[][] map = new int[1001][1001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			for (int j = r; j < r + w; j++) {
				for (int k = c; k < c + h; k++) {
					map[j][k] = i;
				}
			}
		}
		int[] cnt = new int[n + 1];
		for (int index = 1; index <= n; index++) {
			for (int i = 0; i < 1001; i++) {
				for (int j = 0; j < 1001; j++) {
					if (map[i][j] == index) {
						cnt[index]++;
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			sb.append(String.format("%d%n", cnt[i]));
		}
		System.out.println(sb);
	}

}