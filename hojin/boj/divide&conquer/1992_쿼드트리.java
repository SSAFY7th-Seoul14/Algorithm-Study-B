import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1992 {
	static char[][] video;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		video = new char[n][n];
		for (int i = 0; i < n; i++) {
			video[i] = br.readLine().toCharArray();
		}
		br.close();
		System.out.println(compress(0, 0, n));
	}

	private static String compress(int row, int col, int length) {

		if (length == 1) {
			return Character.toString(video[row][col]);
		}
		StringBuilder tmp = new StringBuilder();

		tmp.append(compress(row, col, length / 2));
		tmp.append(compress(row, col + length / 2, length / 2));
		tmp.append(compress(row + length / 2, col, length / 2));
		tmp.append(compress(row + length / 2, col + length / 2, length / 2));

		if (tmp.toString().equals("0000") || tmp.toString().equals("1111")) {
			tmp.setLength(tmp.length() - 3);
			return tmp.toString();
		} else {
			return "(" + tmp.toString() + ")";
		}
	}

}