import java.io.*;
import java.util.*;

// BOJ / 강의실 배정 / G5 / 40분
public class Main_11000 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int study[][] = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			study[i][0] = Integer.parseInt(st.nextToken());
			study[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(study, new Comparator<int[]>() { // 시작시간 오름차순, 시작시간 같으면 종료시간 오름차순
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0])
					return o1[1] - o2[1];
				else
					return o1[0] - o2[0];
			}
		});
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(study[0][1]); 
		for (int i = 1; i < N; i++) {
			int S = study[i][0];
			if (S >= pq.peek()) {
				pq.poll();
				pq.add(study[i][1]);
			} else {
				pq.add(study[i][1]);
			}
		}
		System.out.println(pq.size());
	}
}
