import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 1697번. 숨바꼭질
public class BOJ1697_HideAndSeek {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		boolean[] isVisited = new boolean[100001];
		Queue<Integer> q = new LinkedList<>();
		q.offer(n);
		isVisited[n] = true;
		
		int time = -1;
		while(!q.isEmpty()) {
			time++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int x = q.poll();
				if (x==k) {
					System.out.println(time);
					return;
				}
				
				if (1<=x && !isVisited[x-1]) {
					q.offer(x-1);
					isVisited[x-1] = true;
				}
				if (x<100000 && !isVisited[x+1]) {
					q.offer(x+1);					
					isVisited[x+1] = true;
				}
				if (x<=50000 && !isVisited[x*2]) {
					q.offer(x*2);					
					isVisited[x*2] = true;
				}
			}
		}
	}
}