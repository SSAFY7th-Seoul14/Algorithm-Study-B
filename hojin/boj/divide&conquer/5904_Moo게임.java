import java.io.*;

public class BOJ5904_Moo게임 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int mooAccum = 3, nth = 0;
		// n보다 큰 nth 구하기
		while (n > mooAccum) {
			++nth;
			mooAccum = 2 * mooAccum + nth + 3;
		}
		System.out.println(moo(mooAccum, nth + 3, n));
		br.close();
	}

	private static String moo(int mooAccum, int curNth, int n) {
		// 이전까지의 길이는 현재 길이에서 curNth의 moo의 길이를 빼고 2로 나눠주면 된다.
		int prev = (mooAccum - curNth) / 2;
		// n이 이전길이보다 작거나 같으면 이전 길이로 들어가기
		if (n <= prev) return moo(prev, curNth - 1, n);
		// n이 이전길이 + 현재 moo 길이보다 크면 오른쪽은 이전 길이와 같으므로 "moo" mooo "moo"
		else if (n > prev + curNth) return moo(prev, curNth - 1, n - prev - curNth);
		// nth을 moo 들어오기전 while문으로 걸러줬기 때문에 elif의 예외는 따질 필요 없이 if의 else 경우만 생각하면 가운데 moo로 들어온다.
		// 이때 길이가 0이면 m이고 0 초과면 o다.
		return n - prev - 1 > 0 ? "o" : "m";
	}

}
