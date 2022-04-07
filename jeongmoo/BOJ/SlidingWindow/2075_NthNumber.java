import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 2075번. N번째 큰 수
public class BOJ2075_NthNumber {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
               
        // 처리
        // 슬라이딩 윈도우 방식을 이용
        // n번째 큰 수를 찾으므로 우선순위큐를 오름차순으로 정렬하여 항상 n개를 유지하면 peek이 n번째 수이다.
        // 이 경우 n번째 수보다 큰 경우에만 우선순위 큐에 삽입하고, 하나를 빼서 사이즈를 n으로 맞춘다.
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {    	
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < n; j++) {
        		int num = Integer.parseInt(st.nextToken());
        		if (pq.size() == n) {
        			if (pq.peek() < num) {
        				pq.poll();
        				pq.offer(num);
        			}
        		} else {
        			pq.offer(num);
        		}
			}
    	}
        System.out.println(pq.peek());
    }
}
