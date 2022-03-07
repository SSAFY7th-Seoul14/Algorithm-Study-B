import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 집의 개수 2<=n<=200,000
		int n = Integer.parseInt(st.nextToken());
		// 공유기의 개수 2<=c<=n
		int c = Integer.parseInt(st.nextToken());

		int[] houses = new int[n];

		for (int i = 0; i < n; i++) {
			houses[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		// 정렬해놓고
		Arrays.sort(houses);

		// 최소거리부터
		int minDist = 1;
		// 최대 거리 생각하기
		int maxDist = houses[n - 1] - houses[0];
		int ans = 0;

		// 설치할 수 있는 최소거리와 최대거리 사이에서 이분 탐색 수행
		while (minDist <= maxDist) {
			// 중간 길이값 -> 가장 큰 최소 거리가 되기 위해서는 결국 각 공유기간 거리가 공평하게 분배 될 수 있는 중간 길이에 가까워야 한다.
			int mid = (minDist + maxDist) / 2;
			// 매 탐색마다 다음 공유기 설치 집까지의 거리를 잴 시작점
			int start = houses[0];
			// 시작점을 정했으니 공유기의 개수 cnt는 1
			int cnt = 1;

			// 이 부분이 핵심
			// houses 돌면서 mid 길이보다 큰 길이 확인하고 공유기 설치
			for (int i = 1; i < n; i++) {
				// start를 이용해서 거리를 재어주는 게 핵심인 듯.
				int d = houses[i] - start;
				// 기준이 되는 mid보다 재어준 길이가 크거나 같으면 설치한다.
				if (mid <= d) {
					// 공유기 설치 개수 증가
					++cnt;
					// mid보다 긴 길이가 나온 점을 체크하고 다음 길이를 재줄 시작점 start도 해당 집으로 갱신
					start = houses[i];
				}
			}

			// 현재 설치 개수가 목표 c보다 크거나 같으면
			if (cnt >= c) {
				// 일단 최대 최소 길이는 mid가 되고
				ans = mid;
				// c의 개수를 줄여줘야 하므로 기준이될 mid를 키우기 위해서 오른쪽 범위로 탐색 수행
				minDist = mid + 1;
			} else {
				// c를 늘려야 하므로 mid 값을 줄여주기 위해 왼쪽 범위에서 수행
				maxDist = mid - 1;
			}
		}

		System.out.println(ans);
	}

}