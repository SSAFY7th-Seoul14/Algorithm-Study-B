import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JOL1037 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n + 1][n + 1];

		StringTokenizer st;
		int[] cntR = new int[n + 1];
		int[] cntC = new int[n + 1];
		int rOddIndex = 0;
		int cOddIndex = 0;
		boolean failure = false;
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
				cntR[i] += tmp;
			}
			// 행 홀수 index 체크
			if (cntR[i] % 2 == 1) {
				if (rOddIndex != 0) {
					// 1. 어디든 홀수가 2개 이상이면 실패
					failure = true;
					continue;
				}
				rOddIndex = i;
			}
		}
		if (!failure) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					cntC[i] += map[j][i];
				}
				if (cntC[i] % 2 == 1) {
					if (cOddIndex != 0) {
						// 1. 어디든 홀수가 2개 이상이면 실패
						failure = true;
						break;
					}
					cOddIndex = i;
				}
			}
		}
		if (failure) {
			System.out.println("Corrupt");
		} else {
			// 3. 홀수가 없으면 OK
			if (cOddIndex == 0 && rOddIndex == 0) {
				System.out.println("OK");
			} else {
				// 2. 홀수가 1개씩만 있다면 change
				System.out.println(String.format("Change bit (%d, %d)", rOddIndex, cOddIndex));
			}
		}
	}

}