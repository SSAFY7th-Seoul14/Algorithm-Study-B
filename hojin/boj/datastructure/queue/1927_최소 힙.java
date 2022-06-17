import java.io.*;
import java.util.*;

public class BOJ1927_최소힙 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int n = stoi(br.readLine());
		for (int i = 0; i < n; i++) {
			int inp = stoi(br.readLine());
			switch (inp) {
			case 0:
				if (pq.isEmpty())
					pq.add(inp);
				sb.append(pq.poll()).append('\n');
				break;
			default:
				pq.add(inp);
				break;
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
