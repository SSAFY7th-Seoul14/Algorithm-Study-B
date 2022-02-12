import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13335 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		// 트럭 개수 n
		int n = Integer.parseInt(st.nextToken());
		// 최대 대수 w
		int w = Integer.parseInt(st.nextToken());
		// 최대 하중 limit
		int limit = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(in.readLine());

		// queue 2개로 해결해보자 (다른 풀이 참고 했음)
		// 하나는 truck, 하나는 bridge;
		Queue<Integer> bridge = new LinkedList<>(), truckQ = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			truckQ.offer(Integer.parseInt(st.nextToken())); // 7 4 3 2 5
		}
		in.close();
		for (int i = 0; i < w; i++) {
			bridge.offer(0); // 0 0
		}

		int count = 0, currentL = 0, tr;
		// bridge가 빌 때까지
		while (!bridge.isEmpty()) {
			while (!truckQ.isEmpty()) {
				tr = truckQ.peek();
				currentL += tr - bridge.poll();
				if (currentL > limit) {
					bridge.offer(0);
					currentL -= tr;
				} else {
					bridge.offer(tr);
					truckQ.poll();
				}
				count++;
			}
			count++;
			bridge.poll();
		}
		System.out.println(count);
	}
}
