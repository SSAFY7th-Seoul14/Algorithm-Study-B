import java.io.*;

public class BOJ16463_13일의금요일 {

	static int[] dayPerMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int targetYear = Integer.parseInt(br.readLine());
		int answer = 0;
		// 2019년 13일은 일요일
		int month13th = 0;
		for (int i = 2019; i < targetYear; ++i) {
			for (int j = 0; j < 12; ++j) {
				// 2월에 대해서
				if (j == 1) {
					if (i % 400 == 0)
						++month13th;
					else if (i % 100 != 0 && i % 4 == 0)
						++month13th;
				}
				month13th = (month13th + dayPerMonth[j]) % 7;
				if (month13th == 5)
					++answer;
			}
		}
		// targetYear는 11월까지만
		for (int i = 0; i < 11; ++i) {
			if (i == 1) {
				if (targetYear % 400 == 0)
					++month13th;
				else if (targetYear % 100 != 0 && targetYear % 4 == 0)
					++month13th;
			}
			month13th = (month13th + dayPerMonth[i]) % 7;
			if (month13th == 5)
				++answer;
		}
		System.out.println(answer);
		br.close();
	}

}
