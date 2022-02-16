import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 1655번. 중앙값 구하기
public class BOJ2696_MiddleHeap {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		// 더 큰수를 저장하는 big 힙은 오름차순 (낮은 값 먼저)
		// 더 작은수를 저장하는 small 힙은 내림차순 (큰 값 먼저) 
		PriorityQueue<Integer> big = new PriorityQueue<>();
		PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < t; tc++) {
			// 초기화
			big.clear();
			small.clear();
			
			// n 입력
			int n = Integer.parseInt(br.readLine());
			int count = n/10 + 1;					// 1줄에 10개씩 들어오므로 몇번 읽을지 계산
			
			sb.append((n+1)/2).append("\n"); 		// 중앙값의 개수는 (n+1)의 반
			boolean print = false;					// 계속 반전하면서 true일때만 출력 (홀수번째만 출력하게)
			
			// 원소값 입력 & 처리
			for (int i = 0; i < count; i++) {
				st = new StringTokenizer(br.readLine());
				
				// 처음 이후에 i가 짝수(20개 읽으면 -> 10개 출력)이면 줄바꿈
				if(i>0 && i%2==0)
					sb.append("\n");
				
				while(st.hasMoreTokens()) {
					int num = Integer.parseInt(st.nextToken());
					
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
					
					print = !print;				// 홀수번째만 출력하기 위해 print변수 반전
					
					// 작은 수 힙의 최상단이 중간값
					if (print)
						sb.append(small.peek() + " ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
