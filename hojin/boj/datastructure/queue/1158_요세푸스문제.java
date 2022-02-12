import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1158 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		in.close();
		// queue 생성
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i <= n; i++) {
			q.offer(i);
		}
		// queue가 비어있지 않을 동안
		int cnt;
		sb.append("<");
		while (!q.isEmpty()) {
			cnt = 0;
			// queue poll하면서 count++
			while (cnt < k - 1) {
				q.offer(q.poll());
				cnt++;
			}
			// count = k 되면 sb append
			sb.append(q.poll() + ", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append(">");
		System.out.println(sb);
	}

}
