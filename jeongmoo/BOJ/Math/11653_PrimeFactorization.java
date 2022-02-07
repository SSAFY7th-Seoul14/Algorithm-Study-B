import java.util.Scanner;

// 백준 11653번. 소인수분해
public class BOJ11653_PrimeFactorization {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int origin = n;
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i < n; i++) {
			if (origin < i*i)
				break;
			
			while (n%i==0) {
				sb.append(i+"\n");
				n/=i;
			}
			
		}
		
		if (n != 1)
			sb.append(n+"\n");
		
		System.out.println(sb.toString());
	}
}
