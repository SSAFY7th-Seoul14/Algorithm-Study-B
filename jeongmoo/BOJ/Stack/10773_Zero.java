import java.util.Scanner;
import java.util.Stack;

// 백준 10773번. 제로
public class BOJ10773_Zero {
	public static void main(String[] args) {
		Stack<Integer> s = new Stack<>();
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		
		for (int i = 0; i < k; i++) {
			int n = sc.nextInt();
			if (n != 0)
				s.push(n);
			else
				s.pop();
		}
		
		int sum = 0;
		for (Integer integer : s) {
			sum += integer.intValue();
		}
		
		System.out.println(sum);
	}
}
