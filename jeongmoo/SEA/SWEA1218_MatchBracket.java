import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

//SW Expert Academy 1218번. 괄호 짝짓기
public class SWEA1218_MatchBracket {
	
	public static int check(Stack<Character> s, char open) {
		if(s.isEmpty()) {
			return 0;
		}
		
		char top = s.pop();
		if (top != open) {
			return 0;
		}
		
		return 1;
	}
	
	public static void main(String[] args) throws Exception {
		Stack<Character> s = new Stack<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 10; i++) {
			// 입력
			int t = Integer.parseInt(br.readLine());
			char[] arr = br.readLine().toCharArray();
			
			// 처리
			int result = 1;
			for (char c : arr) {
				switch (c) {
				case '(':
				case '[':
				case '{':
				case '<':
					s.push(c);
					break;
					
				case ')':
					result = check(s, '(');
					break;
				case ']':
					result = check(s, '[');
					break;
				case '}':
					result = check(s, '{');
					break;
				case '>':
					result = check(s, '<');
					break;
				default:
					break;
				}
				
				if (0 == result)
					break;
			}
			
			if (false == s.isEmpty()) {
				result = 0;
				s.clear();
			}
			
			// 출력
			System.out.printf("#%d %d\n", i+1, result);
		}
	}
}
