import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 1038번. 감소하는 수
public class BOJ1038_DecreasingNumber {
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// 10까지는 모두 감소하는 수이므로 그냥 출력
		if (n <= 10) {
			System.out.println(n);
			return;
		}
		
		// 10 다음 감소하는 수인 20부터 시작
		int count = 10;
		long start = 20;
		long last = 0;
		while(count != n) {
			// 숫자가 감소해야하므로 9876543210이 마지막 숫자임.
			if (start > 9876543210L) {
				last = -1;
				break;
			}
			
			long num = start;
			boolean minusNum = true;
			int digits = 0;
			while (num > 10) {
				long back = num%10;
				num /= 10;
				digits++;
				
				// 이전 자리수와 비교
				if (num%10 <= back) {
					minusNum = false;
					break;
				}
			}
			
			// 감소하는 수면 카운팅하고 last에 저장. start는 1 증가시킨다.
			if (minusNum) {
				count++;
				last = start;
				start++;
			// 감소하는 수가 아니면 백트래킹
			} else {
				// 안되는 자리수에서 다음수로 올린다.
				// 643543일 경우 뒤에서 비교하면 5에서 실패처리될 것이고. digits가 3이 됨.
				// 그러면 10^3인 1000으로 나누어 643만 얻어낸뒤. +1하고 100을 다시 곱해서 start를 644000으로 올린다.
				long standard = (long)Math.pow(10, digits);
				long front = start / standard;
				start = (front+1) * standard;
			}
		}
		
		// 출력
		System.out.println(last);
	}
	// 1022 -> 9876543210
	// 1023 -> -1
	
	// 다른 풀이 : 10Cr 하면서 재귀에서 이전수 비교해서 백트래킹
}
