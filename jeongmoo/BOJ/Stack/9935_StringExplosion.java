import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// 백준 9935번. 문자열 폭발
public class BOJ9935_StringExplosion {	
	static String str;
	static String exp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		exp = br.readLine();
		
		explosion();
	}
	
	public static void explosion() {
		int expSize = exp.length();
		int strSize = str.length();
		char[] expData = exp.toCharArray();
		char[] data = str.toCharArray();
		
		Stack<Character> stack = new Stack<>();
		// 폭발 문자열 size부터 시작
		char endchar = expData[expSize-1];
		for (int i = 0; i < strSize; i++) {
			stack.push(data[i]);
			
			// 스택의 마지막이 폭발 문자의 마지막과 같으면 스택 한번 체크한다.
			if (stack.size() >= expSize && stack.peek() == endchar) {
				boolean isExplode = true; 
				for (int j = 0; j < expSize; j++) {
					if (expData[j] != stack.get(stack.size()-expSize+j)) {
						isExplode = false;
						break;
					}
				}
				
				if (isExplode) {
					for (int j = 0; j < expSize; j++) {
						stack.pop();
					}
				}
			}
		}
		
		if (stack.isEmpty()) {
			System.out.println("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < stack.size(); i++) {
				sb.append(stack.get(i));
			}
			System.out.println(sb);
		}
	}
}
