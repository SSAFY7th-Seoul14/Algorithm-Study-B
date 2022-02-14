import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준11047번. 동전 0
public class BOJ11047_Coin {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int result = 0;
		int idx = n-1;
		while(k!=0) {
			int coin = arr[idx];
			int count = k/coin;
			k -= coin*count;
			idx--;
			result += count;
		}
		
		System.out.println(result);
	}

}
