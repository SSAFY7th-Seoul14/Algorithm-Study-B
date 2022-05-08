import java.util.*;
import java.io.*;

public class BOJ11399_ATM {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] times = new int[n];
		for (int i = 0; i < n; ++i) {
			times[i] = stoi(st.nextToken());
		}
		Arrays.sort(times);
		int answer = 0;
		for (int i = 0; i < n; ++i) {
			answer += (n - i) * times[i];
		}
		System.out.println(answer);
		br.close();
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
