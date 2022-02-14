package queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13335_트럭 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Queue<Integer> drive = new LinkedList<>(); // 다리위의 트럭
		Queue<Integer> wait = new LinkedList<>(); // 대기트럭
		
		for(int i = 0; i < N; i++) {
			wait.add(Integer.parseInt(st.nextToken()));
		}
	
		for(int i = 0; i < W; i++) {
			drive.add(0); // 다리길이만큼 0으로 채워줘 다리길이를 유지하는 큐배열을 만들 것임
		}
		
		int sum = 0;//다리위 무게합
		int count = 0;// 시간합
		
		
		while(!wait.isEmpty()) {// 대기트럭이 없을 때 까지
			int truck = wait.peek();
			sum -= drive.poll(); // 일단 맨 앞의 트럭을 한칸 움직인다.
			count++;
			if(sum + truck > L) { //다리위 트럭 무게와 들어갈 트럭무게의 합이 최대무개를 넘을 때
				drive.add(0); //새로운 트럭을 집어넣지 않고 0을 넣어준다.
			}
			else {//새로운 트럭이 들어갈 수 있을 때
				drive.add(wait.poll()); // 대기열에서 뽑아 다리위 트럭배열에 넣어준다.
				sum += truck;// 들어간 트럭의 무게를 더해준다.
			}
		}
		
		System.out.println(count+W);
		
	}

}
