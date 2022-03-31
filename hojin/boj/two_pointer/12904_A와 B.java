import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12904_A와B {
	static int left, right;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 1<= s길이 <= 999 s < t
		char[] S = br.readLine().toCharArray();
		// 2<= t길이 <= 1000
		char[] T = br.readLine().toCharArray();
		br.close();
		left = 0;
		right = T.length - 1;
		int targetLen = S.length - 1;
		while (Math.abs(right - left) > targetLen) {
			if (left < right) {
				// t 뒤에서부터 빼주기
				// 맨뒤가 A면 A빼기만
				// 맨뒤가 B면 B빼고 reverse
				if (T[right--] == 'B')
					swap();
			} else {
				if (T[right++] == 'B')
					swap();
			}
		}
		// s와 길이가 같아졌을 때 비교하기
		int index = 0;
		boolean same = true;
		if (left < right) {
			for (int i = left; i <= right; ++i) {
				if (S[index++] != T[i]) {
					same = false;
					break;
				}
			}
		} else {
			for (int i = left; i >= right; --i) {
				if (S[index++] != T[i]) {
					same = false;
					break;
				}
			}
		}
		System.out.println(same ? "1" : "0");
	}

	private static void swap() {
		int temp = left;
		left = right;
		right = temp;
	}

}