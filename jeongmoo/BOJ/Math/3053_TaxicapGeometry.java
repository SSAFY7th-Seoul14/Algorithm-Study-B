import java.util.Scanner;

public class boj3053 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		
		System.out.printf("%.6f\n", Math.PI * r * r);
		System.out.printf("%.6f\n", 2.0 * r * r);
	}
}
