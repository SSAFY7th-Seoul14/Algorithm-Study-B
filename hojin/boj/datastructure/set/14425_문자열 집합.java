import java.io.*;
import java.util.*;

public class BOJ14425_문자열집합 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Set<String> set = new HashSet<>();
		for (int i = 0; i < n; ++i) {
			set.add(br.readLine());
		}
		int answer = 0;
		for (int i = 0; i < m; ++i) {
			if (set.contains(br.readLine())) {
				++answer;
			}
		}
		System.out.println(answer);
		br.close();
	}

}
