import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//SW Expert Academy 3499번. 퍼펙트 셔플
//큐를 사용
public class SWEA3499_PerfectShuffle2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Queue<String> q = new LinkedList<>();
		Queue<String> q2 = new LinkedList<>();

		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			// 입력
			int n = Integer.parseInt(br.readLine());			
			st = new StringTokenizer(br.readLine());
			
			int count = n%2==0? n/2: n/2+1;  			
			for (int j = 0; j < count; j++) {
				q.offer(st.nextToken());
			}
			while (st.hasMoreTokens()) {
				q2.offer(st.nextToken());
			}
			
			// 처리
			StringBuilder sb = new StringBuilder();
			while (true) {
				if (q.isEmpty())
					break;
				else
					sb.append(q.poll() + " ");
				
				if (q2.isEmpty())
					continue;
				else
					sb.append(q2.poll() + " ");
			}
			
			// 출력
			System.out.println("#" + (i+1) + " " + sb.toString());
		}
	}
}
