import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11047 {

	static BufferedReader br;
	static StringTokenizer st;
	static int n, k, i, values[], cnt;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		values = new int[n];
		for (i = 0; i < n; i++) {
			values[i] = Integer.parseInt(br.readLine());
		}
		for (i = n - 1; i >= 0; i--) {
			while (k >= values[i]) {
				k -= values[i];
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}