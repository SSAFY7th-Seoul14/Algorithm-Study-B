import java.io.InputStreamReader;
import java.io.BufferedReader;

// 백준 5904번. Moo 게임
public class BOJ5904_MooGame {
	static int n;
	static char result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		n = Integer.parseInt(br.readLine()); // max 1000000000
		// 최대 10억에 1초니 O(n)으로 풀어도 안된다.
		if (n <= 3) {
			char c = n==1? 'm': 'o';
			System.out.println(c);
			return;
		}
		
		// n번째를 구해야하므로 길이가 n이 넘어가는 순간까지만 구하면 된다.
		int len = 3;
		int k = 0;
		while (len < n) {
			k++;
			len = len*2+k+3;
		}
		
		// 분할정복
		while(true) {
			// next = prev*2+k+3;
			// 이전값으로 내려가면서 찾는다.
			int prev = (len-k-3)/2;
			if (k == -1) { // 최초까지 내려오면 1번 인덱스만 m, 나머지는 o
				char c = n==1? 'm': 'o';
				System.out.println(c);
				return;
			} else if (n <= prev) { // 이전값보다 작으면 그 이전으로
				k--;
				len = prev;
			} else if (prev + k + 3 < n) { // 뒤에 붙는 이전값이면 n을 그만큼 빼준다.
				n -= prev + k + 3;
				k--;
				len = prev;
			} else { // 사이에 있는 고유한 값이면 1번 인덱스만 m 나머지는 o
				char c = n==prev+1? 'm': 'o';
				System.out.println(c);
				return;
			}
		}
	}
}
