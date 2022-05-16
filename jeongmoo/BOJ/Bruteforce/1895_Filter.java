import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 1895번. 필터
public class BOJ1895_Filter {
	// 크기가 작으므로 완탐
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[][] data = new int[r][c];
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int t = Integer.parseInt(br.readLine());
		
		int count = 0;
		for (int i = 0; i < r-2; i++) {
			for (int j = 0; j < c-2; j++) {
				if (t <= getNoise(data, i, j))
					count++;
			}
		}
		System.out.println(count);
		
	}
	
	public static int getNoise(int[][] data, int r, int c) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = r; i <= r+2; i++) {
			for (int j = c; j <= c+2; j++) {
				pq.offer(data[i][j]);
			}
		}
		
		for (int i = 0; i < 4; i++) {
			pq.poll();
		}
		return pq.poll();
	}
}
