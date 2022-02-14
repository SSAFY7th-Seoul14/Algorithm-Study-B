import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SW Expert Academy 9229번. 한빈이와 Spot Mart
public class SWEA9229_SpotMart {
	static int result = -1;
	static int[] data;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());
		for (int i = 0; i < tc; i++) {
			result = -1;
			
			// 입력
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			data = new int[n];
			for (int j = 0; j < n; j++) {
				data[j] = Integer.parseInt(st.nextToken());
			}
			
			// 처리
			combination(0, 0, n, m, 0);
			
			// 출력
			System.out.printf("#%d %d\n",i+1, result);
		}
	}
	
	public static void combination(int idx, int start, int n, int m, int sum) {
		if(idx == 2) {
			if (sum <= m) {
				if (result < sum) {
					result = sum;
				}
			}
			return;
		}
		
		for(int i=start; i<n; i++) {
			combination(idx+1, i+1, n, m, sum+data[i]);
		}
	}
}
