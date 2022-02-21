import java.util.Scanner;

// 백준 2839번. 설탕 배달
public class BOJ2839_SugarDelivery {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int three = 0;
		int five = n/5;
		int current = n - 5*five;
		while(current%3 != 0 && 0 < five) {
			five--;
			current = n - 5*five;
		}
		if (current%3 == 0)
			three = current/3;
		
		int sum = five+three;
		if (0 == sum)
			System.out.println("-1");
		else
			System.out.println(sum);
	}
}
