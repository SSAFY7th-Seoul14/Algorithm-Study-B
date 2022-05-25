import java.util.*;
import java.io.*;

public class BOJ11279_최대힙 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
			return b - a;
		});
		StringBuilder sb = new StringBuilder();
		int n = stoi(br.readLine());
		for (int i = 0; i < n; ++i) {
			int inp = stoi(br.readLine());
			pq.add(inp);
			if (inp == 0 && !pq.isEmpty()) {
				sb.append(pq.poll()).append('\n');
			}
		}
		System.out.println(sb);
		br.close();
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
