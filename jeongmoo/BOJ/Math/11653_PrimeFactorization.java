import java.util.Scanner;

public class boj11653 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int div = 2;
		int max = (int)Math.sqrt(n);
		
		StringBuilder sb = new StringBuilder();
		while (true) {
			if(max < div)
				break;
			
			if (n%div == 0) {
				while(true) {
					sb.append(div+"\n");
					n /= div;
					
					if (n%div != 0) {
						break;
					}
				}
			}
			div++;
		}
		
		if (n != 1)
			sb.append(n+"\n");
		
		System.out.println(sb.toString());
	}
}
