import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 백준 12919번. A와 B
public class BOJ12919_AandB2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String t = br.readLine();
		
		// 역으로 간다.
		int size = s.length();
		StringBuilder sb = new StringBuilder();
		Queue<String> q = new LinkedList<>();
		q.offer(t);
		while (!q.isEmpty()) {
			if (size == q.peek().length())
				break;
			
			String end = q.poll();
			
			// 마지막 글자가 A면 지우고 set에 넣는다.
			if (end.charAt(end.length()-1) == 'A') {
				String newStr = end.substring(0, end.length()-1);
				if (!q.contains(newStr))
					q.offer(newStr);
			} 
			// 시작 글자가 B면 지우고 반전해서 set에 넣는다.
			if (end.charAt(0) == 'B') {
				sb.setLength(0);
				String newStr = sb.append(end.substring(1, end.length())).reverse().toString();
				if (!q.contains(newStr))
					q.offer(newStr);
			}
		}
		
		if (s.isEmpty()) {
			System.out.println("0");
			return;
		}
		
		for (String str : q) {
			if (s.equals(str)) {
				System.out.println("1");
				return;
			}
		} 
		System.out.println("0");
	}
}
