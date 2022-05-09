import java.util.*;
import java.io.*;

public class BOJ11000_강의실배정 {
	static class Timetable {
		int start, end;

		public Timetable(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = stoi(br.readLine());
		PriorityQueue<Timetable> pq1 = new PriorityQueue<>((a, b) -> {
			return a.start == b.start ? a.end - b.end : a.start - b.start;
		});
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			pq1.add(new Timetable(stoi(st.nextToken()), stoi(st.nextToken())));
		}
		PriorityQueue<Timetable> pq2 = new PriorityQueue<>((a, b) -> {
			return a.end - b.end;
		});
		pq2.add(pq1.poll());
		while (!pq1.isEmpty()) {
			Timetable cur = pq1.poll();
			Timetable peek = pq2.peek();
			if (peek.end <= cur.start) {
				pq2.poll();
				pq2.add(new Timetable(peek.start, cur.end));
			} else {
				pq2.add(cur);
			}
		}
		System.out.println(pq2.size());
		br.close();
	}

	private static int stoi(String readLine) {
		return Integer.parseInt(readLine);
	}

}