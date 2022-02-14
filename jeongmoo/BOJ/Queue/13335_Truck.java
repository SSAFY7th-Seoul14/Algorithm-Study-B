import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준13335번. 트럭
public class BOJ13335_Truck {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		

		Queue<Integer> q = new LinkedList<>(); // 데이터 큐
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			q.offer(Integer.parseInt(st.nextToken()));
		}
		
		// 처리
		int total = 0; // 현재 다리에 있는 트럭무게 총합
		int time = 0; // 경과한 시간
		int[] bridge = new int[w];
		while(true) {
			time++;
			// 모든 트럭이 지나가면
			if(q.isEmpty() && 0 == total)
				break;
						
			// 지나갈 수 있으면 꺼낸다.
			if (!q.isEmpty() && total+q.peek() <= l) {
				int num = q.poll();
				total += num;
				bridge[w-1] = num;
			} else {
				bridge[w-1] = 0;
			}
			
			// 모든 트럭이 움직인다.
			total -= bridge[0];
			for (int i = 1; i < bridge.length; i++) {
				bridge[i-1] = bridge[i];
			}
		}
		
		// 출력
		System.out.println(time);
	}
}
