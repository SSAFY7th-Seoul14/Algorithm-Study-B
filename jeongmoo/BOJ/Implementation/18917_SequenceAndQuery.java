import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 18917번. 수열과 쿼리 38
public class BOJ18917_SequenceAndQuery {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		long sum = 0;
		long xor = 0;
		long num = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			if (cmd == 1) {
				num = Integer.parseInt(st.nextToken());
				sum += num;
				xor ^= num;
			} else if (cmd == 2) {
				num = Integer.parseInt(st.nextToken());
				sum -= num;
				xor ^= num;
			} else if (cmd == 3) {
				sb.append(sum).append("\n");
			} else if (cmd == 4) {
				sb.append(xor).append("\n");
			}			
		}
		System.out.println(sb);
	}
}