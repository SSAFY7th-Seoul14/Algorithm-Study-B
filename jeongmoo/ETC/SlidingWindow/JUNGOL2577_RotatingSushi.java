import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 정올 2577번. 회전 초밥(고)
public class JUNGOL2577_RotatingSushi {
	static int n, d, k, c;
	static int result = 0;
	static int[] list;
	static int[] sushi;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 벨트 위 접시 수
		d = Integer.parseInt(st.nextToken()); // 초밥의 가지수
		k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
		c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		
		list = new int[n];
		for (int i = 0; i < n; i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
		
		// 처리
		// 접시 0번부터 먹는 경우. n번부터 먹는 경우로 n번 돈다
		// n 300만. 
		// 처음에는 k개를 더해둔다. 
		sushi = new int[d+1];
		int count = 0;
		for (int i = 0; i < k; i++) {
			int sushiNo = list[i];
			if (++sushi[sushiNo] == 1)
				count++;
		}
		result = sushi[c]==0? count+1: count;
		
		// 슬라이딩 윈도우
		for (int i = 0; i < n; i++) {
			// 앞을 뺀다.
			int sushiNo = list[i];
			if (--sushi[sushiNo] == 0)
				count--;
			
			// 뒤를 더한다.
			sushiNo = list[(i+k)%n];
			if (++sushi[sushiNo] == 1)
				count++;
			
			result = Math.max(result, sushi[c]==0? count+1: count);
		}
		
		// 출력
		System.out.println(result);
	}
}