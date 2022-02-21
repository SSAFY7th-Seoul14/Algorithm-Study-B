import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 20304번. 비밀번호 제작
public class BOJ20304_CreatePassword {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		// 패스워드가 시작점인 다중 시작점 bfs로 생각.
		Queue<Integer> q = new LinkedList<>();
		int[] distance = new int[n+1];
		Arrays.fill(distance, -1);			
		// 배열을 음수값으로 초기화하지 않고 디폴트값인 0으로 두게되면 
		// bfs 중 최초비밀번호를 만났을 때 거리가 0이기 때문에 다시 갱신해버리고 답이 제대로 안 나올수 있으므로
		// 초기값은 음수로 초기화하고. 최초 비밀번호는 0으로 초기화를 해야한다.
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m ; i++) {
			int num = Integer.parseInt(st.nextToken());
			q.offer(num);		// 일단 패스워드들을 큐에 다 넣음.
			distance[num] = 0; 	// 최초 패스워드들은 거리가 0. 초기값 넣어준다.
		}
		
		// 해밍거리가 노드간 거리라고 생각하면된다. 해밍거리가 1인 숫자부터 bfs로 계속돌아서 최대값 찾으면 됨.
		int last = 0;
		while(!q.isEmpty()) {
			int current = q.poll(); // 현재 패스워드값
			
			// 해밍거리가 1인 값들을 찾아 큐에 다시 넣는다.
			for(int i=1; i<=n; i<<=1) {
				// 설정한 비트 외에는 0이므로 xor시 그대로임.
				// 0 xor 0 = 0, 1 xor 0 = 1이므로 변경되지 않고.
				// 설정한 비트는 1이므로 1 xor 1 = 0, 0 xor 1 = 1이므로 기존에서 반전됨.
				
				// 즉 현재값에서 해밍거리가 1인 값을 구할 수 있다. (1번째 비트가 다른 수, 2번째 비트가 다른 수, ...)
				// 이 값들로 bfs해서 계속 갱신
				int num = current^i;
				
				// 해당 값이 범위 안이고, 현재 해밍거리 값이 처음이면
				if (num <=n && distance[num] == -1) {
					distance[num] = distance[current] + 1;
					q.offer(num);
				}
			}
			last = current;
		}
		
		// 마지막 값이 가장 거리가 먼 것
		System.out.println(distance[last]);
	}
}