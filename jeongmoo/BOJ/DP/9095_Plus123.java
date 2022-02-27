import java.io.InputStreamReader;
import java.io.BufferedReader;

// 백준9095번. 1,2,3더하기
public class BOJ9095_Plus123 {
	static int[] sum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			sum = new int[n+1];
			System.out.println(sum123(n));
		}
	}
	
	public static int sum123(int num) {
		// 남은 값이 0보다 적으면 만들 방법이 없다.
		if (num < 0)
			return 0;
		// 남은 값이 0이 되는 경우. 마지막 선택이므로 1가지 방법이 존재한다 (1,2,3 중 하나를 선택)
		if (num == 0)
			return 1;		
		
		if (sum[num] != 0)
			return sum[num];
		
		int result = 0;
		result += sum123(num-1);	// 1을 선택하면 나머지값은 1을 뺀다.
		result += sum123(num-2);	// 2를 선택하면 나머지값은 2를 뺀다.
		result += sum123(num-3);	// 3을 선택하면 나머지값은 3을 뺀다.
		return sum[num] = result;	// 메모이제이션. 갱신하면서 리턴
	}
}
