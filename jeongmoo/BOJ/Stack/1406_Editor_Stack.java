import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;
import java.util.StringTokenizer;

// 백준1406번. 에디터
public class BOJ1406_Editor {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		

		Stack<Character> prev = new Stack<>();
		Stack<Character> next = new Stack<>();
		
		char[] origin = br.readLine().toCharArray();
		for (char c : origin) {
			prev.push(c);
		}
		
		// 스택을 사용한 풀이.
		// 커서 전 후의 값이 prev, next에 따로 저장되있음
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "P":
				char c = st.nextToken().charAt(0);
				prev.push(c);
				break;
			case "L":
				if (!prev.isEmpty())
					next.push(prev.pop());					
				break;
			case "D":
				if (!next.isEmpty())
					prev.push(next.pop());
				break;
			case "B":
				if (!prev.isEmpty())
					prev.pop();
				break;
			default:
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		// prev 스택은 앞에서부터 더해줌.
		for (char c : prev) {
			sb.append(c);
		}
		// next 스택은 역으로 더해준다.
		for (int i = next.size()-1; i >= 0; i--) {
			sb.append(next.get(i));
		}
		System.out.println(sb.toString());
	}
}
