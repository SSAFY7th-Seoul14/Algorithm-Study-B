import java.util.Scanner;

// 백준1676번. 팩토리얼 0의 개수
public class BOJ1676_CountZero {	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		// 10을 곱할때마다 0이 하나씩 늘어난다.
		// 10 = 2 x 5
		// 2의 배수는 워낙 많으므로 2는 문제가 아니고, 즉 5의 개수 = 답인 것
		// n 범위가 500까지이고, 25, 125는 소인수분해시 5x5, 5x5x5이므로. 추가로 1번씩 더 해줌
		System.out.println(n/5 + n/25 + n/125);
	}
}