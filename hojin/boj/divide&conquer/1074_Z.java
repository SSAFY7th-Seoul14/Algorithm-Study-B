import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1074 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		zFind(n, r, c);
		System.out.println(cnt);
	}

	static int cnt = 0;

	private static void zFind(int n, int r, int c) {

		// n = 0 일 경우는 경우에 미 포함
		if (n == 0)
			return;
		// 영역 divide를 위한 section 변수
		// 0 | 1
		// -----
		// 2 | 3
		int section = 0;
		// section 구분 기준이 될 half 값 구하기
		int half = (int) Math.pow(2, n - 1);
		// 0 index부터 세어주므로 half와 같거나 클 때 section 1로 넘어간다.
		if (c >= half) {
			section += 1;
		}
		if (r >= half) {
			section += 2;
		}
		// 각 n마다 새로 나타나는 index는 4^(n-1)로, 경계가 되는 half의 제곱과 같은 값이다.
		cnt += section * half * half;
		// section은 column만 큰 경우는 1, row만 큰 경우는 2, col, row 모두가 크면 3으로 넘어간다
		// 영역 마다 재귀로 넘겨주는 값이 달라진다.
		switch (section) {
		case 0:
			zFind(n - 1, r, c);
			break;
		case 1:
			zFind(n - 1, r, c - half);
			break;
		case 2:
			zFind(n - 1, r - half, c);
			break;
		case 3:
			zFind(n - 1, r - half, c - half);
			break;
		}
	}

}