import java.io.*;
import java.util.*;

public class BOJ1213_팰린드롬만들기 {

	static char[] name;
	static int[] count = new int[26];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		name = br.readLine().toCharArray();
		int len = name.length;
		Arrays.sort(name);
		for (int i = 0; i < len; ++i) {
			count[name[i] - 'A']++;
		}
		boolean odd2 = false;
		for (int i = 0; i < 26; ++i) {
			if (count[i] % 2 == 1) {
				if (odd2) {
					System.out.println("I'm Sorry Hansoo");
					return;
				}
				odd2 = true;
			}
		}
		LinkedList<Character> q = new LinkedList<Character>();
		Stack<Character> stack = new Stack<Character>();
		char center = 0;
		for (int i = 0; i < len; i += 2) {
			if (i + 1 < len && name[i] == name[i + 1]) {
				q.offer(name[i]);
				stack.add(name[i + 1]);
			} else {
				center = name[i];
				--i;
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!q.isEmpty()) {
			sb.append(q.poll());
		}
		if (center > 0)
			sb.append(center);
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
		br.close();
	}

}