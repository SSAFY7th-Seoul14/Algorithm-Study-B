import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1759 {

	static char[] candidate;
	static int[] flag;
	static char[] conso = { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w',
			'x', 'y', 'z' };
	static char[] vowel = { 'a', 'e', 'i', 'o', 'u' };

	public static void main(String[] args) throws Exception {
		// 서로 다른 L개의 알파벳 소문자, 사용했을법한 문자 종류 C
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// L, C 입력 처리
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		// C 개의 문자 처리
		st = new StringTokenizer(br.readLine());
		candidate = new char[C];
		for (int i = 0; i < C; i++) {
			candidate[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(candidate);

		flag = new int[C];
		for (int i = C - 1; i >= L; i--) {
			flag[i] = 1;
		}
		StringBuilder sb = new StringBuilder();
		char[] tmp = new char[L];
		do {
			// flag의 0과 일치할 때 sb에 붙이기
			int index = 0;
			for (int i = 0; i < C; i++) {
				if (flag[i] == 0)
					tmp[index++] = candidate[i];
			}
			if (checkAlphbet(tmp)) {
				continue;
			} else {
				for (char d : tmp) {
					sb.append(d);

				}
				sb.append("\n");
			}
		} while (np(C));
		System.out.println(sb);
	}

	private static boolean checkAlphbet(char[] charList) {
		int cnt = 0;
		for (int i = 0; i < charList.length; i++) {

			for (int j = 0; j < vowel.length; j++) {
				if (charList[i] == vowel[j])
					cnt++;
			}
		}
		if (cnt < 1)
			return true;

		cnt = 0;
		for (int i = 0; i < charList.length; i++) {
			for (int j = 0; j < conso.length; j++) {
				if (charList[i] == conso[j])
					cnt++;
			}
		}
		if (cnt < 2)
			return true;
		return false;

	}

	private static boolean np(int C) {
		int i = C - 1;
		while (i > 0 && flag[i - 1] >= flag[i])
			--i;

		if (i == 0)
			return false;

		int j = C - 1;
		while (flag[i - 1] >= flag[j])
			--j;

		swap(i - 1, j);

		int k = C - 1;
		while (i < k)
			swap(i++, k--);

		return true;
	}

	private static void swap(int i, int j) {
		int tmp = flag[i];
		flag[i] = flag[j];
		flag[j] = tmp;
	}

}