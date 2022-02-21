import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

// 백준 1655번. 가운데를 말해요
public class BOJ1655_MiddleHeap {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		// 더 큰수를 저장하는 big 힙은 오름차순 (낮은 값 먼저)
		// 더 작은수를 저장하는 small 힙은 내림차순 (큰 값 먼저) 
		PriorityQueue<Integer> big = new PriorityQueue<>();
		PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
		StringBuilder sb = new StringBuilder();
		
		// 입력
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			
			// small을 우선으로 채운다.
			if (big.size() >= small.size())
				small.offer(num);
			else
				big.offer(num);
			
			// 작은 수를 저장하는 힙에 더 큰수가 들어가있다면 둘을 바꾼다.
			if (big.size() != 0 && big.peek() < small.peek()) {
				int smallValue = big.poll();
				int bigValue = small.poll();
				big.offer(bigValue);
				small.offer(smallValue);
			}
			
			// 작은 수 힙의 최상단이 중간값
			sb.append(small.peek()).append("\n");
		}
		System.out.println(sb);
	}
}
