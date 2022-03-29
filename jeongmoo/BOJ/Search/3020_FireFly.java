import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 3020번. 개똥벌레
public class BOJ3020_FireFly {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());;
		int n = Integer.parseInt(st.nextToken()); // 길이
		int h = Integer.parseInt(st.nextToken()); // 높이
		int[] bottom = new int[n/2];
		int[] top = new int[n/2];
		
		// 20만
		for (int i = 0; i < n/2; i++) {
			bottom[i] = Integer.parseInt(br.readLine());
			top[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(bottom);
		Arrays.sort(top);
		
		// 높이마다 부딪히는 장애물 개수 카운트
		// 20만 * 50만 = 1000억. 여기서 시간 계산을 잘못함. 완탐을 돌리면 안된다.
		int min = Integer.MAX_VALUE;
		int count = 1;
		for (int i = 1; i <= h; i++) {
			// 석순 (아래)
			int start = 0;
			int end = n/2-1;
			while(start<=end) {
				int mid = (start+end)/2;
				if (i <= bottom[mid]) { //부딪히면 내려감
					end = mid-1;
				} else {
					start = mid+1;
				}
			}
			int total = n/2-1-end; // 결과적으로 end값이 안 부딪히는 최대값. 
			// end index ~ 끝까지 몇개있는지 counting 하면 되므로 전체(n/2-1) - 현재 index(end)
			
			// 종유석 (위)
			start = 0;
			end = n/2-1;
			while(start<=end) {
				int mid = (start+end)/2;
				if (h-top[mid] < i) { // 부딪히면 내려감
					end = mid-1;
				} else {
					start = mid+1;
				} 
			}
			total += n/2-1-end; // 마찬가지
			
			if (total < min) {
				min = total;
				count = 1;
			} else if (total == min) {
				count++;
			}
		}
		
		// 출력
		System.out.println(min + " " + count);
	}
}
