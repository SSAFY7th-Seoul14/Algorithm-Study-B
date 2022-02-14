import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 백준17298번. 오큰수
// 2493번 타워 문제 역순 느낌.
public class BOJ17298_RightBigNumber {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// 입력
		int[] data = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		// 처리
		Stack<Integer> s = new Stack<>();
		int[] ans = new int[n];
		for (int i = n-1; i >= 0; i--) {
			int num = data[i];
			while(!s.isEmpty()) {
				if(s.peek() > num) {
					ans[i] = s.peek();
					break;
				}
				s.pop();					
			}
			
			if(s.isEmpty())
				ans[i] = -1;
			
			s.push(num);
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(ans[i] + " ");
		}
		System.out.println(sb);
	}
}
