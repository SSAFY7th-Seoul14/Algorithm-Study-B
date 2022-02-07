import java.util.Scanner;
import java.util.Stack;

// 백준 1874번. 스택 수열
public class BOJ1874_StackSequence {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// 데이터 입력
		int[] data = new int[n];
		for (int i = 0; i < n; i++) {
			data[i] = sc.nextInt();
		}
		
		// 처리
		Stack<Integer> s = new Stack<>();
		StringBuilder sb = new StringBuilder();
		int idx = 0;
		for (int i = 1; i <= n; i++) {
			if(i == data[idx]) { // 같으면 넣었다 뺀다.
				sb.append("+\n");
				sb.append("-\n");
				idx++;
				
				// 빼고 더 뺄게 있는지 본다.
				while(!s.isEmpty()) {
					if (s.peek() == data[idx]) {
						s.pop();
						sb.append("-\n");
						idx++;
					} else {
						break;
					}
				}	
			} else if(i < data[idx]) { // 작으면 넣는다.
				s.push(i);
				sb.append("+\n");
			}
		}
		
		// 남아있으면 수열 완성 못함
		if (!s.isEmpty())
			System.out.println("NO");
		else
			System.out.println(sb.toString());
	}
}
