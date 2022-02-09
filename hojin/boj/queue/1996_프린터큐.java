import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();

		int T = Integer.parseInt(in.readLine()), targetIndex, index, maxPriority, priority, count;
		int[] out;
		StringTokenizer st;
		Queue<int[]> queue = new LinkedList<int[]>();
		for (int tc = 0; tc < T; tc++) {
			queue.clear();

			st = new StringTokenizer(in.readLine());
			st.nextToken();
			targetIndex = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(in.readLine());
			index = 0; 

			while (st.hasMoreTokens()) {
				priority = Integer.parseInt(st.nextToken());
				queue.offer(new int[] { priority, index++ });
			}
			count = 0;

			while (!queue.isEmpty()) {
				maxPriority = 0;
				for (int[] obj : queue) {
					maxPriority = Math.max(maxPriority, obj[0]);
				}
				out = queue.poll();
				if (out[0] < maxPriority) {
					queue.offer(out);
					continue;
				}
				count++;
				if (out[1] == targetIndex)
					break;
			}
			ans.append(count).append("\n");
		}
		in.close();
		System.out.println(ans);
	}
}
