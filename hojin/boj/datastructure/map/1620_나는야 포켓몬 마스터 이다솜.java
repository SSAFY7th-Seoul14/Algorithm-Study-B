import java.util.*;
import java.io.*;

public class BOJ1620_나는야포켓몬마스터이다솜 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 1<= n, m <= 100,000
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Map<String, Integer> map = new HashMap<>();
		String[] poketmons = new String[n + 1];
		for (int i = 1; i <= n; ++i) {
			String str = br.readLine();
			poketmons[i] = str;
			map.put(str, i);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; ++i) {
			String str = br.readLine();
			try {
				int no = Integer.parseInt(str);
				sb.append(poketmons[no]).append('\n');
			} catch (Exception e) {
				sb.append(map.get(str)).append('\n');
			}
		}
		System.out.println(sb);
		br.close();
	}

}
