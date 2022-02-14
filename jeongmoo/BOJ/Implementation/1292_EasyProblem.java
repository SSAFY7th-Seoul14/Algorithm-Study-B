import java.util.Scanner;

// 백준1292번. 쉽게 푸는 문제
public class BOJ1292_EasyProblem {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		int start = 1;
		int count = start;
		int sum = 0;
		int prev = 0;
		for(int i=1; i<=b; i++) {
			if(i==a)
				prev = sum;
			
			sum += start;
			if (--count == 0)
				count = ++start;
		}
		

		System.out.println(sum-prev);
	}
}
