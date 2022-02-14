import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ18258 {

	static BufferedReader br;
	static StringBuilder sb;
	static LinkedList<String> q;
	static StringTokenizer st;
	static String tmp;
	static int n, i;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		q = new LinkedList<>();
		n = Integer.parseInt(br.readLine());
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "push":
				q.add(st.nextToken());
				break;
			case "pop":
				tmp = q.poll();
				if (tmp == null)
					sb.append("-1\n");
				else
					sb.append(tmp).append("\n");
				break;
			case "size":
				sb.append(q.size()).append("\n");
				break;
			case "empty":
				if (q.isEmpty())
					sb.append("1\n");
				else
					sb.append("0\n");
				break;
			case "front":
				tmp = q.peek();
				if (tmp == null)
					sb.append("-1\n");
				else
					sb.append(tmp).append("\n");
				break;
			case "back":
				tmp = q.peekLast();
				if (tmp == null)
					sb.append("-1\n");
				else
					sb.append(tmp).append("\n");
				break;
			}
		}
		System.out.println(sb);
	}
}