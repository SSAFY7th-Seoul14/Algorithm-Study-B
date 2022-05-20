import java.io.*;
import java.util.*;

public class BOJ1764_듣보잡 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Set<String> set = new HashSet<>();
		PriorityQueue<String> pq = new PriorityQueue<>();
		for (int i = 0; i < n; ++i) {
			set.add(br.readLine());
		}
		for (int i = 0; i < m; ++i) {
			String str = br.readLine();
			if (set.contains(str))
				pq.add(str);
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(pq.size()));
		bw.newLine();
		while (!pq.isEmpty()) {
			bw.write(pq.poll());
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
