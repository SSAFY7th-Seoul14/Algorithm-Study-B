import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 2075번. N번째 큰 수
public class BOJ2075_NthNumber {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
               
        // 처리
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++) {    	
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < n; j++) {
				pq.offer(Integer.parseInt(st.nextToken()));
			}
    	}
        for (int i = 0; i < n-1; i++) {
			pq.poll();
		}
        System.out.println(pq.poll());
    }
}
