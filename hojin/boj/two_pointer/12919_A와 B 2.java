import java.io.*;

public class BOJ12919_A와B2 {
	static boolean success = false;
	static char[] S, T;
	static int lenS;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine().toCharArray();
		T = br.readLine().toCharArray();
		lenS = S.length;
		int lenT = T.length;
		int left = 0;
		int right = lenT - 1;
		BT(left, right, lenT);
		if (success)
			System.out.println("1");
		else
			System.out.println("0");
		br.close();
	}

	private static void BT(int left, int right, int lenT) {
		boolean flag = left < right;
		if (lenS == lenT) {
			int index = 0;
			if (flag) {
				for (int i = left; i <= right; ++i)
					if (S[index++] != T[i])
						return;

			} else {
				for (int i = left; i >= right; --i)
					if (S[index++] != T[i])
						return;
			}
			success = true;
			return;
		}
		if (T[left] == 'B') {
			if (flag)
				BT(right, left + 1, lenT - 1);
			else
				BT(right, left - 1, lenT - 1);
			if (success)
				return;
		}
		if (T[right] == 'A') {
			if (flag)
				BT(left, right - 1, lenT - 1);
			else
				BT(left, right + 1, lenT - 1);
			if (success)
				return;
		}
	}

}
