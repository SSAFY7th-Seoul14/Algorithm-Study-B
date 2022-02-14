import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ3040 {

	static BufferedReader br;
	static StringBuilder sb;
	static int[] dwarfs;
	static int i, sum;
	static boolean[] isSelected = new boolean[9];

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		dwarfs = new int[9];
		for (i = 0; i < 9; i++) {
			dwarfs[i] = Integer.parseInt(br.readLine());
		}
		combination(0, 0);
		System.out.println(sb);
	}

	private static void combination(int cnt, int start) {
		if (cnt == 7) {
			sum = 0;
			for (i = 0; i < 9; i++) {
				if (isSelected[i]) {
					sum += dwarfs[i];
				}
			}
			if (sum == 100) {
				for (i = 0; i < 9; i++) {
					if (isSelected[i]) {
						sb.append(dwarfs[i]).append("\n");
					}
				}
			}
			return;
		}
		for (i = start; i < 9; i++) {
			isSelected[i] = true;
			combination(cnt + 1, i + 1);
			isSelected[i] = false;
		}
	}
}