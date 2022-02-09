import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.Queue;

// 백준2164번. 카드2
public class BOJ2164_Card2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			q.offer(i);
		}
		
		int i = 0;
		while (1 < q.size()) {
			int num = q.poll();
			if(i%2!=0)
				q.offer(num);
			i++;
		}
		System.out.println(q.poll());
	}
}
