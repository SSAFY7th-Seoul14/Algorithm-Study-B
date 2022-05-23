import java.io.*;
import java.util.*;

public class BOJ1715_카드정렬하기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int n = stoi(br.readLine());
		for (int i = 0; i < n; ++i) {
			pq.add(stoi(br.readLine()));
		}
		int answer = 0;
		while (pq.size() > 1) {
			int sum = 0;
			sum += pq.poll();
			sum += pq.poll();
			answer += sum;
			pq.add(sum);
		}
		System.out.println(answer);
		br.close();
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
