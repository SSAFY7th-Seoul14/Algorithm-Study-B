import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//@ 해결방법이 생각 안나서 구글 참조
//시작시간 기준으로 정렬한 후 pq에 끝나는 시간을 넣어 계산
//https://www.acmicpc.net/problem/11000
//골드5
public class BOJ11000_강의실배정 {
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] times = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			times[i][0] = Integer.parseInt(st.nextToken());
			times[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(times, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0])
					return o1[1] - o2[1];
				return o1[0] - o2[0];
			}
			
		});
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(times[0][1]);
		for (int i = 1; i < N; i++) {
			if(times[i][0] >= pq.peek()) {
				pq.poll();
				
			}
			pq.add(times[i][1]);
		}
		
		System.out.println(pq.size());
		
	}
}
