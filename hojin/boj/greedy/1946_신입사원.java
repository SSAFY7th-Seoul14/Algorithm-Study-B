import java.util.*;
import java.io.*;

public class BOJ1946_신입사원 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; ++tc) {
			int n = Integer.parseInt(br.readLine());
			int[] grades = new int[n + 1];
			for (int i = 0; i < n; ++i) {
				st = new StringTokenizer(br.readLine());
				int grade1st = Integer.parseInt(st.nextToken());
				int grade2nd = Integer.parseInt(st.nextToken());
				grades[grade1st] = grade2nd;
			}
			int min = grades[1];
			int cnt = 1;
			for (int i = 2; i <= n; ++i) {
				if (grades[i] < min) {
					++cnt;
					min = grades[i];
				}
			}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
		br.close();
	}

}
