import java.util.*;
import java.io.*;

public class BOJ15654_N과M5 {

	static int[] input, datas;
	static int n, m;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		input = new int[n];
		m = stoi(st.nextToken());
		datas = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			input[i] = stoi(st.nextToken());
		}
		Arrays.sort(input);
		permu(0, 0);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static void permu(int selected, int cnt) {
		if (cnt == m) {
			for (int i = 0; i < m; i++) {
				sb.append(datas[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		for (int i = 0; i < n; i++) {
			if ((selected & 1 << i) > 0)
				continue;
			datas[cnt] = input[i];
			permu(selected | 1 << i, cnt + 1);
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
