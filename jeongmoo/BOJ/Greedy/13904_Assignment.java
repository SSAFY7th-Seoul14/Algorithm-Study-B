import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.BufferedReader;

// 백준 13904번. 과제
public class BOJ13904_Assignment {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer>[] pq = new PriorityQueue[1001];
		for (int i = 0; i < pq.length; i++) {
			pq[i] = new PriorityQueue<>(Collections.reverseOrder());
		}
		
		StringTokenizer st;
		int maxDay = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int score = Integer.parseInt(st.nextToken());
			pq[day].offer(score);
			maxDay = Math.max(maxDay, day);
		}
		
		// 계산
		int maxScoreDay = 0;
		int maxScore = 0;
		int day = maxDay;
		int score = 0;
		while(day > 0) {
			maxScore = 0;
			maxScoreDay = 0;
			
			for (int i = day; i <= maxDay; i++) {
				if (pq[i].isEmpty()) 
					continue;
				
				if (pq[i].peek() > maxScore) {
					maxScore = pq[i].peek();
					maxScoreDay = i;
				}
			}
			
			if (maxScore > 0) {
				score += maxScore;
				pq[maxScoreDay].poll();
			}
			
			day--;
		}
		
		// 출력
		System.out.println(score);
	}
}
