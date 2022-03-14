import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 13164번. 행복 유치원
public class BOJ13164_HappyKindergarten {	
	static int n, k;
	static int[] data;
	static int[] diff;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());	// 원생 수
		k = Integer.parseInt(st.nextToken());	// 조의 개수
		
		data = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		diff = new int[n-1];
		for (int i = 0; i < n-1; i++) {
			diff[i] = data[i+1] - data[i];
		}
		
		// 계산
		// 처음과 끝의 차이는 diff배열을 0번부터 n-1번까지 더한 것
		// 조 1개를 선택 -> 모든 값을 더해야 함. 빼는 값이 없음.
		// 조 2개를 선택 -> 그 중 1개(k-1)를 끊어야 2개의 조로 나뉘어짐
		// 조 3개를 선택 -> 그 중 2개(k-1)를 끊어야 3개의 조로 나뉘어짐
		// 즉 전체 차이 개수 n-1개에서 k-1개를 제외하면 된다. 즉 n-1 - (k-1) = n-k
		// 가장 큰 값을 제외하는 것이 좋으므로 정렬하고 앞에서부터 더함  
		Arrays.sort(diff);
		int min = 0;
		for (int i = 0; i < n-k; i++) {
			min += diff[i];
		}
		
		System.out.println(min);
	}
}
