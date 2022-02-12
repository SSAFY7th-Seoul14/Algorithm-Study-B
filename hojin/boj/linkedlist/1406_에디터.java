import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

	static BufferedReader br;
	static StringBuilder sb;
	static LinkedList<Character> q;
	static char tmpCharList[];
	static int cursor, m;
	static String str;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		tmpCharList = br.readLine().toCharArray();
		q = new LinkedList<>();
		for (char el : tmpCharList) {
			q.add(el);
		}
		cursor = q.size();
		m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			str = br.readLine();
			switch (str.charAt(0)) {
			case 'L':
				if (cursor == 0)
					continue;
				q.addFirst(q.pollLast());
				cursor--;
				break;
			case 'D':
				if (cursor == q.size())
					continue;
				q.add(q.poll());
				cursor++;
				break;
			case 'B':
				if (cursor == 0)
					continue;
				q.pollLast();
				cursor--;
				break;
			case 'P':
				q.add(str.charAt(2));
				cursor++;
				break;
			}
		}
		for (int i = 0; i < q.size() - cursor; i++) {
			q.add(q.poll());
		}
		while (!q.isEmpty()) {
			sb.append(q.poll());
		}
		System.out.println(sb);
	}
}