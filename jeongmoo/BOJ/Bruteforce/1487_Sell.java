import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1487번. 물건 팔기
public class BOJ1487_Sell {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] data = new int[n];
		int[] shipping = new int[n];
		
		int min = Integer.MAX_VALUE;
		int max = 0;
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			data[i] = Integer.parseInt(st.nextToken());
			shipping[i] = Integer.parseInt(st.nextToken());
			min = Math.min(min, data[i]);
			max = Math.max(max, data[i]);
		}
		
		int result = 0;
		int maxTotal = 0;
		for (int money = max; money >= min; money--) {
			int total = 0;
			for (int i = 0; i < n; i++) {
				if (money <= data[i]) {
					int gain = money - shipping[i];
					if (0 < gain) // 손해면 안 판다.
						total += gain;
				}
			}
			if (maxTotal <= total && 0 < total) {
				maxTotal = total;
				result = money;
			}
		}
		System.out.println(result);
	}
}
