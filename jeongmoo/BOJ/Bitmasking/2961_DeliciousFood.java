import java.util.Scanner;

// 백준 2961번. 도영이가 만든 맛있는 음식
// 비트마스킹으로 부분집합 구해보기
public class BOJ2961_DeliciousFood2 {
	static int n;
	static int min = Integer.MAX_VALUE;
	static int arr[][];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n][2];
		for (int i = 0; i < n; i++) {
			arr[i][0] =	sc.nextInt();
			arr[i][1] =	sc.nextInt();
		}
		
		// 1부터 i<<n까지
		// n이 5이면 00001부터 100000전이니까 11111까지. 
		for (int i = 1; i<(1<<n); i++) {
			int mul = 1;
			int plus = 0;
			// 자리수가 n이므로 n번 반복
			for (int j = 0; j < n;j++) {
				// i를 2진수로 표현했을때 해당자리값이 1이면 선택, 0이면 비선택인 부분집합으로도 볼수 있음.
				// 7이면 00111이니까 -> 0,1,2번을 선택. 3,4번을 비선택
				// 9면 01001으로 0,4번 선택. 1,2,5번 비선택인 부분집합으로 생각. 
				if ((i&(1<<j)) != 0) {
					mul *= arr[j][0];
					plus += arr[j][1];
				}
			}
			min = Math.min(min, Math.abs(mul-plus));
		}
		
		System.out.println(min);
	}
}
