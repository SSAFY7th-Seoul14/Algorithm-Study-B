import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA6808 {

	static int[] kyuyoung, inyoung, numbers;
	static int win, lose;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			kyuyoung = new int[9];
			for (int i = 0; i < 9; i++) {
				kyuyoung[i] = Integer.parseInt(st.nextToken());
			}
			inyoung = new int[9];
			int index = 0;
			for (int i = 1; i <= 18; i++) {
				boolean flag = false;
				for (int num : kyuyoung) {
					if (num == i)
						flag = true;
				}
				if (flag)
					continue;
				inyoung[index++] = i;
			}
			numbers = new int[9];
			win = 0;
			lose = 0;
			permu(0, 0);
			sb.append(win).append(" ").append(lose).append("\n");
		}
		br.close();
		System.out.println(sb);
	}

	private static void permu(int cnt, int flag) {
		if (cnt == 9) {
			int kyuScore = 0;
			int inScore = 0;
			for (int i = 0; i < 9; i++) {
				if (kyuyoung[i] > numbers[i]) {
					kyuScore += kyuyoung[i] + numbers[i];
				} else {
					inScore += kyuyoung[i] + numbers[i];
				}
			}
			if (kyuScore > inScore) {
				win++;
			} else if (kyuScore < inScore) {
				lose++;
			}
			return;
		}
		for (int i = 0; i < 9; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			numbers[cnt] = inyoung[i];
			permu(cnt + 1, flag | 1 << i);
		}
	}

}