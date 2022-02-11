import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ5430 {

	static BufferedReader br;
	static StringBuilder sb;
	static int T, tc, n, i, j, cnt;
	static String funcP, tmpStr, tmpStrList[];
	static Deque<Integer> dq;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		dq = new ArrayDeque<Integer>();
		for (tc = 0; tc < T; tc++) {
			dq.clear();
			funcP = br.readLine();
			n = Integer.parseInt(br.readLine());
			tmpStr = br.readLine();
			tmpStr = tmpStr.substring(0, tmpStr.length() - 1).substring(1);
			tmpStrList = tmpStr.split(",");
			for (j = 0; j < n; j++) {
				dq.offer(Integer.parseInt(tmpStrList[j]));
			}
			flag = true;
			cnt = 0;
			for (j = 0; j < funcP.length(); j++) {
				switch (funcP.charAt(j)) {
				case 'R':
					cnt++;
					break;
				case 'D':
					delete();
					break;
				}
			}
			if (flag) {
				if (dq.isEmpty())
					sb.append("[]");
				else {
					sb.append("[");
					if (cnt % 2 == 1) {
						while (!dq.isEmpty()) {
							sb.append(dq.pollLast()).append(",");
						}
					} else {
						while (!dq.isEmpty()) {
							sb.append(dq.poll()).append(",");
						}
					}
					sb.setLength(sb.length() - 1);
					sb.append("]");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void delete() {
		if (cnt % 2 == 0 && flag) {
			if (dq.poll() == null) {
				flag = false;
				sb.append("error");
			}
		} else if (cnt % 2 == 1 && flag) {
			if (dq.pollLast() == null) {
				flag = false;
				sb.append("error");
			}
		}
	}
}