import java.util.Scanner;

// 백준 2961번. 도영이가 만든 맛있는 음식
public class BOJ2961_DeliciousFood {
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
		
		// n 최대값이 10이므로 부분조합을 돌려도 2^10 = 1024
		subset(0, 1, 0, 0);
		
		System.out.println(min);
	}
	
	static void subset(int idx, int mul, int plus, int count) {	
		if(0 < count)
			min = Math.min(min, Math.abs(mul-plus));

		if(idx == n)
			return;
		
		subset(idx+1, mul*arr[idx][0], plus+arr[idx][1], count+1);
		subset(idx+1, mul, plus, count);
	}
}
