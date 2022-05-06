import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 11000번. 강의실 배정
public class BOJ11000_ClassroomAssignment {
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		
		int[][] data = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			data[i][0] = Integer.parseInt(st.nextToken());
			data[i][1] = Integer.parseInt(st.nextToken());
		}
		// 시작하는 시간 기준 정렬
		Arrays.sort(data, (o1, o2) -> o1[0]-o2[0]);
		
		PriorityQueue<Integer> classroom = new PriorityQueue<>();
		for (int[] cl : data) {
			int start = cl[0];
			int end = cl[1];
			if (classroom.isEmpty()) {
				classroom.offer(end);
				continue;
			}
			
			// 시작시간이 가장 빨리 끝나는 강의보다 빠르면 새 강의실이 필요하다.
			if (start < classroom.peek()) {
				classroom.offer(end);
			} else { // 가장 빨리 끝나는 강의실에서 다음 수업이 가능하면
				classroom.poll();
				classroom.offer(end);
			}
		}
		
		System.out.println(classroom.size());
	}
}
