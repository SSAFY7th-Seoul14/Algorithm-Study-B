import java.util.Scanner;

public class SWEA3307_최장증가부분수열 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; ++tc) {
			int N = sc.nextInt();// 수열의 크기
			int[] arr = new int[N];// 수열의 원소들 저장
			int[] LIS = new int[N];// 자신을 끝으로 하는 LIS 길이

			for (int i = 0; i < N; ++i) {
				arr[i] = sc.nextInt();
			}

			int max = 0; // 해당 수열의 LIS 최장길이
			for (int i = 0; i < N; ++i) { // 모듬 원소에 대해 자신을 끝으로 하는 LIS 길이 계산
				LIS[i] = 1; // 자신 혼자 LIS 구성 할 때의 길이 1로 초기화
				for (int j = 0; j < i; ++j) {// 첫 원소부터 i원소 직전가지 비교
					if (arr[j] < arr[i] && LIS[i] < LIS[j] + 1) {// arr[j] < arr[i] : 증가 수열의 모습
						LIS[i] = LIS[j] + 1;
					}
				}
				if (max < LIS[i])
					max = LIS[i];
			}
			// 시간복잡도 O(n^2)
			System.out.println(String.format("#%d %d", tc, max));
		}
		sc.close();
	}

}