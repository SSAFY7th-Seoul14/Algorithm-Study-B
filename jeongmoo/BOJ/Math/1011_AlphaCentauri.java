import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준1011번. Fly me to the Alpha Centauri
public class BOJ1011_AlphaCentauri {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < t; i++) {
			// 입력
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			// 처리
			int distance = end-start; // 이동해야할 거리
			// n번 최대값?
			// 1 -> 1		-> 1
			// 2 -> 11		-> 2
			// 3 -> 121		-> 4
			// 4 -> 1221	-> 6
			// 5 -> 12321 	-> 9 12 16 20 25 30 36 42 ..
			
			// 다르게 생각하면 최대속도-1까지의 합x2 + 현재속도(홀수번째면 1번, 짝수번째면 2번 더함)
			// -> 1 ~ 최대속도-1 까지의 합. n(n+1)/2인데 *2 -> (최대속도-1)*최대속도
			// 홀수번째만 보자면 (최대속도-1)*최대속도 + 최대속도 = 최속^2 -최속 + 최속 = 최속^2 
			// -> 즉 홀수번째는 최대속도 제곱값 -> 최대이동거리
			
			// 역발상으로 이동해야할 거리 -> n을 찾는다.
			// 거리를 제곱근하여 최대속도가 몇이 되어야 도착가능한지 속도를 찾음.
			double tempSpeed = Math.sqrt(distance);
			// 그 속도가 되려면 필요한 인덱스를 찾는다.
			// n번째의 최대속도는 (n+1)/2이므로 역계산
			double tempN = tempSpeed*2-1;
			int n = (int)Math.ceil(tempN); // 소수점은 한번더 가야하므로 올림처리
			
			// 출력
			sb.append(n + "\n");
		}
		System.out.println(sb.toString());
	}
	
}