import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 백준18258번. 큐 2
public class BOJ18258_Queue2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Deque<Integer> d = new LinkedList<>();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "push":
				int num = Integer.parseInt(st.nextToken());
				d.offer(num);
				break;
			case "pop":
				if (d.isEmpty())
					sb.append("-1\n");
				else
					sb.append(d.poll()+"\n");
				break;
			case "size":
				sb.append(d.size()+"\n");
				break;
			case "empty":
				if (d.isEmpty())
					sb.append("1\n");
				else
					sb.append("0\n");
				break;
			case "front":
				if (d.isEmpty())
					sb.append("-1\n");
				else
					sb.append(d.peek()+"\n");
				break;
			case "back":
				if (d.isEmpty())
					sb.append("-1\n");
				else
					sb.append(d.peekLast()+"\n");
				break;
			default:
				break;
			}
		}
		System.out.println(sb.toString());
	}
}
