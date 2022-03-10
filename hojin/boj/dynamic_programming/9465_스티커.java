import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9465_스티커 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(br.readLine());
			int[][] stickers = new int[2][n + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				stickers[0][i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				stickers[1][i] = Integer.parseInt(st.nextToken());
			}
			int max = 0;
			for (int i = 2; i <= n; i++) {
				stickers[0][i] += Math.max(stickers[1][i - 2], stickers[1][i - 1]);
				stickers[1][i] += Math.max(stickers[0][i - 2], stickers[0][i - 1]);
			}
			max = Math.max(stickers[0][n], stickers[1][n]);
			sb.append(max).append("\n");
		}
		br.close();
		System.out.println(sb);
	}

}