import java.util.Scanner;

// 백준1436번. 영화감독 숌
public class BOJ1436_DirectorSyom {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int num = 665;
		int count = 0;
		while(true) {
			num++;
			if(Integer.toString(num).contains("666")) {
				count++;
				
				if (n == count) {
					System.out.println(num);
					break;
				}
			}
		}
	}
}
