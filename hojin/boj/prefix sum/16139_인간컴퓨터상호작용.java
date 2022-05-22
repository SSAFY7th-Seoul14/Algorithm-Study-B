import java.util.*;
import java.io.*;

public class BOJ16139_인간컴퓨터상호작용 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		char[] S = br.readLine().toCharArray();
		int len = S.length;
		int q = stoi(br.readLine());
		int[][] alphabets = new int[len + 1][26];
		for (int i = 1; i <= len; ++i) {
			for (int j = 0; j < 26; ++j) {
				alphabets[i][j] = alphabets[i - 1][j];
			}
			++alphabets[i][S[i - 1] - 'a'];
		}
		for (int i = 0; i < q; ++i) {
			st = new StringTokenizer(br.readLine());
			char target = st.nextToken().charAt(0);
			int l = stoi(st.nextToken());
			int r = stoi(st.nextToken());
			sb.append(alphabets[r + 1][target - 'a'] - alphabets[l][target - 'a']).append('\n');
		}
		System.out.println(sb);
		br.close();
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
