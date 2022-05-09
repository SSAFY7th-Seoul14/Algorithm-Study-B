import java.io.*;
import java.util.*;

public class BOJ11659_구간합구하기4 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = stoi(st.nextToken());
		int m = stoi(st.nextToken());
		int[] numbers = new int[n + 1];
		int[] accums = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; ++i) {
			numbers[i] = stoi(st.nextToken());
			accums[i] = accums[i - 1] + numbers[i];
		}
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			sb.append(accums[to] - accums[from - 1]).append('\n');
		}
		System.out.println(sb);
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
