import java.util.*;
import java.io.*;

public class BOJ6603_로또 {

	static StringBuilder sb = new StringBuilder();
	static int k;
	static int[] numbers = new int[6], data;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			k = stoi(st.nextToken());
			if (k == 0)
				break;
			data = new int[k];
			for (int i = 0; i < k; ++i) {
				data[i] = stoi(st.nextToken());
			}
			combi(0, 0);
			sb.append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static void combi(int cnt, int start) {
		if (cnt == 6) {
			for (int i : numbers) {
				sb.append(i).append(' ');
			}
			sb.append('\n');
			return;
		}
		for (int i = start; i < k; ++i) {
			numbers[cnt] = data[i];
			combi(cnt + 1, i + 1);
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
