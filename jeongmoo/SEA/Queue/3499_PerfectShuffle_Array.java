import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SW Expert Academy 3499번. 퍼펙트 셔플
public class SWEA3499_PerfectShuffle {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			// 입력
			int n = Integer.parseInt(br.readLine());
			String[] arr = new String[n];
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[j] = st.nextToken();
			}
			
			// 처리
			StringBuilder sb = new StringBuilder();
			int first = 0;
			int second = (int) Math.ceil(n/2.0);
			int count = second;
			for(int j=0; j<count; j++) {
				sb.append(arr[first++] + " ");
				
				if(j==count-1 && n%2==1)
					continue;
				sb.append(arr[second++] + " ");
			}
			
			// 출력
			System.out.println("#" + (i+1) + " " + sb.toString());
		}
	}
}
