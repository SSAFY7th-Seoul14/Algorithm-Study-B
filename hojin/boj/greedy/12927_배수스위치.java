import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ12927 {

	static BufferedReader br;
	static char[] inp;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 입력
		inp = br.readLine().toCharArray();
		br.close();
		int cnt = 0;
		boolean flag;
		for (int i = 0; i < inp.length; i++) {
			flag = false;
			// 1부터 탐색하면서 Y 만나면
			if (inp[i] == 'Y') { // 0이 Y면
				inp[i] = 'N';
				flag = true;
				// 해당 조건에 맞는 만큼 다 돌면서 Y면 N으로 바꾸기
				int j = 2 * (i + 1) - 1; //
				while (j < inp.length) {
					if (inp[j] == 'Y') {
						inp[j] = 'N';
					} else {
						inp[j] = 'Y';
					}
					j += (i + 1);
				}
			}
			if (flag)
				cnt++;
			// 탐색 끝나면 종료
		}
		System.out.println(cnt);
	}

}