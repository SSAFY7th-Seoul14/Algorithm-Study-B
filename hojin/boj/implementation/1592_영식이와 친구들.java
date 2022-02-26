import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1592 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 입력 처리
		// N명, M번 받으면 끝, L번째 다음 사람에게 던지기
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()),
				L = Integer.parseInt(st.nextToken());
		// 1부터 N까지 시계방향
		// 던지고 M번 받으면 끝
		// M보다 작을 때 현재 횟수가 홀수면 시계
		// 짝수면 반시계
		// L번째 다음 사람에게 던지기
		// 1부터 시작

		// 1부터 N명까지 공을 몇번 받았는지 세어줄 cnt[]
		int[] cntList = new int[N];
		int current = 1;
		int cnt = 0;
		// 1부터 시작
		while (++cntList[current] < M) {
			++cnt;
			if (cntList[current] % 2 == 1) {
				// 홀수니까 시계
				current = (current + L) % N;
			} else {
				// 반시계일 때는 반시계
				current = (current - L + N) % N;
			}
		}
		System.out.println(cnt);
	}
}