import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BOJ5430 {

	static BufferedReader br;
	static StringBuilder sb;
	static int T, tc, n, i, j;
	static String funcP, tmpStr, tmpStrList[];
	static ArrayDeque<Integer> dq;
	static boolean err, rvrs;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		dq = new ArrayDeque<Integer>();
		for (tc = 0; tc < T; tc++) {
			// 각 case별 초기화
			dq.clear();
			err = rvrs = false;
			// 함수 P String 받기
			funcP = br.readLine();
			// 배열 내 개수
			n = Integer.parseInt(br.readLine());
			// 문자열 처리
			// 한줄 받고
			tmpStr = br.readLine();
			// substring을 통해 양 끝에 []제거
			tmpStr = tmpStr.substring(0, tmpStr.length() - 1).substring(1);
			// ,을 기준으로 StringArray 생성
			tmpStrList = tmpStr.split(",");
			// 각 숫자들을 dq에 넣어주기
			for (j = 0; j < n; j++) {
				dq.offer(Integer.parseInt(tmpStrList[j]));
			}
			// funcP 실행
			for (j = 0; j < funcP.length(); j++) {
				switch (funcP.charAt(j)) {
				case 'R':
					// reverse 여부 check
					if (rvrs)
						rvrs = false;
					else
						rvrs = true;
					break;
				case 'D':
					// error check 하기
					if (!err) {
						if (rvrs) {
							if (dq.pollLast() == null) {
								err = true;
								sb.append("error");
							}
						} else {
							if (dq.poll() == null) {
								err = true;
								sb.append("error");
							}
						}
					}
					break;
				}
			}
			// err가 아닐 경우 []를 포함한 String 반환해야 함
			if (!err) {
				sb.append("[");
				if (!dq.isEmpty()) {
					if (rvrs) {
						while (!dq.isEmpty()) {
							sb.append(dq.pollLast()).append(",");
						}
					} else {
						while (!dq.isEmpty()) {
							sb.append(dq.poll()).append(",");
						}
					}
					sb.setLength(sb.length() - 1);
				}
				sb.append("]");
			}
			sb.append("\n");
		}
		br.close();
		System.out.println(sb);
	}
}