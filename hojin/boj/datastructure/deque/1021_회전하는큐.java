import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ1021 {
	static BufferedReader br;
	static StringTokenizer st;
	static LinkedList<Integer> dq;
	static int n, m, total, tmp, i;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dq = new LinkedList<Integer>();
		for (i = 1; i <= n; i++) {
			dq.add(i);
		}

		st = new StringTokenizer(br.readLine());
		total = 0;
		for (i = 0; i < m; i++) {
			tmp = Integer.parseInt(st.nextToken());

			if (dq.indexOf(tmp) > dq.size() / 2) {
				while (dq.peek() != tmp) {
					dq.addFirst(dq.pollLast());
					total++;
				}
			} else {
				while (dq.peek() != tmp) {
					dq.add(dq.poll());
					total++;
				}
			}
			dq.poll();
		}
		System.out.println(total);
	}
}