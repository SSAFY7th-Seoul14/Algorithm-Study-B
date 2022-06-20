import java.util.*;
import java.io.*;

public class BOJ11053_가장긴증가하는부분수열 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = stoi(br.readLine());
		int[] arr = new int[n];
		int[] LIS = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = stoi(st.nextToken());
		}
		int max = 0;
		for (int i = 0; i < n; i++) {
			LIS[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && LIS[i] < LIS[j] + 1) {
					LIS[i] = LIS[j] + 1;
				}
			}
			max = Math.max(max, LIS[i]);
		}
		bw.write(String.valueOf(max));
		bw.flush();
		bw.close();
		br.close();
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
