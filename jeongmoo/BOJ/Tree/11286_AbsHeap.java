import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 11286번. 절대값 힙
public class BOJ11286_AbsHeap {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st;
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)->Math.abs(o1)!=Math.abs(o2)?Math.abs(o1)-Math.abs(o2):o1-o2);
		StringBuilder sb = new StringBuilder();
		
		// 입력
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num == 0) {
				if (pq.isEmpty())
					sb.append("0").append("\n");
				else
					sb.append(pq.poll()).append("\n");
			} else {
				pq.offer(num);
			}
		}
		
		System.out.println(sb);
	}
}

