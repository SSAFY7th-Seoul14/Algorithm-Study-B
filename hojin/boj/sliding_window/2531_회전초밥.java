import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2531_회전초밥 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 벨트에 놓인 접시 수 2<=n<=3,000,000
		int n = Integer.parseInt(st.nextToken());
		// 초밥의 가짓 수 2<=d<=3,000 사실상 초밥의 번호
		int d = Integer.parseInt(st.nextToken());
		// 연속해서 먹는 접시 수 k <= n
		int seqNo = Integer.parseInt(st.nextToken());
		// 쿠폰 번호 1<=c<=d
		int couponNo = Integer.parseInt(st.nextToken());
		int[] dishes = new int[n];
		// 각 값이 몇개인지 세어줄 counter배열 선언
		int[] counter = new int[d + 1];
		// 처음 window에서 몇개의 종류가 있는지 담아줄 cnt변수
		int cnt = 0;
		for (int i = 0; i < n; ++i) {
			int sushiNo = Integer.parseInt(br.readLine().trim());
			// window 안에서는(seqNo보다 작을 때)
			if (i < seqNo) {
				// 해당 counter 증가 시켜주면서
				// 비어있던 counter index였다면 (==0이라면)
				if (counter[sushiNo]++ == 0) {
					// 종류 개수 증가
					++cnt;
				}
			}
			dishes[i] = sushiNo;
		}
		int maxCnt = 0;
		// n-1까지 새로 하면 처음과 같은 상태이기 때문에 n-2까지만 진행
		for (int i = 0; i < n - 1; ++i) {
			// couponNo가 현재 window에 포함되어 있지 않으면
			if (counter[couponNo] == 0) {
				// 1증가 시킨 값과 maxCnt 비교
				maxCnt = Math.max(maxCnt, cnt + 1);
			}
			// 아니면
			else {
				// 해당 cnt값과 비교
				maxCnt = Math.max(maxCnt, cnt);
			}
			// 이전 window 영역 counter는 빼주고
			// 만약 0이면 == 종류가 감소하므로
			if (--counter[dishes[i]] == 0) {
				// 종류 변수 cnt 감소
				--cnt;
			}
			// 현재 window에 새로 들어온 index(i+seqNo) 값의 counter 증가
			// ex. 0, 1, 2, 3, 4일 때 window길이(=seqNo)가 3이라면 0~2는 2(=seqNo - 1) 차이. 새로운 window 영역은 0 + seqNo에 해당하는 3
			if (counter[dishes[(i + seqNo) % n]]++ == 0)
				// 포함되어있지 않은 값이었다면 종류 증가
				++cnt;
		}
		System.out.println(maxCnt);
		br.close();
	}

}