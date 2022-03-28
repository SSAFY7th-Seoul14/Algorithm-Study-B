import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2512번. 예산
public class BOJ2512_Budget {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] data = new int[n];
		
		int sum = 0;
		int max = Integer.MIN_VALUE;
		StringTokenizer st = new StringTokenizer(br.readLine());;
		for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(st.nextToken());
			sum += data[i];
			max = Math.max(max, data[i]);
		}
		
		int m = Integer.parseInt(br.readLine()); // 총 예산
		
		// 모든 요청이 가능한 경우
		if (sum <= m) {
			System.out.println(max);
			return;
		}
		
		// 아니면 최대값을 찾자.
		int start = 0;
		int end = max;
		int mid = 0;
		while(start<=end) {
			mid = (start+end)/2;
			sum = getSum(data, mid);
			if (m == sum) {
				System.out.println(mid);
				return;
			} else if (m < sum) {
				end = mid-1;
			} else if (sum < m) {
				start = mid+1;
			}
		}
		
		// 출력
		System.out.println(end);
	}
	
	public static int getSum(int[] data, int limit) {
		int sum = 0;
		for (int i : data) {
			if (i <= limit)
				sum += i;
			else
				sum += limit;
		}
		return sum;
	}
}

