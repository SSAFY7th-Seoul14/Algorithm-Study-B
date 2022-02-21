// 1시간
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ2477 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		StringTokenizer st;

		// dir, len
		int[] maxC = new int[] { 0, 0 };
		int[] maxR = new int[] { 0, 0 };
		ArrayDeque<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			// 1-우, 2-좌, 3-하, 4-상
			int dir = st.nextToken().charAt(0) - '0';
			int len = Integer.parseInt(st.nextToken());
			int[] tmp = new int[] { dir, len };
			switch (dir) {
			// 좌우 방향일때 dir과 longest 기억
			case 1:
			case 2:
				if (len > maxC[1]) {
					maxC = tmp;
				}
				break;
			// 상하 방향일때 dir과 longest 기억
			case 3:
			case 4:
				if (len > maxR[1]) {
					maxR = tmp;
				}
				break;
			}
			q.add(tmp);
		}
		int minusArea = 0;
		// longest 다음 다음 같은 방향일때부터 세어주면 된다
		// 이부분을 좀 무식하게 구현해준 것 같다..
		// max변 각각에 대해서 다른 max변이 아닌 다음 다음 변, 그 다음 변이 뺄 면적 값을 구해줄 수 있는 변이다.
		while (true) {
			int[] compare = q.poll();
			// 꺼내준 값이 max 변 중 하나라면
			if (compare.equals(maxR) || compare.equals(maxC)) {
				// 한번 더 꺼낸다.
				int[] tmp = q.poll();
				// 꺼낸 값이 또 max라면 한번 더 지나가기 위해 poll 해준다.
				if (tmp.equals(maxR) || tmp.equals(maxC)) {
					q.poll();
				}
				// 다음 poll값부터는 뺄 면적의 변 둘이다.
				minusArea = q.poll()[1] * q.poll()[1];
				break;
			}
			// circular queue처럼 사용하기 위해 다시 add 해주기
			q.add(compare);
		}
		int answer = k * ((maxR[1] * maxC[1]) - minusArea);
		System.out.println(answer);
	}
}