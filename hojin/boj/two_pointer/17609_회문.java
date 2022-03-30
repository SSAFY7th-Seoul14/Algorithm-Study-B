import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ17609_회문 {
	static int canMinus, ans;
	static char[] isPal;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			// 그냥 String으로 받고 charAt 돌리면 시간 초과
			isPal = br.readLine().toCharArray();
			int left = 0;
			int right = isPal.length - 1;
			canMinus = 1;
			ans = 0;
			// 투 포인터 이동 시작
			if (!check(left, right))
				ans = 2;
			bw.write(String.valueOf(ans));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean check(int left, int right) {
		while (left <= right) {
			// 같을 경우 포인터 이동
			if (isPal[left] == isPal[right]) {
				++left;
				--right;
			}
			// 첫번째 depth에서 불일치 발생할 경우 파고 들어간다.
			// 다음 depth부터는 들어갈 수가 없다.
			else if (canMinus > 0) {
				--canMinus;
				// 유사회문 경우 체크를 위해 +1
				++ans;
				// left+1된 경우 체크
				if (check(left + 1, right))
					// 유사회문이므로 그냥 true 반환
					return true;
				// right-1된 경우 체크
				if (check(left, right - 1))
					// 유사회문이므로 그냥 true 반환
					return true;
			} else
				// 회문, 유사회문 모두 아니면 false반환
				return false;
		}
		// while문 탈출하면 회문
		return true;
	}
}