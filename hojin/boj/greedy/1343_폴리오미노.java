import java.io.*;

public class BOJ1343_폴리오미노 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int n = str.length();
		char[] charList = new char[n];
		charList = str.toCharArray();
		int cnt = 0;
		boolean possible = true;
		for (int i = 0; i < n; i++) {
			if (charList[i] == 'X') {
				++cnt;
				if (cnt == 4) {
					for (int j = 0; j < 4; j++) {
						charList[i - j] = 'A';
					}
					cnt = 0;
				} else if (i == n - 1) {
					if (cnt == 2) {
						for (int j = 0; j < 2; j++) {
							charList[i - j] = 'B';
						}
					} else {
						possible = false;
						break;
					}
				} else if (charList[i + 1] != 'X' && cnt == 2) {
					for (int j = 0; j < 2; j++) {
						charList[i - j] = 'B';
					}
					cnt = 0;
				}
			} else {
				if (cnt % 2 != 0) {
					possible = false;
					break;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (char c : charList) {
			sb.append(c);
		}
		if (possible) {
			System.out.println(sb);
		} else {
			System.out.println("-1");
		}
	}

}