import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2839 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int cnt = 0;
		while (n > 0) {
			if (n % 5 == 0) {
				cnt += n / 5;
				break;
			} else if (n % 3 == 0) {
				n -= 3;
				cnt++;
			} else {
				n -= 5;
				if (n < 0) {
					cnt = -1;
					break;
				}
				cnt++;
			}
		}
//		더 좋은 코드
//		int bag3kg = 0;
//		while (n % 5 != 0 && n >= 0) {
//			bag3kg++;
//			n -= 3;
//		}
//
//		// n이 5kg로 나눠지거나 음수이거나
//		int cnt = (n < 0) ? -1 : bag3kg + n / 5;

		System.out.println(cnt);
	}

}