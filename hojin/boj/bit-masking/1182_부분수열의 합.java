import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182 {

	static int n, s, count;
	static int[] set;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		set = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			set[i] = Integer.parseInt(st.nextToken());
		}
		count = 0;
		subset(0, 0);
		System.out.println(count);
	}

	private static void subset(int cnt, int flag) {
		if (cnt == n) {
			if (flag == 0)
				return;
			int sum = 0;
			for (int i = 0; i < n; i++) {
				if ((flag & 1 << i) != 0) {
					sum += set[i];
				}
			}
			if (sum == s)
				count++;
			return;
		}
		subset(cnt + 1, flag | 1 << cnt);
		subset(cnt + 1, flag);
	}

}