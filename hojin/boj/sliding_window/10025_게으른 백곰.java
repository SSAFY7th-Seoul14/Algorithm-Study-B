import java.util.*;
import java.io.*;

public class BOJ10025_게으른백곰 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = stoi(st.nextToken());
		int[] ice = new int[1000001];
		int k = stoi(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int g = stoi(st.nextToken());
			int x = stoi(st.nextToken());
			ice[x] = g;
		}
		int max = 0;
		int sum = 0;
		int diff = 2 * k + 1;
		for (int i = 0; i < 1000001; i++) {
			if (i >= diff) {
				sum -= ice[i - diff];
			}
			sum += ice[i];
			max = Math.max(max, sum);
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
