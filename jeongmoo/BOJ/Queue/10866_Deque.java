import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 백준10866번. 덱
public class BOJ10866_Deque {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Deque<Integer> d = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "push_back":
				int num = Integer.parseInt(st.nextToken());
				d.offerLast(num);
				break;
			case "push_front":
				int num2 = Integer.parseInt(st.nextToken());
				d.offerFirst(num2);
				break;
			case "pop_back":
				if (d.isEmpty())
					System.out.println("-1");
				else
					System.out.println(d.pollLast());
				break;
			case "pop_front":
				if (d.isEmpty())
					System.out.println("-1");
				else
					System.out.println(d.pollFirst());
				break;
			case "size":
				System.out.println(d.size());
				break;
			case "empty":
				if (d.isEmpty())
					System.out.println("1");
				else
					System.out.println("0");
				break;
			case "front":
				if (d.isEmpty())
					System.out.println("-1");
				else
					System.out.println(d.peekFirst());
				break;
			case "back":
				if (d.isEmpty())
					System.out.println("-1");
				else
					System.out.println(d.peekLast());
				break;
			default:
				break;
			}
		}
	}
}
