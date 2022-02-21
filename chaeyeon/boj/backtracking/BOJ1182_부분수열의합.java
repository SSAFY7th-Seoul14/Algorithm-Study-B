import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182_부분수열의합 {
	static int N, S;
	static int[] arr;
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		combi(0,0);
		
		if(S == 0) result--;
		System.out.println(result);
	}

	private static void combi(int cnt, int sum) {
		if(sum == S && cnt==N) {
			result++;
		}
		if(cnt == N) return;
		
		combi(cnt+1, sum + arr[cnt]);
		combi(cnt+1, sum);
		
	}

}
