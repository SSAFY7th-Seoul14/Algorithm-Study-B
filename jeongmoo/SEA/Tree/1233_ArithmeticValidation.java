import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SW Expert Academy 1233번. 사칙 연산 유효성 검사
public class SWEA1233_ArithmeticValidation {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int t = 1; t <= 10; t++) {
			// 입력
			int n = Integer.parseInt(br.readLine());
			int result = 1;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				
				if(result == 0) continue; // 이번 테케에서는 거짓이므로 스트링만 읽는다. 다음 테케위해
				
				// 처리
				st.nextToken(); // 자리 필요없음
				char data = st.nextToken().charAt(0);
				switch (data) {
				case '*':
				case '/':
				case '+':
				case '-':
					// 연산자인데 자식이 없으면
					if (!st.hasMoreTokens()) {
						result = 0;
						continue;
					}
					break;
				default:
					// 나머지 숫자인 경우는 자식이 있으면 안됨.
					if (st.hasMoreTokens()) {
						result = 0;
						continue;
					}
					break;
				}
			}
			
			// 출력
			System.out.printf("#%d %d\n", t, result);
		}
	}
	
	// 이진트리에서 n/2번까지 내부노드.
	// n/2+1 ~ n번까지는 단말노드. 
}
