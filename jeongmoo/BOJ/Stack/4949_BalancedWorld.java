import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// 백준4949번. 균형잡힌 세상
public class BOJ4949_BalancedWorld {
	
	public static boolean check(Stack<Character> s, char open) {
		if (s.isEmpty())
			return false;
		
		char c = s.pop();
		if (c != open)
			return false;
		
		return true;
	}

	public static void main(String[] args) throws Exception {
		Stack<Character> s = new Stack<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String str = br.readLine();
			if (str.equals("."))
				break;
			
			boolean check = true;
			for (char c : str.toCharArray()) {
				switch (c) {
				case '(':
				case '[':
					s.push(c);
					break;
				case ')':
					check = check(s, '(');
					break;
				case ']':
					check = check(s, '[');
					break;
				default:
					break;
				}
				
				if (!check) break;
			}
			
			if (!s.isEmpty()) {
				check = false;
				s.clear();
			}
			
			if (check)
				System.out.println("yes");
			else
				System.out.println("no");
		}
	}

}
