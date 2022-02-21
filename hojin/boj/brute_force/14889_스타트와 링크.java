import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ14889 {

	static int n, min;
	static int[][] S;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		S = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int data = Integer.parseInt(st.nextToken());
				S[i][j] = data;
			}
		}
		br.close();
		min = Integer.MAX_VALUE;
		combination(0, 0, 0);
		bw.write(min + "\n");
		bw.close();
	}

	private static void combination(int cnt, int start, int flag) {
		if (cnt == n / 2) {
			sumMethod(flag);
			return;
		}
		for (int i = start; i < n; i++) {
			combination(cnt + 1, i + 1, flag | 1 << i);
		}
	}

	private static void sumMethod(int flag) {
		int sumA = 0, sumB = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if ((flag & 1 << i) != 0 && (flag & 1 << j) != 0) {
					sumA += S[i][j] + S[j][i];
				} else if ((flag & 1 << i) == 0 && (flag & 1 << j) == 0) {
					sumB += S[i][j] + S[j][i];
				}
			}
		}
		min = Math.min(min, Math.abs(sumA - sumB));
	}

}