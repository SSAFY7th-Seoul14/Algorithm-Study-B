import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1436 {
	static BufferedReader br;
	static int n, totalCnt, num, i;
	static String str;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		totalCnt = 0;
		num = 666; // 666부터 check
		while (totalCnt != n) {
//			str = Integer.toString(num); // 여기서 계속 String 객체를 만들기 때문에 메모리 초과
			if (String.valueOf(num).contains("666")) {
				totalCnt++;
			}
			num++;
		}
		System.out.println(num - 1);
	}
}