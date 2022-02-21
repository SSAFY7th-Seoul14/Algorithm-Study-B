// 25분
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2961 {

	static int n, sour = 1, bitter, minDiff = Integer.MAX_VALUE, tmpDiff;
	static int[][] pairs;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		pairs = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			pairs[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		}
		for (int r = 1; r <= n; r++) {
			combination(0, 0, r);
		}
		System.out.println(minDiff);
	}
	private static void combination(int cnt, int start, int r) {
		if (cnt == r) {
			tmpDiff = Math.abs(sour - bitter);
			if (tmpDiff < minDiff) {
				minDiff = tmpDiff;
			}
			return;
		}
		for (int i = start; i < n; i++) {
			sour *= pairs[i][0];
			bitter += pairs[i][1];
			combination(cnt + 1, i + 1, r);
			sour /= pairs[i][0];
			bitter -= pairs[i][1];
		}
	}
}