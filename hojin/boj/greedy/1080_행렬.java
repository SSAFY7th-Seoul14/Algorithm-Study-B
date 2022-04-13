import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1080_행렬 {

	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char[][] A = new char[n][m];
		for (int i = 0; i < n; ++i)
			A[i] = br.readLine().toCharArray();
		char[][] B = new char[n][m];
		for (int i = 0; i < n; ++i)
			B[i] = br.readLine().toCharArray();
		boolean isSame = true;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (A[i][j] != B[i][j]) {
					isSame = false;
					break;
				}
			}
		}
		if (isSame) {
			System.out.println("0");
		} else {
			if (n < 3 || m < 3)
				System.out.println("-1");
			else {
				map = new int[n][m];
				for (int i = 0; i < n; ++i) {
					for (int j = 0; j < m; ++j) {
						map[i][j] = Math.abs(A[i][j] - B[i][j]);
					}
				}
				int nMinus3 = n - 3;
				int mMinus3 = m - 3;
				int cnt = 0;
				for (int i = 0; i <= nMinus3; ++i) {
					for (int j = 0; j <= mMinus3; ++j) {
						if (map[i][j] == 1) {
							flip(i, j);
							++cnt;
						}
					}
				}
				System.out.println(check(n, m) ? cnt : "-1");
			}
		}
		br.close();
	}

	private static boolean check(int n, int m) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (map[i][j] == 1)
					return false;
			}
		}
		return true;
	}

	private static void flip(int r, int c) {
		for (int i = r; i < r + 3; ++i) {
			for (int j = c; j < c + 3; ++j)
				map[i][j] ^= 1;
		}
	}

}
